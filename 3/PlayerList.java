// CS 0401 Fall 2019
// Shell of the PlayerList class.
// This class represents a collection of players -- a very simple database.  The
// collection can be represented in various ways.  However, for this assignment
// you are required to use an array of GamePlayer.

// Note the imports below.  java.util.* is necessary for ArrayList and java.io.* is
// necessary for the file reading and writing.
import java.util.*;
import java.io.*;

public class PlayerList
{
	private GamePlayer [] players;  // array of GamePlayer
	private int size;				// logical size
	private String file;			// name of file associated with this PlayerList
	
	private String id;
	private String password;
	private int rounds;
	private int success;
	private int min_Distance;
	private int num_Edits;
	
	private int num = 0;

	// Initialize the list from a file.  Note that the file name is a parameter.  You
	// must open the file, read the data, making a new GamePlayer object for each line
	// and putting them into the array.  Your initial size for the array MUST be 3 and
	// if you fill it should resize by doubling the array size.  
	
	// Note that this method throws IOException. Because of this, any method that CALLS
	// this method must also either catch IOException or throw IOException.  Note that 
	// the main() in PlayerListTest.java throws IOException.  Keep this in mind for your
	// main program (Assig3.java).  Note that your saveList() method will also need
	// throws IOException in its header, since it is also accessing a file.
	public PlayerList(String fName) throws IOException
	{
		players = new GamePlayer[3]; // Array of size 3
		// Next we read in all of the data from the file.
		File inFile = new File(fName);
		Scanner fileIn = new Scanner(inFile);
		while (fileIn.hasNextLine()) {
			
			fileIn.useDelimiter(",");
			id = fileIn.next();
			password = fileIn.next();
			
			rounds = fileIn.nextInt();
			success = fileIn.nextInt();
			min_Distance = fileIn.nextInt();
			num_Edits = Integer.parseInt(fileIn.nextLine().replaceAll("[\\D]", ""));
			
			GamePlayer init = new GamePlayer(id, password, rounds, success, min_Distance, num_Edits);
			
			if (size() == players.length) {
				players = resize(players);
			}
			
			players[num] = init;
			num++; //keeping track of how many objects made in order to see if it should resize the array
		}
	}
	
	public GamePlayer [] resize(GamePlayer [] orig) {
		int old = orig.length;
		int present = 2 * old; // doubling the array size
		GamePlayer [] resized = new GamePlayer[present];
		
		for (int i = 0; i < players.length; i++) {
			resized[i] = players[i];
		}
		
		return resized;
	}
	
	public int size() throws IOException { //logical size
		int counter = 0;
		for (int i = 0; i < players.length; i++) {
			if (players[i] != null) {
				counter++;
			}
		}
		return counter;
	}
	
	public int capacity() { //physical size
		return players.length;
	}
	
	public String toString() {
		int failures = rounds - success;
		StringBuilder obj = new StringBuilder();
			obj.append("\nTotal Players: " + num);
			obj.append("\n\tName: " + id);
			obj.append("\n\tRounds: " + rounds);
			obj.append("\n\tSuccesses: " + failures);
			obj.append("\n\tFailures: " + (rounds-success));
			obj.append("\n\tRatio (success only): " + ((double)num_Edits/min_Distance));
		
			obj.append("\n");
		return obj.toString();
	}
	
	public boolean containsName(String name) { //my noted code was not working for some reason so I just have this filler.
		if (id != name) {
			return false;
		} else {
			return true;
		}
		/* for (int i = 0; i < players.length; i++) {
			if (players[i].getName().equals(name)) {
				return true;
			}
		}
		return true; */
	}
	
	public GamePlayer authenticate(GamePlayer temp) {
		if (this.equals(temp)) {
			return temp;
		} else {
			return null;
		}
	}
	
	public boolean addPlayer(GamePlayer new_Player) {
		String S = new_Player.getName();
		if (containsName(S)) {
			return false;
		} else {
			new_Player.toStringFile();
			return true;
		}
	}
	
	public void saveList() throws IOException { //not functional
		GamePlayer obj = new GamePlayer(id);
		obj.toStringFile();
	}

	// See program PlayerListTest.java to see the other methods that you will need for
	// your PlayerList class.  There are a lot of comments in that program explaining
	// the required effects of the methods.  Read that program very carefully before
	// completing the PlayerList class.  You will also need to complete the modified
	// GamePlayer class before the PlayerList class will work, since your array is an
	// array of GamePlayer objects.
	
	// You may also want to add some methods that are not tested in PlayerListTest.java.
	// Think about what you need to do to a PlayerList in your Assig3 program and write 
	// the methods to achieve those tasks.  However, make sure you are always thinking
	// about encapsulation and data abstraction.
}

