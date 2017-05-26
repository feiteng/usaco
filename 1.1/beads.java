
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: beads
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

public class beads
{
	static BufferedReader f;
	static PrintWriter out;
	static String filename = "beads";

	public static void main( String[] args ) throws IOException
	{
		boolean test = false;
		if ( test )
		{
			f = new BufferedReader( new InputStreamReader( System.in ) );

			out = new PrintWriter( System.out );
		}
		else
		{
			f = new BufferedReader( new FileReader( filename + ".in" ) );

			out = new PrintWriter( new BufferedWriter( new FileWriter( filename + ".out" ) ) );
		}

		beads g = new beads();
		g.run();

		out.close(); // close the output file
	}

	void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( f.readLine() );
		int n = Integer.valueOf( st.nextToken() );
		st = new StringTokenizer( f.readLine() );
		String beads = st.nextToken();
		String beads3 = beads + beads;
		out.println( val( beads3 ) );
	}

	int val( String beads )
	{
		// keeps track of all visited color
		LinkedList<Character> qCharacters = new LinkedList<>(),
				// q last keeps track of last inserted color - no white
				qlast = new LinkedList<>();
		Map<Character, Integer> map = new HashMap<>();
		map.put( 'w', 0 );
		map.put( 'r', 0 );
		map.put( 'b', 0 );
		int runningSum = 0;
		for ( int i = 0; i < beads.length() && sum( map.values() ) < beads.length() / 2; i++ )
		{
			char ch = beads.charAt( i );
			if ( ch == 'w' ) // always add w
			{
				qCharacters.add( 'w' );
				map.put( 'w', map.get( 'w' ) + 1 );
			}
			else
			{
				if ( !qlast.isEmpty() && qlast.getLast() != ch && map.get( ch ) > 0 )
				// e.g. b...r...b, now pop all previous b
				{
					runningSum = Math.max( runningSum, sum( map.values() ) );
					while ( map.get( ch ) > 0 )
					{
						char poll = qCharacters.poll();
						if ( poll != 'w' )
							qlast.poll();
						map.put( poll, map.get( poll ) - 1 );
					}
					qCharacters.add( ch );
					map.put( ch, 1 );
					qlast.add( ch );
				}
				else // no duplicates
				{
					qlast.add( ch );
					qCharacters.add( ch );
					map.put( ch, map.get( ch ) + 1 );
				}
			}

		}
		runningSum = Math.max( runningSum, sum( map.values() ) );

		return runningSum;
	}

	int sum( Iterable<Integer> list )
	{
		int sum = 0;
		for ( int k : list )
			sum += k;
		return sum;
	}

}
