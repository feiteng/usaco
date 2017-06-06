
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: milk3
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

public class milk3
{

	static String filename = "milk3";

	static boolean test = true;

	static void run() throws IOException
	{
		StringTokenizer st;

		int a, b, c;
		if ( test )
		{
			a = 8;
			b = 9;
			c = 10;
		}
		else
		{
			st = new StringTokenizer( reader.readLine() );
			a = Integer.valueOf( st.nextToken() );
			b = Integer.valueOf( st.nextToken() );
			c = Integer.valueOf( st.nextToken() );
		}
		c1 = a;
		c2 = b;
		c3 = c;
		visited = new boolean[c1 + 1][c2 + 1][c3 + 1];
		dfs( 0, 0, c );
		String string = "";
		List<String> sList = new ArrayList<>();
		for ( int k : set )
			sList.add( String.valueOf( k ) );
		string = String.join( " ", sList );
		// string += "\n";
		out.println( string );
	}

	static Set<Integer> set = new HashSet<>();
	static int c1, c2, c3;
	static boolean[][][] visited;

	static void dfs( int a, int b, int c )
	{
		if ( visited[a][b][c] )
			return;
		visited[a][b][c] = true;
		if ( a == 0 ) // add c
		{
			set.add( c );

			// c -> a
			if ( c1 <= c )
				dfs( c1, b, c - c1 );
			else
				dfs( c, b, 0 );
			// c -> b
			if ( b + c <= c2 )
				dfs( a, b + c, 0 );
			else
				// b + c > c2 -> c > c2-b
				dfs( a, c2, c - ( c2 - b ) );
		}
		else // a not zero, pour a into b and c
		{
			if ( b + a <= c2 )
				dfs( 0, b + a, c );
			else // b + a > c2 -> a > c2 - b
				dfs( a - ( c2 - b ), c2, c );
			if ( c + a <= c3 )
				dfs( 0, b, c + a );
			else
				dfs( a - ( c3 - c ), b, c3 );
		}
	}

	static BufferedReader reader;
	static PrintWriter out;

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
}
