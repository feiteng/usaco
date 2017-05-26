
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: crypt1
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

public class crypt1
{

	static BufferedReader reader;
	static PrintWriter out;
	static String filename = "crypt1";
	static boolean test = false;

	public static void main( String[] args ) throws IOException
	{

		long t = System.currentTimeMillis();
		crypt1 g = new crypt1();
		g.setup();
		g.run();

		if ( test )
			System.out.printf( "Run time... %s ms\n", System.currentTimeMillis() - t );

		out.close(); // close the output file

	}

	void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() );
		st = new StringTokenizer( reader.readLine() );
		int[] vals = new int[n];
		for ( int i = 0; i < n; i++ )
			vals[i] = Integer.valueOf( st.nextToken() );
		Set<Integer> set = new HashSet<>();
		for ( int k : vals )
			set.add( k );
		List<Integer> abc = new ArrayList<>(), de = new ArrayList<>();
		generateVal( set, 0, 2, 0, de );
		generateVal( set, 0, 3, 0, abc );
		int count = 0;
		for ( int i = 0; i < abc.size(); i++ )
		{
			for ( int j = 0; j < de.size(); j++ )
			{
				int vabc = abc.get( i ), vde = de.get( j );
				if ( vabc == 222 && vde == 22 )
					System.err.println();
				int e = vde % 10, d = vde / 10;
				if ( vabc * e >= 100 && vabc * e <= 999 )
					if ( vabc * d >= 100 && vabc * d <= 999 )
					{
						int mult = vabc * vde;
						if ( allIn( mult, set ) && allIn( vabc * e, set ) && allIn( vabc * d, set ) )
							count++;
					}
			}
		}
		out.println( count );
	}

	boolean allIn( int mul, Set<Integer> set )
	{
		for ( char c : String.valueOf( mul ).toCharArray() )
			if ( !set.contains( c - '0' ) )
				return false;
		return true;

	}

	void generateVal( Set<Integer> set, int pos, int total, int val, List<Integer> list )
	{
		if ( pos == total )
		{
			list.add( val );
			return;
		}
		for ( int k : set )
			generateVal( set, pos + 1, total, val * 10 + k, list );
	}

	void setup() throws IOException
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
