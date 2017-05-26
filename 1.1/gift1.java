
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: gift1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class gift1
{
	static BufferedReader f;
	static PrintWriter out;
	static String filename = "gift1";

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

		gift1 g = new gift1();
		g.run();

		out.close(); // close the output file
	}

	void run() throws NumberFormatException, IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( f.readLine() );
		int np = Integer.valueOf( st.nextToken() );
		Map<String, Integer> map = new HashMap<>();
		String[] names = new String[np];
		int[] vals = new int[np];
		for ( int i = 0; i < np; i++ )
		{
			StringTokenizer st2 = new StringTokenizer( f.readLine() );
			String name = st2.nextToken();
			map.put( name, i );
			names[i] = name;
		}
		for ( int i = 0; i < np; i++ )
		{
			StringTokenizer st2 = new StringTokenizer( f.readLine() );
			String name = st2.nextToken();
			st = new StringTokenizer( f.readLine() );
			int amount = Integer.valueOf( st.nextToken() ),
					split = Integer.valueOf( st.nextToken() ),
					left = split == 0 ? 0 : amount % split;
			int pos = map.get( name );
			vals[pos] += -amount + left;
			// map.put( name, map.get( name ) - amount + left );
			for ( int k = 0; k < split; k++ )
			{
				StringTokenizer st3 = new StringTokenizer( f.readLine() );
				String sname = st3.nextToken();
				int sPos = map.get( sname );
				vals[sPos] += amount / split;
			}
		}
		for ( int i = 0; i < np; i++ )
			out.printf( "%s %d\n", names[i], vals[i] );
	}

}
