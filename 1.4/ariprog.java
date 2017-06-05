
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: ariprog
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ariprog
{

	static String filename = "ariprog";

	static boolean test = true;

	static void run() throws IOException
	{
		StringTokenizer st;
		int n, m;
		if ( !test )
		{
			st = new StringTokenizer( reader.readLine() );
			n = Integer.valueOf( st.nextToken() );
			st = new StringTokenizer( reader.readLine() );
			m = Integer.valueOf( st.nextToken() );
		}
		else
		{
			n = 21;
			m = 200;
		}
		int a, b, p, q, max = 0, sumP = 0;
		int[] sumsq2 = new int[m * m * 2 + 1], sumsq = new int[m * m * 2 + 1];
		for ( p = 0; p <= m; p++ )
		{
			for ( q = 0; q <= m; q++ )
			{
				int calc = p * p + q * q;
				sumsq2[calc] = 1;
				max = Math.max( calc, max );
				// sqSum.add( p * p + q * q );
			}
		}

		for ( int i = 0; i < m * m * 2; i++ )
		{
			if ( sumsq2[i] > 0 )
				sumsq[sumP++] = 1;
		}

		// max = Collections.max( sqSum );
		int arrCount = 0;
		data[] darray = new data[m * m * 2 + 1];
		// List<int[]> list = new ArrayList<>();
		for ( a = 0; a <= sumP; a++ )
		{

			for ( b = 1; b <= sumP; b++ )
			{
				boolean flag = true;
				for ( int k = n - 1; k >= 0; k-- )
				{
					int s = a + k * b;
					if ( s > max || sumsq[s] == 0 )
					{
						flag = false;
						break;
					}
				}
				if ( flag )
					darray[arrCount++] = new data( a, b );
			}
		}

		System.out.printf( "Run time... %s ms\n", System.currentTimeMillis() - t );
		t = System.currentTimeMillis();
		if ( arrCount > 0 )
		{
			Arrays.sort( darray, 0, arrCount );
			for ( int i = 0; i < arrCount; i++ )
				out.println( "" + darray[i].a + " " + darray[i].b );
		}
		else
			out.println( "NONE" );
	}

	static BufferedReader reader;
	static PrintWriter out;
	static long t;

	public static void main( String[] args ) throws IOException
	{

		t = System.currentTimeMillis();
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

class data implements Comparable<data>
{
	int a, b;

	public data( int a, int b )
	{
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo( data d )
	{
		return this.b != d.b ? this.b - d.b : this.a - d.a;
	}

}
