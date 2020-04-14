//Assignment 3 GamePlayer
import java.util.*;
import java.io.*;

public class GamePlayer {
	private String id = "";
	
	private int rounds;
	private int success;
	private int failures;
	
	private int success_Edits;
	private int num_Edits;
	private int optimal;
	
	private double ratio = 0.0;
	
	private String password;
	private int min_Distance;
	
	public GamePlayer(String n) {
		id = n;
		rounds = 0;
		success = 0;
	}
	
	public GamePlayer(String n, String p, int r, int s, int md, int ne) {
		id = n;
		password = p;
		rounds = r;
		success = s;
		min_Distance = md;
		num_Edits = ne;
	}
	
	public String getName() { // Getter for name
		return id;
	}
	
	public void setPass(String arr) { 
		password = arr;
	}
	
	public boolean equals(GamePlayer arg) { //equals method
		if (this == arg) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toStringFile() { //File writing
			
		StringBuilder obj = new StringBuilder();
		obj.append("\n");
		obj.append(id + ","); //name
		obj.append(password + ","); //password
		obj.append(rounds + ","); //rounds played
		obj.append(success + ","); //successful rounds
		obj.append(min_Distance + ","); // minimum distance
		obj.append(num_Edits); //total number of edits
	
		return obj.toString();
	}
		
	public String toString() { //Screen printing
		int failures = rounds - success;
		
		StringBuilder obj = new StringBuilder();
		obj.append("\n\tname: " + id);
		obj.append("\n\tRounds: " + rounds);
		obj.append("\n\tSuccesses: " + success);
		obj.append("\n\tFailures: " + failures);
		if (ratio == 0.0 && optimal == 0) {
			obj.append("\n\tRatio (successes only): 1.0");
		}
		else {
		obj.append("\n\tRatio (successes only): " + ((double)success_Edits/optimal));
		}
		return obj.toString();
	}
	
	public void success(int win_tries, int opt) { //success method that counds up total edits in a success
		success_Edits += win_tries;				  //Also records optimal moves
		optimal += opt;
		success++;
		rounds++;
	}
	
	public void failure() {
		rounds++;
		failures++;
	}
	
}