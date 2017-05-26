
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
import java.util.Arrays;
import java.util.Comparator;
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

	void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() ), m = Integer.valueOf( st.nextToken() );
		int[][] farmer = new int[m][2];
		for ( int i = 0; i < m; i++ )
		{
			st = new StringTokenizer( reader.readLine() );
			int u = Integer.valueOf( st.nextToken() ), v = Integer.valueOf( st.nextToken() );
			farmer[i][0] = u;
			farmer[i][1] = v;
		}
		Arrays.sort( farmer, new Comparator<int[]>()
		{
			@Override
			public int compare( int[] a, int[] b )
			{
				return a[0] - b[0];
			}
		} );
		int i = 0, cost = 0;
		for ( i = 0; i < farmer.length; i++ )
		{
			if ( n < farmer[i][1] )
			{
				cost += farmer[i][0] * n;
				break;
			}
			n -= farmer[i][1];
			cost += farmer[i][0] * farmer[i][1];

		}
		// now n >=0 and < farmer[i][0]

		out.println( cost );
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
}
