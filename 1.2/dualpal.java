
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: dualpal
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class dualpal
{

	static BufferedReader reader;
	static PrintWriter out;
	static String filename = "dualpal";
	static boolean test = false;

	public static void main( String[] args ) throws IOException
	{

		long t = System.currentTimeMillis();
		dualpal g = new dualpal();
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
		int n = Integer.valueOf( st.nextToken() ), s = Integer.valueOf( st.nextToken() );
		int count = 0, num = s + 1;
		while ( count < n )
		{

			int baseCount = 0;
			for ( int i = 2; i <= 10; i++ )
			{
				if ( baseCount > 1 )
					break;
				String string = Integer.toString( num, i );
				if ( string.indexOf( 0 ) != 0 && palindrome( string ) )
					baseCount++;
			}
			if ( baseCount > 1 )
			{
				out.println( num );
				count++;
			}
			num++;

		}
	}

	boolean palindrome( String string )
	{
		return string.equals( new StringBuilder( string ).reverse().toString() );
	}

}
