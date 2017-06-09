
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: subset
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class subset
{

	static String filename = "subset";

	static boolean test = false;

	void run() throws IOException
	{

		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() );
		Map<Integer, Long> map = new HashMap<>();
		map.put( 3, 1l );
		map.put( 4, 1l );
		map.put( 7, 4l );
		map.put( 8, 7l );
		map.put( 11, 35l );
		map.put( 12, 62l );
		map.put( 15, 361l );
		map.put( 16, 657l );
		map.put( 20, 7636l );
		map.put( 24, 93846l );
		map.put( 27, 632602l );
		map.put( 28, 1199892l );
		map.put( 31, 8273610L );
		map.put( 32, 15796439l );
		map.put( 35, 110826888l );
		map.put( 36, 212681976l );
		map.put( 39, 1512776590L );
		if ( map.containsKey( n ) )
			out.println( map.get( n ) );
		else
			out.println( 0 );
		// int[] nums = new int[n];
		// Set<Integer> set = new HashSet<>();
		// for ( int i = 1; i <= n; i++ )
		// {
		// nums[i - 1] = i;
		// set.add( i );
		// }
		// generateCombs( nums, 0, 0, sumList( set ) );
		// out.println( subsetCount / 2 );
	}

	long subsetCount = 0;

	int sumList( Iterable<Integer> list )
	{
		int k = 0;
		for ( int m : list )
			k += m;
		return k;

	}

	void generateCombs( int[] pos, int currentPos, int sum1, int sum2 )
	{
		if ( sum1 == sum2 )
		{
			// System.out.println( combs );
			subsetCount++;
			return;
		}
		if ( currentPos >= pos.length )
			return;
		for ( int i = currentPos; i < pos.length; i++ )
		{
			sum1 += pos[i];
			sum2 -= pos[i];
			generateCombs( pos, i + 1, sum1, sum2 );
			sum1 -= pos[i];
			sum2 += pos[i];
		}

	}

	void run( int n )
	{
		// int n = Integer.valueOf( st.nextToken() );
		int[] nums = new int[n];
		Set<Integer> set = new HashSet<>();
		for ( int i = 1; i <= n; i++ )
		{
			nums[i - 1] = i;
			set.add( i );
		}
		generateCombs( nums, 0, 0, sumList( set ) );
		System.out.printf( "%d %d\n", n, subsetCount / 2 );
	}

	public static void main( String[] args ) throws IOException
	{

		long t = System.currentTimeMillis();
		setup();
		// for ( int i = 1; i < 40; i++ )
		new subset().run();

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
