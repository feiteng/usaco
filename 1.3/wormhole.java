
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: wormhole
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

public class wormhole
{

	static String filename = "wormhole";

	static boolean test = false;

	static int[] immediateRight;

	static void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int n = Integer.valueOf( st.nextToken() );
		int[][] pos = new int[n + 1][2];
		immediateRight = new int[n + 1];
		for ( int i = 1; i <= n; i++ )
		{
			st = new StringTokenizer( reader.readLine() );
			int u = Integer.valueOf( st.nextToken() ), v = Integer.valueOf( st.nextToken() );
			pos[i][0] = u;
			pos[i][1] = v;
		}
		for ( int i = 1; i <= n; i++ )
		{
			for ( int j = 1; j <= n; j++ )
			{
				if ( pos[j][1] == pos[i][1] && pos[j][0] > pos[i][0] )
					if ( immediateRight[i] == 0 || pos[j][0] - pos[i][0] < pos[immediateRight[i]][0] - pos[i][0] )
						immediateRight[i] = j;
			}
		}
		System.out.println( Arrays.toString( immediateRight ) );
		int[] partner = new int[n + 1];

		out.println( solve( partner ) );

	}

	static boolean thereExistsCycle( int[] partner )
	{
		// return false;
		System.out.println( Arrays.toString( partner ) );
		for ( int i = 1; i < partner.length; i++ )
		{
			int pos = i;
			for ( int j = 1; j < partner.length; j++ )
				pos = immediateRight[partner[pos]];
			if ( pos != 0 )
				return true;
		}
		return false;
	}

	static int solve( int[] partner )
	{
		int i = 1, total = 0;
		for ( i = 1; i < partner.length; i++ )
		{
			if ( partner[i] == 0 )
				break;
		}
		if ( i == partner.length )
		{
			if ( thereExistsCycle( partner ) )
				return 1;
			return 0;
		}
		for ( int j = i + 1; j < partner.length; j++ )
		{
			if ( partner[j] == 0 )
			{
				partner[i] = j;
				partner[j] = i;
				total += solve( partner );
				partner[i] = partner[j] = 0;
			}

		}
		return total;
	}

	static BufferedReader reader;
	static PrintWriter out;

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
}
