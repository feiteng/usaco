
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

	static boolean test = true;

	static void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int a = Integer.valueOf( st.nextToken() ), b = Integer.valueOf( st.nextToken() ), c = Integer.valueOf( st.nextToken() );
		Set<Integer> list = new HashSet<>();
		int m = 0;
		while ( b > a * m && c > a * m )
		{
			list.add( c - a * m );
			m++;
		}
		int newc = c > b ? c - b : 0;
		b = Math.min( b, c );
		m = 0;
		while ( b > a * m )
		{
			list.add( newc + a * m );
			m++;
		}
		if ( b > a && c > a && b > c - a )
			list.add( a );
		List<Integer> iList = new ArrayList<>( list );
		Collections.sort( iList );
		String string = "";
		List<String> sList = new ArrayList<>();
		for ( int k : iList )
			sList.add( String.valueOf( k ) );
		string = String.join( " ", sList );
		// string += "\n";
		out.println( string );
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
