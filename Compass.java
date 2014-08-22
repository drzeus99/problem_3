package problem_3;

import java.util.*;
/**
 * Generic class which stores the compass rose and allows conversion between compass points and headings in degrees.
 * The headings in this class are done in such a way that east is 90 degrees and N is 0 degrees.
 * This is the conventional way of representing compass headings. 
 * @author zacharykraus
 * 
 */
public class Compass {
	private HashMap<String, Double> compass_rose;
	
	/**
	 * Constructor which builds the compass rose stored in this class. 
	 * The compass rose is a HashMap with keys that are the compass points
	 * and values that are the headings in degrees.
	 */
	public Compass() {
		//initialize cardinal_directions
		compass_rose = new HashMap<String, Double>();
		//this is not the complete compass rose
		//only the cardinal and ordinal directions are listed
		//directions such as NNW and WNW are missing
		compass_rose.put("N", 0.00);
		compass_rose.put("NE", 45.0);
		compass_rose.put("E", 90.0);
		compass_rose.put("SE", 135.0);
		compass_rose.put("S", 180.0);
		compass_rose.put("SW", 225.0);
		compass_rose.put("W", 270.0);
		compass_rose.put("NW", 315.0);
	}
	
	/**
	 * Method uses the compass point to get the heading stored in compass rose
	 * @param input the compass point
	 * @return The heading in degrees. Currently N is 0 degrees and E is 90 degrees.
	 */
	public double getHeading(String input) {	
		Double ans = compass_rose.get(input);
		if (ans == null) {
			System.out.println("Invalid compass point");
			System.out.println("Returning default heading of 0 degrees");
			return 0.0;
		}
		return ans;
	}
	
	/**
	 * Method uses the heading to get the compass point stored in compass rose
	 * @param angle corresponding to the heading
	 * @return The compass point
	 */
	public String getCompassPoints(double angle) {
		//need to round angle to account for drift due to floating point errors
		angle=Math.round(angle);
		//need to convert angle so that 0 <= angle < 360 degrees
		if (angle >= 360.00 || angle < 0.00) {
			int n_periods = (int)Math.floor(angle/360.00);
			//System.out.println(n_periods);
			angle -= (360.00*n_periods);
		}
		//System.out.println("the angle is " + angle);
		Set set = compass_rose.entrySet();
		Iterator i = set.iterator();
		while (i.hasNext()) {
			Map.Entry test = (Map.Entry)i.next();
			if (angle == (double) test.getValue()) {
				return (String)test.getKey();
			}
		}
		return "The heading cannot be converted to a compass point";
	}
}
