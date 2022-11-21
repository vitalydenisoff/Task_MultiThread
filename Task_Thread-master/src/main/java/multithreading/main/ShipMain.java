package multithreading.main;


import multithreading.entity.Port;
import multithreading.entity.Ship;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShipMain {
    public static void main(String[] args) {
        Ship ship1=new Ship(1);
        Ship ship2=new Ship(2);
        Ship ship3=new Ship(3);
        Ship ship4=new Ship(4);
        Ship ship5=new Ship(5);

       ExecutorService service= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
       service.execute(ship1);
       service.execute(ship2);
       service.execute(ship3);
       service.execute(ship4);
       service.execute(ship5);
       service.shutdown();

    }
}
