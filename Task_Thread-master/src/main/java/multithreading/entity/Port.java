package multithreading.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    static final Logger logger = LogManager.getLogger();
    private static Lock locker = new ReentrantLock();
    private static Port portInstance;
    private static final int NUMBER_OF_PIERS = 2;
    private Queue<Pier> piers ;


    private Port() {
        this.piers=new LinkedList<>();
        for (int i = 0; i < NUMBER_OF_PIERS; i++) {
            piers.add(new Pier(i));
        }
        logger.log(Level.INFO, "Port is create");
    }

    public static Port getInstance() {
        if (portInstance == null) {
            locker.lock();
            if (portInstance == null) {
                portInstance = new Port();
            }
            locker.unlock();
        }
        return portInstance;
    }
    public synchronized Pier getPier(Ship ship) throws InterruptedException {
        if ( this.piers.isEmpty() ){
            logger.log(Level.INFO,"All piers are busy,please wait ");
            wait();
        }
            logger.log(Level.INFO,"Ship number {} took pier number {} ",ship.getShipId(),this.piers.peek().getNumber());
            this.piers.peek().setShip(ship);
            return this.piers.poll();
    }

    public synchronized void returnPier(Pier pier) {
        notify();
        logger.log(Level.INFO," Ship number {} vacated pier number {} ",pier.getShip().getShipId(),pier.getNumber());
       this.piers.offer(pier);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Port.class.getSimpleName() + "[", "]")
                .add("piers=" + piers)
                .toString();
    }
}
