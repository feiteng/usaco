
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: runround
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class runround
{

	static String filename = "runround";

	static boolean test = false;

	void run() throws IOException
	{

		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		long n = Integer.valueOf( st.nextToken() );
		n++;
		while ( !round( toArray( n ) ) )
		{
			// System.out.println( n );
			n++;
		}
		out.println( n );

	}

	int[] toArray( long n )
	{
		String s = String.valueOf( n );
		int[] k = new int[s.length()];
		Set<Integer> set = new HashSet<>();
		for ( int i = 0; i < s.length(); i++ )
		{
			k[i] = s.charAt( i ) - '0';
			if ( !set.contains( k[i] ) )
				set.add( k[i] );
			else
				return new int[] { 0 };
		}
		return k;
	}

	boolean round( int[] k )
	{
		Set<Integer> set = new HashSet<>();
		for ( int m : k )
			if ( m == 0 )
				return false;
		int m = 0, next = ( k[m] ) % k.length;
		boolean[] visit = new boolean[k.length];
		// visit[0] = true;
		// set.add( 0 );
		while ( !visit[next] )
		{
			visit[next] = true;
			set.add( next );
			next = ( k[next] + next ) % k.length;

		}
		return set.size() == k.length;

	}

	public static void main( String[] args ) throws IOException
	{

		long t = System.currentTimeMillis();
		setup();
		new runround().run();

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
