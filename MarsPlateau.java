package problem_3;
import java.util.*;

/**
 * Class which contains the maximum x and y positions and an ArrayList of Rovers.
 * This class allows the moving and printing of the Rovers stored in ArrayList.
 * @author zacharykraus
 *
 */
public class MarsPlateau {
	private ArrayList<Rover> rovers = new ArrayList<Rover>();
	private long x_max;
	private long y_max;
	
	/**
	 * Constructor which constructs an ArrayList of Rovers and stores the maximum x and y position.
	 * @param input String which contains all of the information necessary to build the maximum 
	 * x and y position and an ArrayList of Rovers
	 */
	public MarsPlateau(String input) {
		//Break input into an array of strings
		String[] array_of_strings = input.split("\\s");
		try {
			//Assign x_max and y_max using first two indexes
			x_max = Long.parseLong(array_of_strings[0]);
			y_max = Long.parseLong(array_of_strings[1]);
			//Use the rest of the indexes of array_of_strings to add Rover classes
			//to the ArrayList rovers
			for (int i = 2; i < array_of_strings.length; i += 4) {
				rovers.add(new Rover(array_of_strings[i], array_of_strings[i+1],
														 array_of_strings[i+2], array_of_strings[i+3]));
			}
		} catch (Exception e) {
			System.out.println("Invalid input");
			System.out.println("Needs to be in the form");
			System.out.println("for the plateau");
			System.out.println("xmax ymax");
			System.out.println("and for each rover");
			System.out.println("x_position y_position cardinal_direction");
			System.out.println("move_commands");
		}
	}
	
	/**
	 * Method which iterates through the ArrayList of Rovers and calls the Rovers move method
	 */
	public void move_rovers() {
		for (int i = 0; i < rovers.size(); i++) {
			rovers.get(i).move(x_max, y_max);
		}
	}
	
	/**
	 * Method which iterates through the ArrayList of Rovers and calls the Rovers printPosition method
	 */
	public void print_rovers() {
		for (int i = 0; i < rovers.size(); i++) {
			rovers.get(i).printPosition();
		}
	}
	
}
