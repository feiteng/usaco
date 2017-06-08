
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: holstein
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class holstein
{

	static String filename = "holstein";

	static boolean test = false;

	static void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() );
		int[] v = new int[n];
		st = new StringTokenizer( reader.readLine() );
		for ( int i = 0; i < n; i++ )
			v[i] = Integer.valueOf( st.nextToken() );

		st = new StringTokenizer( reader.readLine() );
		int g = Integer.valueOf( st.nextToken() );
		int[] picks = new int[g];
		int[][] scoop = new int[g][n];
		for ( int i = 0; i < g; i++ )
		{
			st = new StringTokenizer( reader.readLine() );
			for ( int j = 0; j < n; j++ )
				scoop[i][j] = Integer.valueOf( st.nextToken() );
			picks[i] = i;
		}

		generateCombs( new ArrayList<>(), picks, 0, v, scoop );
		int minsize = list.get( 0 ).size(), minIndex = Collections.min( list.get( 0 ) );
		List<Integer> rList = new ArrayList<>( list.get( 0 ) );

		for ( List<Integer> l : list )
		{
			if ( l.size() < minsize )
			{
				minsize = l.size();
				rList = l;
			}
			else if ( l.size() == minsize && minIndex > Collections.min( l ) )
			{
				minIndex = Collections.min( l );
				rList = l;
			}
		}

		String string = "" + rList.size();
		for ( int k : rList )
			string += " " + ( k + 1 );

		out.println( string );

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
