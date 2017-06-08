
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: frac1
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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class frac1
{

	static String filename = "frac1";

	static boolean test = false;
	static int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113,
			127, 131, 137, 139, 149, 151, 157 };

	static void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() );

		Set<String> set = new HashSet<>();
		set.add( "0/1" );
		for ( int i = 1; i <= n; i++ )
			addValue( set, i );
		List<String> list = new ArrayList<>( set );
		Collections.sort( list, ( a, b ) -> toDouble( a ) > toDouble( b ) ? 1 : -1 );
		for ( String s : list )
			out.println( s );
	}

	static double toDouble( String string )
	{
		if ( string.indexOf( '/' ) < 0 )
			return Double.valueOf( string );
		String s1 = string.substring( 0, string.indexOf( '/' ) ), s2 = string.substring( string.indexOf( '/' ) + 1 );
		return Double.valueOf( s1 ) / Double.valueOf( s2 );
	}

	static void addValue( Set<String> set, int n )
	{
		for ( int i = 1; i <= n; i++ )
			set.add( factorize( i, n ) );
	}

	static String factorize( int a, int b )
	{
		int i = 0;
		while ( i < primes.length && primes[i] <= a && primes[i] <= b )
		{
			while ( a % primes[i] == 0 && b % primes[i] == 0 )
			{
				a /= primes[i];
				b /= primes[i];
			}
			i++;
		}

		return "" + a + "/" + b;
	}

	static void visit( int i, int j, int[][] dirs, boolean[][] visited, Set<int[]> set )
	{
		// bfs to visit all neighbors
		Queue<int[]> neighbours = new LinkedList<>();
		neighbours.add( new int[] { i, j } );
		set.add( new int[] { i, j } );
		visited[i][j] = true;
		while ( !neighbours.isEmpty() )
		{
			int[] pos = neighbours.poll();
			int[][] d = getDirs( dirs[pos[0]][pos[1]] );
			for ( int[] n : d )
				if ( !visited[n[0] + pos[0]][n[1] + pos[1]] )
				{
					neighbours.add( new int[] { pos[0] + n[0], pos[1] + n[1] } );
					set.add( new int[] { pos[0] + n[0], pos[1] + n[1] } );
					visited[pos[0] + n[0]][pos[1] + n[1]] = true;
				}
		}

	}

	static int[][] getDirs( int m )
	{
		String string = toBinaryString( m );// .substring( 28, 32 );
		int[][] allD = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		int[][] dirs = new int[4][2];
		int k = 0;
		for ( int i = 0; i < 4; i++ )
		{
			if ( string.charAt( i ) == '0' )
				dirs[k++] = allD[i];
		}
		return Arrays.copyOf( dirs, k );
	}

	static String toBinaryString( int m )
	{
		String s = "";
		while ( m > 0 )
		{
			s = String.valueOf( m % 2 ) + s;
			m /= 2;
		}
		while ( s.length() < 4 )
			s = "0" + s;
		return s;
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
