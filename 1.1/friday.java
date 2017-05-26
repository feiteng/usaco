import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*-
 ID: feiteng li
 LANG: JAVA
 TASK: friday
 */

public class friday
{
	static BufferedReader f;
	static PrintWriter out;
	static String filename = "friday";

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

		friday g = new friday();
		g.run();

		out.close(); // close the output file
	}

	void run() throws IOException
	{
		StringTokenizer st = new StringTokenizer( f.readLine() );
		int n = Integer.valueOf( st.nextToken() );
		int day = 0, year = 1900, annual = 365; // 1900-1-1
		int[] dates = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }, thirteen = new int[12], week = new int[7];
		for ( int y = 0; y < n; y++ )
		{
			thirteen[0] = 13 + day;
			if ( leapYear( year ) )
			{
				dates[1] = 29;
				annual = 366;
			}
			else
			{
				dates[1] = 28;
				annual = 365;
			}
			for ( int i = 1; i < dates.length; i++ )
				// finds day (counting from 1) representation of thirteenth
				thirteen[i] = thirteen[i - 1] + dates[i - 1];
			for ( int i = 0; i < thirteen.length; i++ )
				week[thirteen[i] % 7]++; // 0 - Sunday
			day += annual;
			year++;
		}
		String string = String.valueOf( week[6] );
		for ( int i = 0; i < week.length - 1; i++ )
			string += " " + String.valueOf( week[i] );
		string += "\n";
		out.write( string );
	}

	boolean leapYear( int n )
	{
		if ( n % 4 == 0 )
		{
			if ( n % 100 != 0 )
				return true;
			if ( n % 100 == 0 && n % 400 == 0 )
				return true;
		}
		return false;

	}
}
