
/*-
  ID:feiteng li
  LANG:JAVA
  TASK:ride
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ride
{
	static BufferedReader f;
	static PrintWriter out;

	public static void main( String[] args ) throws IOException
	{

		BufferedReader f = new BufferedReader( new FileReader( "ride.in" ) );
		//
		out = new PrintWriter( new BufferedWriter( new FileWriter( "ride.out" ) ) );
		// out = new PrintWriter( System.out );

		String comet = f.readLine(), name = f.readLine();
		// String comet = "COMETQ", name = "HVNGAT";

		ride r = new ride();

		r.run( comet, name );
		out.close(); // close the output file
	}

	void run( String comet, String name )
	{
		long cometSum = 1, nameSum = 1;
		for ( char c : comet.toCharArray() )
			cometSum *= c - 'A' + 1;
		for ( char c : name.toCharArray() )
			nameSum *= c - 'A' + 1;
		if ( cometSum % 47 == nameSum % 47 )
			out.println( "GO" ); // output result
		else
			out.println( "STAY" ); // output result

	}
}
