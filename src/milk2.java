
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: milk2
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

public class milk2
{

	static BufferedReader f;
	static PrintWriter out;
	static String filename = "milk2";
	static boolean test = false;

	public static void main( String[] args ) throws IOException
	{

		if ( test )
		{
			f = new BufferedReader( new InputStreamReader( System.in ) );

			out = new PrintWriter( System.out );
		}
		else
		{
			f = new BufferedReader( new FileReader( filename + ".in" ) );

			out = new PrintWriter( new BufferedWriter( new FileWriter( filename + ".out" ) ) );
		}

		milk2 g = new milk2();
		g.run();

		out.close(); // close the output file
	}

	void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( f.readLine() );
		int n = Integer.valueOf( st.nextToken() );
		int[] start = new int[n], end = new int[n];
		for ( int i = 0; i < n; i++ )
		{
			st = new StringTokenizer( f.readLine() );
			start[i] = Integer.valueOf( st.nextToken() );
			end[i] = Integer.valueOf( st.nextToken() );
		}

		Arrays.sort( start );
		Arrays.sort( end );

		int i = 0, j = 0, maxLen = 0, maxGap = 0;
		while ( i < start.length - 1 )
		{
			while ( i < start.length - 1 && end[i] >= start[i + 1] )
				i++;
			// now either at end, or found next start
			maxLen = Math.max( maxLen, end[i] - start[j] );
			if ( i < start.length - 1 )
				maxGap = Math.max( maxGap, start[i + 1] - end[i] );
			j = i + 1;
			i++;
		}
		// if at end
		if ( i < start.length )
			maxLen = Math.max( maxLen, end[i] - start[j] );

		out.printf( "%d %d\n", maxLen, maxGap );
	}

}
