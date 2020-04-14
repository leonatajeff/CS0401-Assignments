import java.util.*;
import java.io.*;

public class Assig2 {
	public static void main(String [] args) throws IOException {
		Scanner players = new Scanner(System.in);
		System.out.println("How many players would like to play?");
		int num_Players = players.nextInt();
		for(int k = 0; k < num_Players; k++) { //loops through a game num_Players amount of times
		System.out.println("\nGame "  + (k+1) + " initialized.");
		playing(); //The main game method
		}
	}
	
	public static void playing() throws IOException {
		Scanner input = new Scanner(System.in);
		
		System.out.println("\nWelcome to the Word Changer Game a.k.a the Best Game Ever.");
		
		boolean run;

		while(run = true) {

		System.out.println("\nEnter a Dictionary file name (Example: Name.txt):");
		String pickDict = input.next(); //Dictionary file is chosen
		
		System.out.println("\nPlease enter a username : ");
		String username = input.next(); // username
		String score = player(username); // player method creates the .txt file
		
		System.out.println("\nWelcome " + username + "!");
		
		Dictionary init = new Dictionary(pickDict); // main dictionary object with the chosen dictionary
		Dictionary used = new Dictionary(); // dictionary object to put used words in
	
		//Instructions for the game, taken from the sample output
		System.out.println("\nHere is how to play:\n" +
					"\nFor each round you will see two randomly chosen words." +
					"\nYou will only have 1 minute to convert the first word to the second " +
					"\nUsing only the following changes: " + 
					"\n\tINSERT a character at position x (with x starting at 0)" + 
					"\n\tDELETE a character at position x (with x starting at 0)" +
					"\n\tCHANGE a character at position x (with x starting at 0)" +
					"\nFor example, to change the word 'WEASEL' to 'SEASHELL' you could do the following: " + 
					"\n\t1) Change 'W' at positition 0 to 'S': SEASEL" +
					"\n\t1) Insert 'H' at positition 4: SEASHEL" +
					"\n\t1) Insert 'L' at positition 7: SEASHELL" +
					"\nYour goal is to make the conversion with the least amount of changes." +
					"\nNote that there may be more than one way to achieve this goal!");
		
		System.out.println("\nWould you like to play " + username + "? (y/n)");
		String play = input.next(); // if YES, it will create a GamePlayer object and it will start a game. If NO, it will it will close the program
		
		if (play.toLowerCase().equals("y")) {
			GamePlayer init_Game = new GamePlayer(username);
			String word_1, word_2;
			wordGen(init,used,init_Game); //method explanation at method
			init_Game.playGame();
			
			boolean again;
			while(again = true) {
			System.out.println("Would you like to play another round? (y/n)"); // if YES it will play another round, if NO it will start a new game for another player if the player was entered
			String play_again = input.next();
			if (play_again.equals("n")) {
			System.out.println("Thanks for playing :)");
			PrintWriter output = new PrintWriter(new FileWriter(score,true));
			output.println(init_Game.toStringFile());
			output.close();
			
			return;
			}
			else {
				if (init == null) { // if all words in a dictionary are used, then it will exit the game and start a new one.
					System.out.println("Out of words! Exiting your game now :(");
					System.out.println("Thanks for playing :)");
					return;
				}
			
				wordGen(init,used,init_Game);
				
				init_Game.playGame();
			}
			}
		}
		else {
			System.exit(0); //exits game if player does not want to play at all
		}
		}
	}
	
	// METHOD: generates two random words that are not equal to each other then adds it to the used dictionary profile (all in context of a single game)
	public static void wordGen(Dictionary init, Dictionary used, GamePlayer profile) {
	
		String word_1,word_2;
		do {
			word_1 = init.randWord(5,9);
			word_2 = init.randWord(5,9);
		} while(word_1.equals(word_2));
			
		if (used.contains(word_1)) {
			do {
				word_1 = init.randWord(5,9); 
			} while(word_1.equals(word_2));
		}
		
		if (used.contains(word_2)) {
			do {
				word_2 = init.randWord(5,9);
			} while(word_1.equals(word_2));
		}
			
		profile.getWord_1(word_1);
		profile.getWord_2(word_2);
		
		used.addWord(word_1);
		used.addWord(word_2);
	}

 	public static String player(String id) throws IOException { // checks for existance of file and if it doesn't exist then it creates a file.
		String name = id + ".txt";

		File create = new File(name);
		
		if (!create.exists()) {
			create.createNewFile();
		}
		
		return name;
	} 
}