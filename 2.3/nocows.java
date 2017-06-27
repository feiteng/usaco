
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: nocows
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class nocows
{

	static String filename = "nocows";

	static boolean test = true;

	void run() throws IOException
	{

		StringTokenizer st;

		st = new StringTokenizer( reader.readLine() );

		// 3 <= n <= 200 1 < k < 100
		int n = Integer.valueOf( st.nextToken() ), k = Integer.valueOf( st.nextToken() );

		int leaves = 1, lvl = 1, each = 1;
		while ( lvl < k && leaves + each * 2 < n )
		{
			leaves += each * 2;
			each *= 2;
			lvl++;
		}
		// now leaves -> number of possible nodes
		int rest = n - leaves;
		out.println( choose( each, rest ) % 9901 );

	}

	int choose( int n, int k )
	{
		return fact( n ) / fact( k ) / fact( n - k );
	}

	int fact( int n )
	{
		if ( n <= 1 )
			return 1;

		return n * fact( n - 1 );
	}

	public static void main( String[] args ) throws IOException
	{

		long t = System.currentTimeMillis();
		setup();
		new nocows().run();

		System.out.printf( "Run time... %s ms\n", System.currentTimeMillis() - t );

		out.close(); // close the output file

	}

	static void setup() throws IOException
	{

		if ( test )
		{
			String f = "C:/Users/Feiteng/Desktop/java/usaco/2.3/prefix.txt";
			// reader = new BufferedReader( new FileReader( f ) );
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
