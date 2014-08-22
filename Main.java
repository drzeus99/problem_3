package problem_3;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		String file=args[0]; //will later get rid of this and use the String[] args format instead
		String input="";
		FileReader d = new FileReader(file);
		int c;
		
		while ((c = d.read()) != -1) {
			input += Character.toString((char)c);//converts the integer returned from d.read to an ascii character
			//which is than appended to the string input
		}
		d.close();
		//I am certain there is a better way to handle the IO here but this is my first time ever writing java.
		//I literally finished the tutorial from http://www.tutorialspoint.com/java/ three days ago.
		
		MarsPlateau mars = new MarsPlateau(input); 
		mars.move_rovers();
		mars.print_rovers();
	}

}
