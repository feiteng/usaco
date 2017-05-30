
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: ariprog
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ariprog
{

	static String filename = "ariprog";

	static boolean test = false;

	static void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() );
		int[] heights = new int[n];
		for ( int i = 0; i < n; i++ )
		{
			st = new StringTokenizer( reader.readLine() );
			heights[i] = Integer.valueOf( st.nextToken() );
		}

		int total = Integer.MAX_VALUE;
		for ( int step = 0; step < 84; step++ )
		{
			int fl = step, ceil = fl + 17, sum = 0;
			for ( int i = 0; i < n; i++ )
			{
				int x = 0;
				if ( heights[i] < fl )
					x = fl - heights[i];
				if ( heights[i] > ceil )
					x = heights[i] - ceil;
				sum += x * x;
			}
			total = Math.min( total, sum );
		}
		out.println( total );
	}

	static BufferedReader reader;
	static PrintWriter out;

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
}
