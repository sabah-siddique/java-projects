package project3;

/**
 * This class repesents an integer.
 * It is used in the WayFinder program to represent the integers in the array to be solved.
 * It stores a number as an int, as well as a boolean value indicating whether the MyInteger object
 * at a  particular index was already visited (true) or not (false).
 * 
 * @author sabah
 *
 */
public class MyInteger {
    
    private int num;
    private boolean visited;

    /**
     * Constructs a new MyInteger object with specified number.
     * @param num int value to be used for this MyInteger
     * @throws IllegalArgumentException if num parameter is invalid, based on the spec for WayFinder
     */
    public MyInteger(int num) throws IllegalArgumentException {
        if (num < 0) {
            throw new IllegalArgumentException("Value cannot be negative.");
        }
        this.num = num;
        this.visited = false;
    }

    /**
     * Returns the int value representing this MyInteger object
     * @return the int value of this MyInteger object
     */
    public int getNum() {
        return this.num;
    }

    /**
     * Returns the boolean value for visited for this MyInteger object
     * @return the boolean value for this MyInteger object
     */
    public boolean getVisited() {
        return this.visited;
    }

    /**
     * Updates the value of visited from false (default) to true
     */
    public void updateVisited() {
        this.visited = true;
    }
    
    /**
     * Reverts the value of visited from true to false
     */
    public void revertVisited() {
        this.visited = false;
    }

    /**
     * Returns the string representation of this MyInteger object
     * @return the string representation of this MyInteger object
     */
    public String toString() {
        return String.valueOf(this.num);
    }
}