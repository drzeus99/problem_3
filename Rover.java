package problem_3;

/**
 * Class which contains the Compass and Direction2d classes as members and stores the position of the rover
 * Additionally, this class handles moving and rotating the rover as well as printing out its current position
 * and heading in terms of the cardinal directions. 
 * @author zacharykraus
 *
 */
public class Rover { 
	private long x_position;
	private long y_position;
	private String moves;
	
	private Direction2d direction;
	private Compass compass;
	
	/**
	 * Constructor which builds Compass, Direction2d and the position of the rover
	 * @param input1 is the x_position in String format
	 * @param input2 is the y_position in String format
	 * @param input3 is the compass point in String Format
	 * @param input4 is the moves in String format
	 */
	protected Rover(String input1, String input2, String input3, String input4) {
		x_position = Long.parseLong(input1);
		y_position = Long.parseLong(input2);
		compass = new Compass();
		direction = new Direction2d(compass, input3, 1.0);
		moves = input4;
	}
	
	/**
	 * Method that iterates through the String moves using the characters in the string 
	 * to command the rover to rotate or move forward. 
	 * @param x_max is the maximum x coordinate the rover can move to
	 * @param y_max is the maximum y coordinate the rover can move to
	 */
	protected void move(long x_max, long y_max) {
		//iterates through the string moves to execute the commands
		//the commands are:
		// R which rotates the direction right 90 degrees
		// L which rotates the direction left 90 degrees
		// M which moves the position of rover using the direction object
		for (int i = 0; i<moves.length(); i++){
			char command = moves.charAt(i);
			switch (command) {
				case 'R':
					direction.rotate(-90);
					break;
				case 'L':
					direction.rotate(90);
					break;
				case 'M':
					//need to round this due to floating point errors and the way java is converting double to long
					x_position += Math.round(direction.x()); 
					//Checks to make sure x_position has not gone outside the viable grid
					//If x_position has gone outside the grid move the rover back onto the grid
					if (x_position < 0)
						x_position = 0;
					else if (x_position > x_max)
						x_position = x_max;
					//need to round this due to floating point errors and the way java is converting double to long
					y_position += Math.round(direction.y());
					//Checks to make sure y_position has not gone outside the viable grid
					//If y_position has gone outside the grid move the rover back onto the grid
					if (y_position < 0)
						y_position = 0;
					else if (y_position > y_max)
						y_position = y_max;
					break;
				default: //in case of an erroneous command
					continue;
			}
		}
	}
	
	/**
	 * Method that prints the position of the rover and the heading in terms of the cardinal directions
	 */
	protected void printPosition() {
		System.out.printf("%d %d %s%n", x_position, y_position,
				compass.getCompassPoints(direction.heading()));
	}
	
}
