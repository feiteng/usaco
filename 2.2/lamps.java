
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: lamps
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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class lamps
{

	static String filename = "lamps";

	static boolean test = false;

	void run() throws IOException
	{

		Set<String> set = new HashSet<>();
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() );
		st = new StringTokenizer( reader.readLine() );
		int c = Integer.valueOf( st.nextToken() );
		c = Math.min( 4, c );
		st = new StringTokenizer( reader.readLine() );
		int[] on = new int[n], off = new int[n];
		while ( st.hasMoreTokens() )
		{
			int l = Integer.valueOf( st.nextToken() );
			if ( l != -1 )
				on[l - 1] = 1;
		}

		st = new StringTokenizer( reader.readLine() );
		while ( st.hasMoreTokens() )
		{
			int l = Integer.valueOf( st.nextToken() );
			if ( l != -1 )
				off[l - 1] = 1;
		}

		for ( int i = 0; i <= 16; i++ )
		{
			if ( Integer.bitCount( i ) > c )
				continue;
			int[] lamps = generateLamp( n, i );
			if ( compare( on, off, lamps ) )
				set.add( arrToString( lamps ) );
		}
		List<String> list = new ArrayList<>( set );
		Collections.sort( list );
		if ( list.size() > 0 )
			for ( String s : list )
				out.println( s );
		else
			out.println( "IMPOSSIBLE" );
	}

	String arrToString( int[] m )
	{
		String s = "";
		for ( int k : m )
			s += "" + k;
		return s;
	}

	boolean compare( int[] on, int[] off, int[] lamps )
	{
		for ( int i = 0; i < lamps.length; i++ )
		{
			if ( on[i] == 1 && lamps[i] != 1 )
				return false;
			if ( off[i] == 1 && lamps[i] != 0 )
				return false;
		}
		return true;
	}

	int[] generateLamp( int n, int m )
	{
		int[] k = new int[n];
		Arrays.fill( k, 1 );
		int[] ops = ops( m );
		for ( int i = 0; i < ops.length; i++ )
		{
			if ( ops[i] > 0 )
				op( i, k );
		}
		return k;
	}

	void op( int i, int[] lamps )
	{
		if ( i == 0 )
		{
			for ( int j = 0; j < lamps.length; j++ )
				lamps[j] = 1 - lamps[j];
		}
		if ( i == 1 )
		{
			for ( int j = 0; j < lamps.length; j += 2 )
				lamps[j] = 1 - lamps[j];
		}
		if ( i == 2 )
		{
			for ( int j = 1; j < lamps.length; j += 2 )
				lamps[j] = 1 - lamps[j];
		}
		if ( i == 3 )
		{
			for ( int j = 0; j < lamps.length; j += 3 )
				lamps[j] = 1 - lamps[j];
		}

	}

	int[] ops( int i )
	{
		int[] operation = new int[4];
		for ( int k = 3; k >= 0; k-- )
		{
			operation[k] = i % 2;
			i /= 2;
		}
		return operation;
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
		new lamps().run();

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
