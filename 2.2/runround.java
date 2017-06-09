
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
import java.util.StringTokenizer;

public class runround
{

	static String filename = "runround";

	static boolean test = true;

	void run() throws IOException
	{

		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		long n = Integer.valueOf( st.nextToken() );
		n++;
		while ( !round( toArray( n ) ) )
			n++;
		out.println( n );

	}

	int[] toArray( long n )
	{
		String s = String.valueOf( n );
		int[] k = new int[s.length()];
		for ( int i = 0; i < s.length(); i++ )
			k[i] = s.charAt( i ) - '0';
		return k;
	}

	boolean round( int[] k )
	{
		int m = 0, next = ( m + k[m % k.length] ) % k.length;
		boolean[] visit = new boolean[k.length];
		visit[0] = true;
		while ( !visit[next] )
		{
			visit[next] = true;
			m = k[next];
			next = ( m + k[m % k.length] ) % k.length;

		}
		for ( boolean f : visit )
			if ( !f )
				return false;
		return true;
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
