
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: holstein
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class holstein
{

	static String filename = "holstein";

	static boolean test = false;

	static void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() ), c1 = 0, c2 = 0, c3 = 0;
		int[] a = new int[n];
		for ( int i = 0; i < n; i++ )
		{
			st = new StringTokenizer( reader.readLine() );
			a[i] = Integer.valueOf( st.nextToken() );
			if ( a[i] == 1 )
				c1++;
			else if ( a[i] == 2 )
				c2++;
			else
				c3++;
		}
		System.out.println( Arrays.toString( a ) );

		out.println();

	}

	public static void main( String[] args ) throws IOException
	{

		long t = System.currentTimeMillis();
		setup();
		run();

		System.out.printf( "Run time... %s ms\n", System.currentTimeMillis() - t );

		out.close(); // close the output file

	}

	static void setup() throws IOException
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

	static BufferedReader reader;
	static PrintWriter out;

}
