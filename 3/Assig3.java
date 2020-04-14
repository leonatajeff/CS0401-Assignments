import java.util.*;
import java.io.*;

public class Assig3 {
	
	public static void main(String [] args) throws IOException {
		boolean run;
		PlayerList L = new PlayerList("players.txt"); // PlayerList initialized.
		while (run = true) { // this loop allows the game to keep running until a user does not want to play anymore.
		Scanner input = new Scanner(System.in);
		
		System.out.println("What dictionary file would you like to use? (Example: bigDict.txt)");
		String dict = input.next();
		
		System.out.println("\nWelcome to the World Changer Program");
		
		System.out.println("Would you like to play?");
		String play_Option = input.next();
		
		if (play_Option.equals("no")) {
			System.exit(0);
			System.out.println("Oh no! Exiting program now.");
		} else {
			theGame(dict, L);
		}
		
		L.toString(); //Showing the cumulative statistics of all of the GamePlayers
		L.saveList(); //saves list
	}
	}
	
	public static void theGame(String dictionary, PlayerList players) throws IOException { // PlayerList is initialized in here
		Dictionary init = new Dictionary(dictionary); // main dictionary object with the chosen dictionary
		Dictionary used = new Dictionary(); // dictionary object to put used words in
		Scanner option = new Scanner(System.in); 
		int num_Changes = 0;
		int success_Edits = 0;
		int optimal = 0;
		GamePlayer GAME;
		
		System.out.println("\nPlease sign in:\n" +
						   "\n(If you are a new player, type in * and press enter\n" +
						   "and you will proceed to create a new account)\n");
		
		String un,pw;

		System.out.println("Username: ");
		un = option.next();
		
		GAME = new GamePlayer(un);
	
		boolean create;
		if (un.equals("*")) { //account creation step
			System.out.println("Create a new account! Enter a name: ");
			un = option.next();
			System.out.println("Enter a password: ");
			pw = option.next();
			GAME = new GamePlayer(un);
			players.addPlayer(GAME);
			GAME.setPass(pw);
			System.out.println("Account Created");
		} else {
		
			System.out.println("Password: ");
			pw = option.next();
			GAME = new GamePlayer(un);
			GAME.setPass(pw);
		}
		boolean run;
		while (run = true) { // the base of the game with all the features.
		String word_one,word_two;
		
		//-----------------------------------------generating the two words--------
		do {
		word_one = init.randWord(5,9);
		word_two = init.randWord(5,9);
		} while(word_one.equals(word_two));
	
		if (used.contains(word_one)) {
			do {
				word_one = init.randWord(5,9); 
			} while(word_one.equals(word_two));
		}
		
		if (used.contains(word_two)) {
			do {
				word_two = init.randWord(5,9);
			} while(word_one.equals(word_two));
		}
		
		used.addWord(word_one);
		used.addWord(word_two);
		//-------------------------------------------------------------------------
		int L_Distance = Dictionary.distance(word_one.toString(),word_two.toString());
		
		StringBuilder word_1 = new StringBuilder(word_one);
		StringBuilder word_2 = new StringBuilder(word_two);
		
		System.out.println("\nYour goal is to convert '" + word_1 + "' to '" + word_2 + " in " + L_Distance + " edits");
		
		// I (Mutator) | x (location/index) | v (letter value)
		System.out.println("\nHere are your options: ");
		System.out.println("\tI x v -- [Insert] a character at location [x] with value [v]");
		System.out.println("\tD x v -- [Delete] a character at location [x]");
		System.out.println("\tC x v -- [Change] a character at location [x] to value [v]");
		
		//timer
		MyTimer time = new MyTimer(60000);
		time.start();
		System.out.println("\nYou have ONE minute to complete a round");
		
		do {
			time.check();
			System.out.println("\nIndex (Locations):" + "\t" + "0123456789");
			System.out.println("----------------------------------");
		
			System.out.println("Current Word:       " + "\t" + word_1);
			System.out.println("Word 2:             " + "\t" + word_2);
			System.out.println("\nWould you like to Insert, Delete, or Change? (Enter I, D, or C)");
			String IDC;
			
			do {
				IDC = option.next();
			} while(!(IDC.toUpperCase().equals("I") || IDC.toUpperCase().equals("D") || IDC.toUpperCase().equals("C")));
		
			System.out.println("Location? (An integer) [x]");
			char value = '\0';
			int location = option.nextInt();
			if (IDC.toUpperCase().equals("I") || IDC.toUpperCase().equals("C")) {
				System.out.println("Value? (A letter) [v]");
				value = option.next().charAt(0);
			}
		
			//the mutators
			if (IDC.toUpperCase().equals("I")) {
				word_1 = insert(word_1, location, value);
				num_Changes++;
			}
			else if (IDC.toUpperCase().equals("D")) {
				word_1 = delete(word_1, location);
				num_Changes++;
			}
			else {
				word_1 = change(word_1, location, value);
				num_Changes++;
			}
		
			if (word_1.toString().equals(word_2.toString())) {
				break;
			}

		} while (time.check() == true);
		
		if (word_1.toString().equals(word_2.toString())) {
			success_Edits += num_Changes;
			GAME.success(success_Edits, optimal);
		}
	
		if (!(word_1.toString().equals(word_2.toString()))) {
			GAME.failure();
		}
		
		System.out.println("\nYou ran out of time! Your last input might not have counted\n");
		
		System.out.println("\nWould you like to play another round? (y/n)");
		String again = option.next();
		if (again.equals("n")) {
			System.out.println("Thanks for playing");
			GAME.toString(); //shows cum statistics of one player
			
			String str = GAME.toStringFile();
			
			BufferedWriter out = new BufferedWriter(new FileWriter("players.txt", true)); 
			out.write(str); 
			out.close(); 
			
			break;
		} else if (init == null) { // if all words in a dictionary are used, then it will exit the game and start a new one.
				System.out.println("Out of words! Exiting your game now :(");
					System.out.println("Thanks for playing :)");
					return;	
		}
	}
	}
	
	public static StringBuilder insert(StringBuilder word, int x, char v) {  //mutator for inserting a character
		word.insert(x,v);
		return word;
	}
	
	public static StringBuilder delete(StringBuilder word, int x) { //mutator for deleting a character
		word.deleteCharAt(x);
		return word;
	}
	
	public static StringBuilder change(StringBuilder word, int x, char v) { //mutator for removing but replacing a character
		for(int k = 0; k < word.length(); k++) {
			if (word.codePointAt(k) == word.codePointAt(x)) {
				word.setCharAt(x,v);
				break;
			}
		}
		return word;
	}
}