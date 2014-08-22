package problem_3;

import java.util.Arrays;

/**
 * Generic class which stores the direction in 2 dimensions an object will move each time step.
 * The class allows the positions of objects to be updated each time step and
 * the direction to be rotated but not increased in magnitude. 
 * Additionally, the class allows calculating both the heading and the angle.   
 * @author zacharykraus
 *
 */
public class Direction2d {
	private double [] direction;

	/**
	* Constructor which uses an array to build the class
	* @param input array used to construct the class
	*/
	public Direction2d(double [] input) {
		direction = new double[2];
		direction = Arrays.copyOf(input, 2);
	}
	
	/**
	 * Constructor which uses an angle and the distance to build the class
	 * @param angle in degrees where 90 degrees corresponds to north or the vector (1,0)
	 *  and 0 degrees corresponds to east or the vector (0,1)
	 * @param distance the object moves per time step
	 */
	public Direction2d(double angle, double distance) {
		direction = new double[2];
		direction[0] = distance*Math.cos(angle*Math.PI/180.0);
		direction[1] = distance*Math.sin(angle*Math.PI/180.0);
	}
	
	/**
	 * Constructor which uses a compass, a compass_point and the distance to build the class  
	 * @param compass class which contains a map relating compass points to headings
	 * @param compass_point a string that is on the compass rose ie: E, W, SE, etc., etc.
	 * @param distance the object moves per time step
	 */
	public Direction2d(Compass compass, String compass_point, double distance) {
		direction = new double[2];
		double heading = compass.getHeading(compass_point);
		//convert heading to the angle used in mathematics ie: north corresponds to 0 degrees
		//and east corresponds to 90 degrees
		double angle = 90 - heading;
		if (angle < 0.00)
			angle += 360.00;
		//use the angle and distance to calculate direction
		direction[0] = distance*Math.cos(angle*Math.PI/180.0);
		direction[1] = distance*Math.sin(angle*Math.PI/180.0);
	}
	
	/**
	 * Method gets the direction array stored in the class
	 * @return the direction array
	 */
	public double [] getDirection() {
		return direction;
	}
	
	/**
	 * Method gets the x value in the direction array stored in the class
	 * @return the x value in the direction array
	 */
	public double x() {
		return direction[0];
	}
	
	/**
	 * Method gets the y value in the direction array stored in the class
	 * @return the y value in the direction array
	 */
	public double y() {
		return direction[1];
	}
	
	/**
	 * Method takes the current direction an object is moving in and calculates the angle
	 * @return the angle in degrees where 0 degrees corresponds with the vector (1,0) and 90 degrees with (0,1).
	 * If x and y in the direction array are both 0, this method returns NaN.
	 */
	public double angle() {
		double theta=Math.atan(direction[1]/direction[0]);
		//convert radians to degrees
		theta=180.00*theta/Math.PI;
		//need to convert the current range of theta from 90 to -90
		//to the new range of 0 to 360
		if (x() > 0) {
			if (y() >= 0) {
				return theta;
			} else if (y() < 0) {
				theta += 360.00;
			}		
		} else if (x() < 0) {	
			theta += 180.00;
		} else if (x() == 0) {
			if (y() > 0) {
				return theta;
			} else if (y() < 0) {
				theta += 360.00;
			}
		} else {//if none of the above cases fit the problem return NaN 
			return Double.NaN;	
		}
		return theta;
	}
	
	/**
	 * Method takes the current direction an object is moving in and calculates the heading
	 * @return the heading in degrees where 0 degrees corresponds with the vector (0,1) and 90 degrees with (1,0).
	 * If x and y in the direction array are both 0, this method returns NaN.
	 */
	public double heading() {
		double heading=90.00 - angle();
		if (heading < 0.00)
			heading += 360;
		return heading;
	}
	
	/**
	 * Method rotates the direction array stored in the class by the angle inputed
	 * @param angle in degrees where positive rotates counter clockwise and 
	 * negative rotates clockwise
	 */
	public void rotate(double angle) { //angle is in degrees
		double[][] rotation_matrix = 
			{{Math.cos(angle*Math.PI/180.0), -Math.sin(angle*Math.PI/180.0)},
			{Math.sin(angle*Math.PI/180.0), Math.cos(angle*Math.PI/180.0)}};
		direction = multiply(rotation_matrix, direction);
	}
	
	/**
	 * Method multiplies a matrix of 2x2 by a vector of 2
	 * @param matrix of doubles is of size 2x2
	 * @param vector of doubles is of size 2
	 * @return a vector of size 2 which equals matrix*vector
	 */
	private double[] multiply(double[][] matrix, double[] vector) {
		double[] ans = new double[2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j< 2; j++)
				ans[i] += matrix[i][j] * vector[j];
		}		
		return ans;
	}
	
}
