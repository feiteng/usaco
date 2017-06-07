
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: numtri
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

public class numtri
{

	static String filename = "numtri";

	static boolean test = false;

	static void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() ), maxVal = 0;
		int[] a = new int[n], b = new int[n];
		for ( int i = 1; i < n + 1; i++ ) // outer loop
		{
			st = new StringTokenizer( reader.readLine() );
			for ( int j = 0; j < i; j++ ) // array calculation
			{
				int k = Integer.valueOf( st.nextToken() );
				if ( j == 0 )
					b[j] = a[j] + k;
				else if ( j == i - 1 )
					b[j] = a[j - 1] + k;
				else
					b[j] = Math.max( a[j - 1], a[j] ) + k;

				maxVal = Math.max( maxVal, b[j] );
			}
			a = Arrays.copyOf( b, b.length );
			// System.out.println( Arrays.toString( a ) );

		}

		out.println( maxVal );
	}

	public static void main( String[] args ) throws IOException
	{

		long t = System.currentTimeMillis();
		setup();
		run();

		if ( test )
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
