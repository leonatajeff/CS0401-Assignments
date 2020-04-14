import java.util.*;
import java.io.*;

public class GamePlayer {
	
	private String id = "";
	private StringBuilder word_1;
	private StringBuilder word_2;
	
	private int rounds;
	private int success;
	private int failures;
	private int num_Changes; // number of edits made
	private int num; // minimum number of edits from L-Distance
	private int total_edits_W; // total number of edits made in successes
	private int optimal = 0;
	private double ratio = 0.0;
	
	
	public GamePlayer(String n) { //Constructor for player username, fresh round and success value
		id = n;
		rounds = 0;
		success = 0;
	}

	public GamePlayer(String name, int r, int succ, int opt, int tries) { //Constructor for existing player
		id = name;
		rounds = r;
		success = succ;
		optimal = opt;
		num_Changes = tries;
		failures = (r-succ);
	}
	
	public String getName() { // Getter for name
		return id;
	}
	
	public StringBuilder getWord_1(String one) { // Getter for first word 
		StringBuilder one1 = new StringBuilder(one);
		word_2 = one1;
		return word_2;
	}

	public StringBuilder getWord_2(String two) { // Getter for second word
		StringBuilder two2 = new StringBuilder(two);
		word_1 = two2;
		return word_1;
	}
	
	public void playGame() { // The game itself.
		Scanner option = new Scanner(System.in);
		num = Dictionary.distance(word_1.toString(),word_2.toString());
		
		//the actual game
		boolean run;
		while(run = true) {
		System.out.println("\nYour goal is to convert '" + word_1 + "' to '" + word_2 + " in " + num + " edits");
		
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
			total_edits_W += num_Changes;
			success(total_edits_W, optimal);
		}
	
		if (!(word_1.toString().equals(word_2.toString()))) {
			failure();
		}
		
		System.out.println("\nYou ran out of time! Your last input might not have counted\n");

		//Results of one game
		System.out.println("Here are your current stats: ");
		System.out.println("\tName: " + getName());
		System.out.println("\tRounds: " + rounds);
		System.out.println("\tSuccesses: " + success);
		System.out.println("\tFailures: " + failures);
		if (ratio == 0.0 && optimal == 0) {
			System.out.println("\tRatio (successes only): 1.0");
		}
		else {
		System.out.println("\tRatio (successes only): " + ((double)total_edits_W/optimal));
		}
		
		toStringFile();
	
		break;
		}
	}
	
	public void success(int win_tries, int opt) { //success method that counds up total edits in a success
		total_edits_W += win_tries;				  //Also records optimal moves
		optimal += opt;
		success++;
		rounds++;
	}
	
	public void failure() {
		rounds++;
		failures++;
	}
	
	public String toString() { //Screen printing
		StringBuilder obj = new StringBuilder();
		obj.append("\tname: " + id);
		obj.append("\n\tRounds: " + rounds);
		obj.append("\n\tSuccesses: " + success);
		obj.append("\n\tFailures: " + failures);
		if (ratio == 0.0 && optimal == 0) {
			obj.append("\n\tRatio (successes only): 1.0");
		}
		else {
		obj.append("\n\tRatio (successes only): " + ((double)total_edits_W/optimal));
		}
		return obj.toString();
	}
	
	public String toStringFile() { //File writing
		StringBuilder obj = new StringBuilder();
		obj.append(rounds + "\n"); //rounds played
		obj.append(success + "\n"); //successful rounds
		obj.append(optimal + "\n"); // optimal edits needed
		obj.append(num_Changes + "\n"); //total edits needed
		
		return obj.toString();
	}
	
	public StringBuilder insert(StringBuilder word, int x, char v) {  //mutator for inserting a character
		word.insert(x,v);
		return word;
	}
	
	public StringBuilder delete(StringBuilder word, int x) { //mutator for deleting a character
		word.deleteCharAt(x);
		return word;
	}
	
	public StringBuilder change(StringBuilder word, int x, char v) { //mutator for removing but replacing a character
		for(int k = 0; k < word.length(); k++) {
			if (word.codePointAt(k) == word.codePointAt(x)) {
				word.setCharAt(x,v);
				break;
			}
		}
		return word;
	}
}