
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: hamming
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class hamming
{

	static String filename = "hamming";

	static boolean test = false;

	static void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() ), blength = Integer.valueOf( st.nextToken() );
		hammingDistance = Integer.valueOf( st.nextToken() );
		Integer.bitCount( 0 );
		int ceil = 1 << blength;
		int[] nums = new int[ceil];
		for ( int i = 0; i < nums.length; i++ )
			nums[i] = i;
		findNum( new ArrayList<>(), n, nums, 0 );

		// for ( List<Integer> list : tList )
		// System.out.println( list );
		String string = "";

		int i = 0;

		for ( int k : tList )
		{

			string += "" + k + " ";

			if ( i == 9 )
			{
				string = string.substring( 0, string.length() - 1 ) + "\n";
				i = -1;
			}
			i++;

		}
		out.println( string.substring( 0, string.length() - 1 ) );

	}

	static List<Integer> tList = new ArrayList<>();
	static int hammingDistance;
	static boolean flag = false;

	static void findNum( List<Integer> list, int n, int[] nums, int currentPos )
	{
		if ( n == 0 && checkHamming( list ) )
		{
			// System.out.println( list );
			tList = new ArrayList<>( list );
			flag = true;
			return;
		}
		for ( int i = currentPos; i < nums.length; i++ )
		{

			list.add( nums[i] );
			if ( checkHamming( list ) && !flag )
				findNum( list, n - 1, nums, i + 1 );
			list.remove( list.size() - 1 );
		}
	}

	static boolean checkHamming( List<Integer> list )
	{
		int k = list.get( list.size() - 1 );
		for ( int i = 0; i < list.size() - 1; i++ )
		{
			// System.out.println( Integer.bitCount( k ^ list.get( i ) ) );
			if ( Integer.bitCount( k ^ list.get( i ) ) < hammingDistance )
				return false;
		}
		return true;
	}

	static List<List<Integer>> list = new ArrayList<>();
	static Set<Integer> set = new HashSet<>();

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
