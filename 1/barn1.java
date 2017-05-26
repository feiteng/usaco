
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: barn1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class barn1
{
	static BufferedReader reader;
	static PrintWriter out;
	static String filename = "barn1";
	static boolean test = false;

	public static void main( String[] args ) throws IOException
	{

		long t = System.currentTimeMillis();
		barn1 g = new barn1();
		g.setup();
		g.run();

		if ( test )
			System.out.printf( "Run time... %s ms\n", System.currentTimeMillis() - t );

		out.close(); // close the output file

	}

	/**
	 * Idea: starts with 1 block only
	 * adding a block means separating at biggest gap available
	 * 3 blocks means 2 gaps, thus m--
	 * 1 sort by gap
	 * 2 pick out the top m gaps
	 * 3 find lengths
	 * 
	 * 
	 * @throws IOException
	 */
	void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int m = Integer.valueOf( st.nextToken() ), s = Integer.valueOf( st.nextToken() ), c = Integer.valueOf( st.nextToken() );
		m--;
		if ( m > c )
		{
			out.println( c );
			return;
		}
		int[] stalls = new int[c];
		int[][] diff = new int[c - 1][2];
		for ( int i = 0; i < c; i++ )
		{
			st = new StringTokenizer( reader.readLine() );
			stalls[i] = Integer.valueOf( st.nextToken() );
		}
		Arrays.sort( stalls ); // is this needed?
		List<List<Integer>> list = new ArrayList<>();
		int k = 0, count = 0;
		if ( m > 0 )
		{
			for ( int i = 0; i < stalls.length - 1; i++ )
			{
				diff[i][0] = stalls[i + 1] - stalls[i];
				diff[i][1] = i;
			}
			Arrays.sort( diff, new Comparator<int[]>()
			{
				public int compare( int[] d1, int[] d2 )
				{
					return d2[0] - d1[0];
				}
			} );
			// for ( int[] d : diff )
			// System.out.println( Arrays.toString( d ) );
			int[][] breaks = new int[m][2];
			for ( int i = 0; i < m; i++ )
				breaks[i] = diff[i];
			Arrays.sort( breaks, new Comparator<int[]>()
			{
				public int compare( int[] b1, int[] b2 )
				{
					return b1[1] - b2[1];
				}
			} );

			// for ( int[] b : breaks )
			// System.out.println( Arrays.toString( b ) );

			for ( int i = 0; i < breaks.length - 1; i++ )
			{
				if ( test )
					System.out.println( stalls[breaks[i + 1][1]] - stalls[breaks[i][1] + 1] + 1 );
				count += stalls[breaks[i + 1][1]] - stalls[breaks[i][1] + 1] + 1;
			}
			// end
			count += stalls[stalls.length - 1] - stalls[breaks[breaks.length - 1][1] + 1] + 1;
			// head
			count += stalls[breaks[0][1]] - stalls[0] + 1;
		}
		else
			count = stalls[stalls.length - 1] - stalls[0] + 1;
		out.println( count );
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
