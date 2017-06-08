
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: sort3
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

public class sort3
{

	static String filename = "sort3";

	static boolean test = false;

	static void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() ), c1 = 0, c2 = 0, c3 = 0;
		int[] a = new int[n];
		for ( int i = 0; i < n; i++ )
		{
			st = new StringTokenizer( reader.readLine() );
			a[i] = Integer.valueOf( st.nextToken() );
			if ( a[i] == 1 )
				c1++;
			else if ( a[i] == 2 )
				c2++;
			else
				c3++;
		}
		System.out.println( Arrays.toString( a ) );

		int j = c1 + c2 - 1, l = n - 1, k = 0;
		for ( int i = 0; i < c1; i++ )
		{
			if ( a[i] == 1 )
				continue;
			while ( j >= c1 && a[j] != 1 )
				j--;
			while ( l >= c1 + c2 && a[l] != 1 )
				l--;
			// swap 1 and 2
			if ( j >= c1 && a[i] == 2 )
			{
				// swap with j
				System.out.printf( "%d %d \n", i + 1, j + 1 );
				a[j] = 2;
				j--;
				a[i] = 1;
				k++;

			}
			// swap 1 and 3
			if ( l >= c1 + c2 && a[i] == 3 )
			{
				System.out.printf( "%d %d \n", i + 1, l + 1 );
				a[l] = 3;
				l--;
				a[i] = 1;
				k++;
			}
			// sub optimal case
			// no more space for 3, so swap into 2
			if ( l < c1 + c2 && a[i] == 3 )
			{
				System.out.printf( "%d %d \n", i + 1, j + 1 );
				a[j] = a[i];
				j--;
				a[i] = 1;
				k++;
			}
			// no more space for 2, so swap into 3
			if ( j < c1 && a[i] == 2 )
			{
				a[l] = a[i];
				l--;
				a[i] = 1;
				k++;
			}
		}
		System.out.println( Arrays.toString( a ) );
		j = n - 1;
		// now 1 are in place, swap 2 and 3
		for ( int i = c1; i < c1 + c2; i++ )
		{

			if ( a[i] == 2 )
				continue;
			while ( a[j] != 2 )
				j--;
			System.out.printf( "%d %d \n", i + 1, j + 1 );
			a[j] = a[i];
			j--;
			a[i] = 2;
			k++;

		}
		System.out.println( Arrays.toString( a ) );
		System.out.println( c1 );
		System.out.println( c2 );
		System.out.println( c3 );
		out.println( k );

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
