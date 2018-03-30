package project3;


/**
 * This class is the program solving the puzzle.
 * The program is not interactive.
 * The program takes an array of integers from command line arguments and uses recursion to find
 * all possible paths from the first number of the array to the last number of the array. The 
 * moves are made based on the value of the element at the particular index.
 * 
 * @author sabah
 *
 */
public class WayFinder {
	
	public static int counter = 0;
    
	/**
	 * The main() method of this program.
	 * @param args array of String provided on the command line when the program is run
	 * The elements of the args array should be positive integers less than 100.
	 */
    public static void main(String[] args) {
        
        // Check that arguments exist
    		if (args.length == 0) {
    			System.err.println("Must enter command line arguments");
    			System.exit(1);
    		}
		// Validate command line arguments, and check that there are no 0's in the array, except in the last position
        for (int i = 0; i < args.length; i++) {
            if (Integer.parseInt(args[i]) < 0 || Integer.parseInt(args[i]) > 99) {
                System.err.println("Invalid value: values must be between 0 and 99");
                System.exit(1);
            }
            if (Integer.parseInt(args[i]) == 0 && i != args.length-1) {
                System.err.println("Invalid value: cannot have 0 inside the array");
                System.exit(1);
            }
        }
        // Verify that the last value is 0
        if (Integer.parseInt(args[args.length-1]) != 0) {
            System.err.println("Invalid value: last value must be 0.");
            System.exit(1);
        }
        
        // Populate array of MyInteger objects using values from args array
        MyInteger[] array = new MyInteger[args.length];
        for (int i = 0; i < args.length; i++) {
            array[i] = new MyInteger(Integer.parseInt(args[i]));
        }
        
        // Create a String to store the steps of a partcular solution
        String steps = "";
        
        // Call recursive method
        wayFinder(array, 0, steps);

        // Display number of solutions for puzzle
        if (counter == 0)
        		System.out.println("No way through this puzzle.");
        else
        		System.out.println("There are " + counter + " ways through this puzzle.");
    }
    

    /**
     * Solves the puzzle using recursion...
     * @param array array of MyInteger objects representing the command line arguments
     * @param curIndex integer representing the current index in array
     * @param steps String to contain all the steps, formatted as required
     */
    public static void wayFinder(MyInteger[] array, int curIndex, String steps) {
        
        /* BASE CASES */
    	
        // Check that the value at curIndex has not been visited for this path (would result in a repeated path)
        if (array[curIndex].getVisited() == true)
            return;

        // Successful solution
        if (curIndex == array.length-1) {
            counter++; // to keep track of total solutions
            // Print out the steps for current solution
            System.out.println(steps);
            return;
        }
        
        
        /* RECURSION PART */
        
        // Change visited of the value at curIndex to true
        array[curIndex].updateVisited();

        // Create left index and right index values
        int leftIndex = curIndex - array[curIndex].getNum();
        int rightIndex = curIndex + array[curIndex].getNum();
        
        // Check if left index is valid
        if (leftIndex >= 0 && leftIndex < array.length) {
        		// Add the current step to the String steps...for now
        		String step = formatStep(array, steps, "L", curIndex);
        		steps += step;
        		// Make the recursive call
        		wayFinder(array, leftIndex, steps);
        		
        		// After the above call returns, remove the most recent step from steps, clearing the way for the next step
        		steps = deleteStep(steps);
        		// Change visited of curIndex back to false
        		array[curIndex].revertVisited();
        } 
        
        // Check if right index is valid
        if (rightIndex >= 0 && rightIndex < array.length) {
        		// Add the current step to the String steps...for now
        		String step = formatStep(array, steps, "R", curIndex);
        		steps += step;
        		// Make the recursive call
        		wayFinder(array, rightIndex, steps);

        		// After the above call returns, remove the most recent step from steps, clearing the way for the next step
    			steps = deleteStep(steps);
    			// Change visited of curIndex to false
    			array[curIndex].revertVisited();
        }
    }
    

    /**
     * Formats the current step in the puzzle according to the spec
     * @param array array of MyInteger objects representing the command line arguments
     * @param steps String that holds all steps for a solution
     * @param direction String object storing a single character, "R" or "L", indicating which way the move was made
     * @param index integer representing the current index in array
     * @return step formatted String
     */
    public static String formatStep(MyInteger[] array, String steps, String direction, int index) {
    		// Initialize newStep variable
    		String newStep = "[";
    		
    		for (int i = 0; i < array.length-1; i++) {
    			// Append number, and space depending on amount of digits
		    if (array[i].getNum() < 10)
		    		newStep += " " + array[i].toString();
		    else
			    newStep += array[i].toString();
    	    
    	    		// Append direction if curIndex is reached
		    if (i == index)
		    		newStep += direction + ", ";
		    else
		    		newStep += " , ";
    		}
    		
    		// Last element should be 0, add newline character to the end
    		newStep += " 0 ]\n";
    		
    		return newStep;
    }
    
    /**
     * Removes the most recently added step from the String steps
     * @param steps String containing all the steps in a particular solution
     * @return String with the last step removed
     */
    public static String deleteStep(String steps) {
    		
    		int index = -1; // arbitrary value
    		// Find the '[' char closest to the end of the String - this denotes the start of the last step in steps
    		for (int i = steps.length()-1; i >= 0; i--) {
    			if (steps.charAt(i) == '[') {
    				index = i;
    				break;
    			}
    		}
		
    		// Just check that index was found
    		if (index != -1)
    			steps = steps.substring(0, index);
    		return steps;
    }
}