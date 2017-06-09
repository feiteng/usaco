
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: subset
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class subset
{

	static String filename = "subset";

	static boolean test = false;

	void run() throws IOException
	{

		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() ), sum = ( n + 1 ) * n / 2, runSum = 1, k = 0;
		if ( ( sum & 1 ) != 0 )
		{
			out.println( 0 );
			return;
		}
		long[] vals = new long[sum + 1];
		vals[0] = 1;
		for ( int i = 1; i <= n; i++ )
		{
			runSum += k;
			// System.out.printf( "%d %d \n", i, runSum );
			for ( int j = runSum - 1; j >= 0; j-- )
				vals[i + j] += vals[j];
			// System.out.println( Arrays.toString( vals ) );
			k++;
		}

		out.println( vals[sum / 2] / 2 );
	}

	public static void main( String[] args ) throws IOException
	{

		long t = System.currentTimeMillis();
		setup();
		// for ( int i = 1; i < 40; i++ )
		new subset().run();

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
