package Assign_05;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Name: Aditya Verma
 * Date: April 10, 2022
 * Description: Lab 09 "Bishop's Bridge" Submission for CS321.
 */
public class Bridge {

    ArrayList<Car> cars;
    Semaphore BishopsBoundCars;
    Semaphore LionsBoundCars;
    int count = 0; //arraylist iteration index

    /**
     * Bridge Constructor
     *
     * @param cars
     */
    public Bridge(ArrayList<Car> cars) {
        this.cars = cars; //cars array passed in Main
        BishopsBoundCars = new Semaphore(1);
        LionsBoundCars = new Semaphore(1);
    }

    /**
     * Method adds the car to the bridge
     *
     * @param car
     */
    public void addCar(Car car) {
        cars.add(car);
        System.out.println("CAR WAS ADDED! Car: " + car.getName());
    }


    /**
     * Method removes the car from the bridge
     *
     * @param car
     * @return int value, 1 if remove() is successful, 0 if not
     */
    public int removeCar(Car car) {

        while (count < cars.size()) {
            if (cars.get(count).getName().equals(car.getName())) {
                cars.remove(count); //remove cars
                System.out.println("CAR WAS REMOVED! Car: " + car.getName());
                return 1;
            }
            count++;
        }
        return 0;
    }

    /**
     * Method prints out the cars on the bridge
     */
    public void printCars() {
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(cars.get(i).getName());
        }
    }
}

