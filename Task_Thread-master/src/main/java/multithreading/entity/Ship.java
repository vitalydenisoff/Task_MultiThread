package multithreading.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ship extends Thread{
    static final Logger logger = LogManager.getLogger();
    private int shipId;
    private ShipStatus status;

    public Ship(int id){
        this.shipId=id;
        this.status=ShipStatus.WAITING;
    }

    public int getShipId() {
        return shipId;
    }


    @Override
    public void run() {
        Port port=Port.getInstance();
        logger.log(Level.INFO,"The ship number {} entered the port",this.getShipId());
        try {
            Pier pier=port.getPier(this);
            pier.unload(this);
            port.returnPier(pier);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.log(Level.INFO,"Ship number {} left the port",this.getShipId());


    }

}
