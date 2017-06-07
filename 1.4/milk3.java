
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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class milk3
{

	static String filename = "milk3";

	static boolean test = false;

	static void run() throws IOException
	{
		StringTokenizer st;

		int a, b, c;
		if ( test )
		{
			a = 2;
			b = 5;
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
		List<Integer> iList = new ArrayList<>();
		for ( int k : set )
			iList.add( k );
		Collections.sort( iList );
		List<String> sList = new ArrayList<>();
		for ( int k : iList )
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
		if ( visited[a][b][c] || a < 0 || b < 0 || c < 0 )
			return;
		visited[a][b][c] = true;
		if ( a == 0 ) // add c
			set.add( c );
		int from, to, toCap, tofill, pourout;
		if ( a != 0 )
		{ // pour a into b and c
			from = a;
			if ( b != c2 )
			{
				toCap = c2;
				to = b;
				tofill = toCap - to < from ? toCap - to : from;
				pourout = from - tofill;
				dfs( a - tofill, b + tofill, c );
			}
			if ( c != c3 )
			{
				toCap = c3;
				to = c;

				tofill = toCap - to < from ? toCap - to : from;
				pourout = from - tofill;

				dfs( a - tofill, b, c + tofill );
			}

		}
		if ( b != 0 )
		{// pour b into a and c
			from = b;
			if ( a != c1 )
			{
				toCap = c1;
				to = a;
				tofill = toCap - to < from ? toCap - to : from;
				pourout = from - tofill;
				dfs( a + tofill, b - tofill, c );
			}
			if ( c != c3 )
			{
				toCap = c3;
				to = c;

				tofill = toCap - to < from ? toCap - to : from;
				pourout = from - tofill;

				dfs( a, b - tofill, c + tofill );
			}

		}
		if ( c != 0 )
		{// pour c into a and b
			from = c;
			if ( a != c1 )
			{
				toCap = c1;
				to = a;
				tofill = toCap - to < from ? toCap - to : from;
				pourout = from - tofill;
				dfs( a + tofill, b, c - tofill );
			}
			if ( b != c2 )
			{
				toCap = c2;
				to = b;

				tofill = toCap - to < from ? toCap - to : from;
				pourout = from - tofill;

				dfs( a, b + tofill, c - tofill );
			}
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
