
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: combo
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

public class combo
{

	static String filename = "combo";

	static boolean test = false;

	static void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() );
		st = new StringTokenizer( reader.readLine() );
		int a = Integer.valueOf( st.nextToken() ), b = Integer.valueOf( st.nextToken() ), c = Integer.valueOf( st.nextToken() );
		st = new StringTokenizer( reader.readLine() );
		int d = Integer.valueOf( st.nextToken() ), e = Integer.valueOf( st.nextToken() ), f = Integer.valueOf( st.nextToken() );

		Set<Integer> s1 = neighbour( n, a ),
				s2 = neighbour( n, b ),
				s3 = neighbour( n, c ),
				s4 = neighbour( n, d ),
				s5 = neighbour( n, e ),
				s6 = neighbour( n, f );
		int count = s1.size() * s2.size() * s3.size() + s4.size() * s5.size() * s6.size();
		s1.retainAll( s4 );
		s2.retainAll( s5 );
		s3.retainAll( s6 );

		int intersection = s1.size() * s2.size() * s3.size();

		out.println( count - intersection );
	}

	static Set<Integer> neighbour( int n, int a )
	{
		Set<Integer> set = new HashSet<>();
		int a0 = a - 1 > 0 ? a - 1 : n, a01 = a0 - 1 > 0 ? a0 - 1 : n, a1 = a + 1 > n ? 1 : a + 1, a2 = a1 + 1 > n ? 1 : a1 + 1;
		set.add( a );
		set.add( a0 );
		set.add( a01 );
		set.add( a1 );
		set.add( a2 );
		return set;

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
