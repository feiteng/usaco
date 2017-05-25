
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: milk2
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class milk2
{

	static BufferedReader f;
	static PrintWriter out;
	static String filename = "milk2";
	static boolean test = true;

	public static void main( String[] args ) throws IOException
	{

		if ( test )
		{
			f = new BufferedReader( new InputStreamReader( System.in ) );

			out = new PrintWriter( System.out );
		}
		else
		{
			f = new BufferedReader( new FileReader( filename + ".in" ) );

			out = new PrintWriter( new BufferedWriter( new FileWriter( filename + ".out" ) ) );
		}

		milk2 g = new milk2();
		g.run();

		out.close(); // close the output file
	}

	void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( f.readLine() );
		int n = Integer.valueOf( st.nextToken() );
		int[][] times = new int[n][2];
		for ( int i = 0; i < n; i++ )
		{
			st = new StringTokenizer( f.readLine() );
			times[i][0] = Integer.valueOf( st.nextToken() );
			times[i][1] = Integer.valueOf( st.nextToken() );
		}

		int[] v = val( times );
		out.printf( "%d %d\n", v[0], v[1] );
	}

	int[] val( int[][] times )
	{
		List<Integer[]> list = new ArrayList<>();
		int[] ret = new int[2];
		for ( int[] t : times )
			list.add( new Integer[] { t[0], t[1] } );
		Collections.sort( list, ( a, b ) -> ( a[1] == b[1] ? a[0] - b[0] : ( b[1] - b[0] ) - ( a[1] - a[0] ) ) );
		Map<Integer, Integer> map = new HashMap<>();
		map.put( list.get( 0 )[0], list.get( 0 )[1] );
		for ( Integer[] v : list )
			updateMap( v[0], v[1], map );
		//
		List<Integer> list2 = new ArrayList<>( map.keySet() );
		Collections.sort( list2 );
		ret[0] = map.get( list2.get( 0 ) ) - list2.get( 0 );
		// ret[1] = list2.get( 1 ) - map.get( list2.get( 0 ) );
		for ( int k = 1; k < list2.size(); k++ )
		{
			ret[0] = Math.max( map.get( list2.get( k ) ) - list2.get( k ), ret[0] );
			ret[1] = Math.max( ret[1], list2.get( k ) - map.get( list2.get( k - 1 ) ) );
		}
		if ( test )
			System.out.println( map );
		return ret;

	}

	void updateMap( int v0, int v1, Map<Integer, Integer> map )
	{
		Map<Integer, Integer> mapCopy = new HashMap<>( map );
		for ( int k : map.keySet() )
		{
			int k0 = k, k1 = map.get( k0 );
			if ( k0 == v0 && k1 == v1 )
				continue;
			if ( k0 <= v0 && k1 >= v1 ) // inclusion
				continue;
			if ( k0 >= v0 && k1 <= v1 ) // bigger interval
			{
				mapCopy.put( v0, v1 );
				mapCopy.remove( k0 );
				// itor.remove();
			}
			if ( k1 < v0 || k0 > v1 ) // no intersection
				mapCopy.put( v0, v1 );
			else // partial
			{
				if ( k0 == Math.min( k0, v0 ) )
					mapCopy.put( k0, Math.max( k1, v1 ) );
				else
				{
					mapCopy.remove( k0 );
					// itor.remove();
					mapCopy.put( v0, Math.max( k1, v1 ) );
					v1 = Math.max( k1, v1 );
				}
			}

		}
		map.putAll( mapCopy );

	}

	int sum( Iterable<Integer> list )
	{
		int sum = 0;
		for ( int k : list )
			sum += k;
		return sum;
	}

}
