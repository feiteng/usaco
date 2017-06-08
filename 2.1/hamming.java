
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
		int n = Integer.valueOf( st.nextToken() ), blength = Integer.valueOf( st.nextToken() ),
				hammingDistance = Integer.valueOf( st.nextToken() );
		Integer.bitCount( 0 );
		int ceil = 1 << blength;
		int[] nums = new int[ceil];
		for ( int i = 0; i < nums.length; i++ )
			nums[i] = i;
		out.println();

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
