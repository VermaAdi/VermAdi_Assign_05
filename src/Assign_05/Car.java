package Assign_05;

/**
 * Name: Aditya Verma
 * Date: April 10, 2022
 * Description: Lab 09 "Bishop's Bridge" Submission for CS321.
 */
abstract class Car {
    private String name;

    /**
     * Setter Method, updates name of the Car
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter Method, returns the name of the Car
     *
     * @return
     */
    public String getName() {
        return name;
    }
}
