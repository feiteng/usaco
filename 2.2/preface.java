
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: preface
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class preface
{

	static String filename = "preface";

	static boolean test = false;
	static Map<Integer, String> map = new HashMap<>();

	static String intToRoman( int num )
	{
		String string = "";

		int t = 0;
		while ( num > 0 )
		{
			string = toRom( num % 10, t, map ) + string;
			num /= 10;
			t++;
		}
		return string;
	}

	static String toRom( int n, int pow, Map<Integer, String> map )
	{
		String string = "", rest = "";
		int p = (int) ( Math.pow( 10, pow ) );

		if ( n == 4 )
			string = map.get( p ) + map.get( 5 * p );
		else if ( n == 5 )
			string = map.get( 5 * p );
		else if ( n == 9 )
			string = map.get( p ) + map.get( 10 * p );

		else if ( n > 5 )
		{
			string = map.get( 5 * p );
			for ( int k = 0; k < n - 5; k++ )
				rest += map.get( p );
		}
		else
			for ( int k = 0; k < n; k++ )
				rest += map.get( p );
		return string + rest;
	}

	static void romanCount( String string, Map<Character, Integer> map )
	{
		for ( char c : string.toCharArray() )
		{
			if ( !map.containsKey( c ) )
				map.put( c, 0 );
			map.put( c, map.get( c ) + 1 );
		}
	}

	static Map<Character, Integer> mcount = new HashMap<>();

	static void run() throws IOException
	{
		map.put( 1, "I" );
		map.put( 5, "V" );
		map.put( 10, "X" );
		map.put( 50, "L" );
		map.put( 100, "C" );
		map.put( 500, "D" );
		map.put( 1000, "M" );
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() );
		for ( int i = 1; i <= n; i++ )
		{
			romanCount( intToRoman( i ), mcount );
		}
		int[] k = { 1, 5, 10, 50, 100, 500, 1000 };
		for ( int m : k )
		{
			char c = map.get( m ).toCharArray()[0];
			if ( mcount.containsKey( c ) )
				out.printf( "%s %d\n", map.get( m ), mcount.get( c ) );
		}

	}

	static List<List<Integer>> list = new ArrayList<>();
	static Set<Integer> set = new HashSet<>();

	static void generateCombs( List<Integer> combs, int[] pos, int currentPos, int[] v, int[][] scoop )
	{
		if ( checkV( v, combs, scoop ) )
		{
			list.add( new ArrayList<>( combs ) );
			return;
		}
		if ( currentPos >= pos.length )
			return;
		for ( int i = currentPos; i < pos.length; i++ )
		{
			if ( set.contains( pos[i] ) )
				continue;
			combs.add( pos[i] );
			set.add( pos[i] );
			generateCombs( combs, pos, i + 1, v, scoop );
			combs.remove( combs.size() - 1 );
			set.remove( pos[i] );
		}

	}

	static boolean checkV( int[] v, List<Integer> combs, int[][] scoop )
	{
		int[] sum = new int[v.length];
		for ( int j = 0; j < v.length; j++ )
		{
			for ( int i : combs )
				sum[j] += scoop[i][j];
			if ( sum[j] < v[j] )
				return false;
		}
		return true;

	}

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
