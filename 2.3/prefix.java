
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
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class prefix
{

	static String filename = "prefix";

	static boolean test = false;

	Set<String> primitive = new HashSet<>();
	String string;

	int len = 0;
	Trie _root = new Trie();

	void run() throws IOException
	{

		for ( String s : primitive )
			_root.insert( s );

		readData();

		// trie is a lot slower.., due to recursive thinking

		bruteForce();

	}

	void bruteForce()
	{
		// idea from https://github.com/lklein/usaco-training/blob/master/usaco/prefix/prefix.java

		boolean[] vist = new boolean[string.length() + 1];
		for ( String s : primitive )
		{
			if ( string.substring( 0, s.length() ).equals( s ) )
				vist[s.length()] = true;
		}
		for ( int i = 0; i < vist.length; i++ )
		{
			if ( vist[i] )
			{
				for ( String s : primitive )
				{
					if ( i + s.length() <= string.length() &&
							string.substring( i, i + s.length() ).equals( s ) )
						vist[i + s.length()] = true;
				}
			}
		}
		int m = 0;
		for ( int i = 0; i < vist.length; i++ )
			if ( vist[i] )
				m = i;
		out.println( m );
	}

	void readData() throws IOException
	{
		StringTokenizer st;

		st = new StringTokenizer( reader.readLine() );
		boolean flag = true;
		while ( flag )
		{
			while ( st.hasMoreTokens() )
			{
				String nextToken = st.nextToken();
				if ( nextToken.equals( "." ) )
				{
					flag = false;
					break;
				}
				primitive.add( nextToken );
			}
			if ( flag )
				st = new StringTokenizer( reader.readLine() );
		}
		// now read string
		StringBuilder sequence = new StringBuilder();

		String line = reader.readLine();
		while ( line != null )
		{
			sequence.append( line );
			line = reader.readLine();
		}

		string = sequence.toString();

	}

	void trieMethod()
	{

		findLen( _root, string, 0 );
		out.println( len );
	}

	void findLen( Trie root, String sequence, int pos )
	{
		for ( int i = pos; i < sequence.length(); i++ )
		{
			if ( root.child[sequence.charAt( i ) - 'A'] == null )
			{
				len = Math.max( pos, len );
				return;
			}
			if ( root.child[sequence.charAt( i ) - 'A'].string != null )
				findLen( _root, sequence, i + 1 ); // either start with original tree
			root = root.child[sequence.charAt( i ) - 'A']; // or continue finding on current tree

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
			String f = "C:/Users/Feiteng/Desktop/java/usaco/2.3/prefix.txt";
			reader = new BufferedReader( new FileReader( f ) );
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
