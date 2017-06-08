
/*-
 ID: feiteng li
 LANG: JAVA
 TASK: castle
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class castle
{

	static String filename = "castle";

	static boolean test = false;

	static void run() throws IOException
	{
		StringTokenizer st;
		st = new StringTokenizer( reader.readLine() );
		int m = Integer.valueOf( st.nextToken() ), n = Integer.valueOf( st.nextToken() );
		int[][] dirs = new int[n][m];
		boolean[][] visited = new boolean[n][m];
		Set<Set<int[]>> posSet = new HashSet<>();
		Set[][] setM = new Set[n][m];
		int[][] setMre = new int[n][m];
		for ( int i = 0; i < n; i++ )
		{
			st = new StringTokenizer( reader.readLine() );
			for ( int j = 0; j < m; j++ )
				dirs[i][j] = Integer.valueOf( st.nextToken() );
		}
		int maxSise = 0, k = 0;

		for ( int i = 0; i < n; i++ )
		{
			for ( int j = 0; j < m; j++ )
			{

				if ( !visited[i][j] )
				{
					Set<int[]> set = new HashSet<>();
					visited[i][j] = true;
					visit( i, j, dirs, visited, set );

					for ( int[] p : set )
					{
						setM[p[0]][p[1]] = set;
						setMre[p[0]][p[1]] = k;
						// System.out.printf( "%d %d\n", p[0], p[1] );
					}
					// System.out.println( set );
					//

					k++;
					posSet.add( set );

					maxSise = Math.max( maxSise, set.size() );
				}
			}
		}
		if ( test )
		{
			for ( int[] v : setMre )
				System.out.println( Arrays.toString( v ) );
			System.out.println();
		}
		int[][] allD = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
		int maxSum = 0, newI = 0, newJ = 0;
		String string = "";

		for ( int j = 0; j < m; j++ )
		{
			for ( int i = n - 1; i >= 0; i-- )
			{
				for ( int[] d : allD )
				{
					int ni = i + d[0], nj = j + d[1];
					if ( ni < 0 || nj < 0 || ni >= n || nj >= m )
						continue;
					if ( !setM[i][j].equals( setM[ni][nj] ) )
					{
						if ( maxSum < setM[i][j].size() + setM[ni][nj].size() )
						{
							maxSum = setM[i][j].size() + setM[ni][nj].size();
							newI = i + 1;
							newJ = j + 1;
							string = ni > i ? "S" : ni < i ? "N" : nj > j ? "E" : "W";
						}
					}

				}
			}
		}
		out.println( posSet.size() );
		out.println( maxSise );
		out.println( maxSum );
		out.printf( "%d %d %s\n", newI, newJ, string );
	}

	static void visit( int i, int j, int[][] dirs, boolean[][] visited, Set<int[]> set )
	{
		// bfs to visit all neighbors
		Queue<int[]> neighbours = new LinkedList<>();
		neighbours.add( new int[] { i, j } );
		set.add( new int[] { i, j } );
		visited[i][j] = true;
		while ( !neighbours.isEmpty() )
		{
			int[] pos = neighbours.poll();
			int[][] d = getDirs( dirs[pos[0]][pos[1]] );
			for ( int[] n : d )
				if ( !visited[n[0] + pos[0]][n[1] + pos[1]] )
				{
					neighbours.add( new int[] { pos[0] + n[0], pos[1] + n[1] } );
					set.add( new int[] { pos[0] + n[0], pos[1] + n[1] } );
					visited[pos[0] + n[0]][pos[1] + n[1]] = true;
				}
		}

	}

	static int[][] getDirs( int m )
	{
		String string = toBinaryString( m );// .substring( 28, 32 );
		int[][] allD = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		int[][] dirs = new int[4][2];
		int k = 0;
		for ( int i = 0; i < 4; i++ )
		{
			if ( string.charAt( i ) == '0' )
				dirs[k++] = allD[i];
		}
		return Arrays.copyOf( dirs, k );
	}

	static String toBinaryString( int m )
	{
		String s = "";
		while ( m > 0 )
		{
			s = String.valueOf( m % 2 ) + s;
			m /= 2;
		}
		while ( s.length() < 4 )
			s = "0" + s;
		return s;
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

class point
{
	int x, y;

	public point( int x, int y )
	{
		this.x = x;
		this.y = y;
	}

	public boolean equals( point o )
	{
		// TODO Auto-generated method stub
		return this.x == o.x && this.y == o.y;
	}

}
