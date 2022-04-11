package Assign_05;

import java.util.Random;

/**
 * Name: Aditya Verma
 * Date: April 10, 2022
 * Description: Lab 09 "Bishop's Bridge" Submission for CS321.
 */
public class BishopsCar extends Car implements Runnable {

    public Random random = new Random();
    private Bridge bridge;
    public int lionsCarTime; //time a LionsCar takes to cross bridge

    /**
     * BishopsCar Constructor
     *
     * @param name
     * @param bridge
     */
    public BishopsCar(String name, Bridge bridge) {
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
                lionsCarTime = random.nextInt(10000);
                Thread.sleep(lionsCarTime);  //lions car driving across the bridge
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (bridge.cars.size() == 0) { //no cars on bridge
                try {
                    bridge.BishopsBoundCars.acquire(); //locking semaphore until bishop's car crosses.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bridge.addCar(this); //add Bishop's car on the bridge if bridge is empty
                try {
                    Thread.sleep(4 * lionsCarTime); //Bishop's car driving across the bridge
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                bridge.removeCar(this);
                System.out.println("Bishop's Car has crossed!\n");
                bridge.BishopsBoundCars.release(); //signalling that bishopcar has crossed.
            }

            //when the bridge is not empty
            else if ((bridge.cars.size() > 0) && hasLionCar()) { //if there is LionsCar on bridge
                try {
                    bridge.LionsBoundCars.acquire(); // waiting until LionsCar has crossed to add bishop's car
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (bridge.cars.size() == 0) { //no cars on bridge

                    try {
                        bridge.BishopsBoundCars.acquire(); //locking semaphore until bishop's car crosses.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bridge.addCar(this); //add Bishop's car on the bridge if bridge is empty

                    try {
                        Thread.sleep(4 * lionsCarTime); //Bishop's car driving across the bridge
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    bridge.removeCar(this);
                    System.out.println("Bishop's Car has crossed!\n");
                    bridge.BishopsBoundCars.release(); //signalling that bishopcar has crossed.

                    if (bridge.cars.size() == 0) {
                        bridge.LionsBoundCars.release();
                    }
                }
            }
        }
    }

    /**
     * Method checks if the bridge contains a Lion's Car
     *
     * @return boolean value
     */
    public boolean hasLionCar() {
        for (int i = 0; i < bridge.cars.size(); i++) {
            if (bridge.cars.get(i) instanceof LionsCar) {
                return true;
            }
        }
        return false;
    }
}
