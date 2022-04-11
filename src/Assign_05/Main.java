package Assign_05;

import java.util.ArrayList;

/**
 * Name: Aditya Verma
 * Date: April 10, 2022
 * Description: Lab 09 "Bishop's Bridge" Submission for CS321.
 */
public class Main {
    /**
     * Main method testing out the Bishop's Bridge Functionality
     *
     * @param args
     */
    public static void main(String[] args) {

        ArrayList<Car> cars = new ArrayList<>(); //collection of cars passed in the Bridge Constructor
        Bridge bridge = new Bridge(cars); //Bridge used to cross by the Cars

        //Creating Test Cars
        BishopsCar bishopsCar1 = new BishopsCar("Maruti", bridge);
        BishopsCar bishopsCar2 = new BishopsCar("Honda", bridge);
        BishopsCar bishopsCar3 = new BishopsCar("Lamborghini", bridge);
        LionsCar lionsCar1 = new LionsCar("Tesla", bridge);
        LionsCar lionsCar2 = new LionsCar("Mitsubishi", bridge);

        //Creating Thread objects from the initialized Cars
        Thread bishops1 = new Thread(bishopsCar1);
        Thread bishops2 = new Thread(bishopsCar2);
        Thread bishops3 = new Thread(bishopsCar3);
        Thread lions1 = new Thread(lionsCar1);
        Thread lions2 = new Thread(lionsCar2);

        //starting all threads
        lions1.start();
        lions2.start();
        bishops1.start();
        bishops2.start();
        bishops3.start();

        //printing cars on bridge
        bridge.printCars();
    }
}
