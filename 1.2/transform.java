
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: transform
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class transform
{

	static BufferedReader reader;
	static PrintWriter out;
	static String filename = "transform";
	static boolean test = false;

	public static void main( String[] args ) throws IOException
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

		transform g = new transform();
		g.run();

		out.close(); // close the output file
	}

	void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() );
		String[] g = new String[n], f = new String[n];
		for ( int i = 0; i < n; i++ )
			g[i] = reader.readLine();
		for ( int i = 0; i < n; i++ )
			f[i] = reader.readLine();

		if ( equals( op1( g ), f ) )
		{
			out.println( 1 );
			return;
		}
		if ( equals( op2( g ), f ) )
		{
			out.println( 2 );
			return;
		}
		if ( equals( op3( g ), f ) )
		{
			out.println( 3 );
			return;
		}
		if ( equals( op4( g ), f ) )
		{
			out.println( 4 );
			return;
		}
		if ( equals( op51( g ), f ) )
		{
			out.println( 5 );
			return;
		}
		if ( equals( op52( g ), f ) )
		{
			out.println( 5 );
			return;
		}
		if ( equals( op53( g ), f ) )
		{
			out.println( 5 );
			return;
		}
		if ( equals( op6( g ), f ) )
		{
			out.println( 6 );
			return;
		}
		out.println( 7 );

	}

	String[] op1( String[] g )
	{
		String[] f = new String[g.length];
		for ( int i = 0; i < g[0].length(); i++ )
		{
			StringBuilder sb = new StringBuilder();
			for ( int j = 0; j < g.length; j++ )
				sb.append( g[j].charAt( i ) );
			f[i] = sb.reverse().toString();
		}

		return f;
	}

	String[] op2( String[] g )
	{
		return op1( op1( g ) );
	}

	String[] op3( String[] g )
	{
		return op1( op2( g ) );
	}

	String[] op4( String[] g )
	{
		String[] reflect = new String[g.length];
		for ( int i = 0; i < g.length; i++ )
			reflect[i] = new StringBuilder( g[i] ).reverse().toString();
		return reflect;
	}

	String[] op51( String[] g )
	{
		return op4( op1( g ) );
	}

	String[] op52( String[] g )
	{
		return op4( op2( g ) );
	}

	String[] op53( String[] g )
	{
		return op4( op3( g ) );
	}

	String[] op6( String[] g )
	{
		return g;
	}

	boolean equals( String[] g, String[] f )
	{
		if ( g.length != f.length )
			return false;
		for ( int i = 0; i < g.length; i++ )
			if ( !g[i].equals( f[i] ) )
				return false;
		return true;
	}

}
