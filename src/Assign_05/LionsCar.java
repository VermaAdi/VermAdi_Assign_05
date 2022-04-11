package Assign_05;

import java.util.Random;

/**
 * Name: Aditya Verma
 * Date: April 10, 2022
 * Description: Lab 09 "Bishop's Bridge" Submission for CS321.
 */
public class LionsCar extends Car implements Runnable {

    public Random random = new Random();
    private Bridge bridge;
    public int BishopsCarTime; //time a BishopsCar takes to cross bridge

    /**
     * Lion's Car Constructor
     *
     * @param name
     * @param bridge
     */
    public LionsCar(String name, Bridge bridge) {
        this.setName(name);
        this.bridge = bridge;
    }

    @Override
    /**
     * Run Method for each Car thread
     */
    public void run() {

        while (true) {

            //sleep for a random time
            try {
                BishopsCarTime = random.nextInt(10000);
                Thread.sleep(BishopsCarTime);  //Bishops car driving across the bridge
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (bridge.cars.size() == 0) { //no cars on bridge
                try {
                    bridge.LionsBoundCars.acquire(); //locking semaphore until Lion's car crosses.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bridge.addCar(this); //add Lions car on the bridge if bridge is empty
                try {
                    Thread.sleep(BishopsCarTime / 100); //Lions car driving across the bridge
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                bridge.removeCar(this);
                System.out.println("Lion's Car has crossed!\n");
                bridge.LionsBoundCars.release(); //signalling that Lionscar has crossed.
            } else if ((bridge.cars.size() > 0) && hasBishopsCar()) { //if there is BishopsCar on bridge
                try {
                    bridge.BishopsBoundCars.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (bridge.cars.size() == 0) { //no cars on bridge
                    try {
                        bridge.LionsBoundCars.acquire(); //locking semaphore until lion's car crosses.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    bridge.addCar(this); //add Lion's car on the bridge if bridge is empty

                    try {
                        Thread.sleep(BishopsCarTime / 100); //Bishop's car driving across the bridge
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    bridge.removeCar(this);
                    System.out.println("Lion's Car has crossed!\n");
                    bridge.LionsBoundCars.release(); //signalling that lionscar has crossed.


                    if (bridge.cars.size() == 0) {
                        bridge.BishopsBoundCars.release(); //letting other side's cars go when no cars on bridge
                    }
                }
            }
        }
    }

    /**
     * Method checks if the bridge contains a Bishop's Car
     *
     * @return boolean value
     */
    public boolean hasBishopsCar() {
        for (int i = 0; i < bridge.cars.size(); i++) {
            if (bridge.cars.get(i) instanceof BishopsCar) {
                return true;
            }
        }
        return false;
    }
}

