
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: prefix
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

public class prefix
{

	static String filename = "prefix";

	static boolean test = true;

	void run() throws IOException
	{

		Set<String> primitive = new HashSet<>();
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		boolean flag = true;
		while ( flag )
		{
			while ( st.hasMoreTokens() )
			{
				if ( st.nextToken().equals( "." ) )
				{
					flag = false;
					break;
				}
				primitive.add( st.nextToken() );
			}
			if ( flag )
				st = new StringTokenizer( reader.readLine() );
		}
		// now read string
		StringBuilder sequence = new StringBuilder();

		flag = true;
		while ( flag )
		{
			while ( st.hasMoreTokens() )
			{
				sequence.append( st.nextToken() );
			}
			if ( ( st = new StringTokenizer( reader.readLine() ) ) == null )
				break;
		}

		Trie root = new Trie();
		for ( String s : primitive )
			root.insert( s );

		findLen( root, sequence.toString(), 0 );

		out.println( len );
	}

	int len = 0;

	void findLen( Trie root, String sequence, int pos )
	{
		for ( int i = pos; i < sequence.length(); i++ )
		{
			if ( root.child[sequence.charAt( i )] == null )
			{
				len = Math.max( pos, len );
				return;
			}
			if ( root.string != null )
				findLen( root, sequence, i + 1 );
			root = root.child[sequence.charAt( i )];
		}
		len = Math.max( pos, len );
	}

	public static void main( String[] args ) throws IOException
	{

		long t = System.currentTimeMillis();
		setup();
		new prefix().run();

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

class Trie
{
	Trie[] child = new Trie[26];
	String string = null;

	void insert( String string )
	{
		Trie root = this;
		for ( char c : string.toCharArray() )
		{
			if ( root.child[c - 'A'] == null )
				root.child[c - 'A'] = new Trie();
			root = root.child[c - 'A'];
		}
		root.string = string;
	}
}
