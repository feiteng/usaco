
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: sprime
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class sprime
{

	static String filename = "sprime";

	static boolean test = false;

	static void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() );
		int[] p = { 2, 3, 5, 7 };
		for ( int k : p )
			genNum( k, n );

		for ( int k : list )
			out.println( k );
	}

	static List<Integer> list = new ArrayList<>();

	static void genNum( int k, int n )
	{
		if ( n == 1 )
		{
			if ( isPrime( k ) )
				list.add( k );
			return;
		}
		for ( int i = 1; i < 10; i++ )
		{
			if ( isPrime( k * 10 + i ) )
				genNum( k * 10 + i, n - 1 );
		}

	}

	static boolean isPrime( int k )
	{
		for ( int i = 2; i <= Math.sqrt( k ); i++ )
			if ( k % i == 0 )
				return false;
		return true;
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
