
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: milk
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class milk
{

	static BufferedReader reader;
	static PrintWriter out;
	static String filename = "milk";
	static boolean test = false;

	public static void main( String[] args ) throws IOException
	{

		long t = System.currentTimeMillis();
		milk g = new milk();
		g.setup();
		g.run();

		if ( test )
			System.out.printf( "Run time... %s ms\n", System.currentTimeMillis() - t );

		out.close(); // close the output file

	}

	void setup() throws IOException
	{

		if ( test )
		{
			reader = new BufferedReader( new InputStreamReader( System.in ) );

			out = new PrintWriter( System.out );
		}
		else
		{
			reader = new BufferedReader( new FileReader( filename + ".in" ) );

			out = new PrintWriter( new BufferedWriter( new FileWriter( filename + ".out" ) ) );
		}

	}

	void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int b = Integer.valueOf( st.nextToken() );
		for ( int i = 1; i <= 300; i++ )
		{
			String ib = Integer.toString( i, b );
			String isq = Integer.toString( i * i, b );
			if ( palindrome( isq ) )
				out.printf( "%s %s\n", ib.toUpperCase(), isq.toUpperCase() );
		}
	}

	boolean palindrome( String string )
	{
		return string.equals( new StringBuilder( string ).reverse().toString() );
	}

}
