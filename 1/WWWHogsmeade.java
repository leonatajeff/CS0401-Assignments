/*
Jefferson Leonata - CS-0401 - TuTh 1:00
Prof: Ramirez
Lab Instructor: Matthew Hyrdil

I'm not a very good programmer and I'm sure I could've made this program a lot shorter but I did what I could.
I also tried to copy the format of the example output to make it look neat.
For the questions and answers, I created my own.

*/

import java.util.*;

public class WWWHogsmeade {

	// global variables
	public static double numPP = 0.0;
	public static double numEEeach = 0.0;
	public static double numEEthree = 0.0;
	public static double numTWreg = 0.0;
	public static double numTWauto = 0;
	public static double pricePP = 290.0;
	public static double priceEEeach = 60.0;
	public static double priceEEthree = 160.0;
	public static double priceTWreg = 1479.0;
	public static double priceTWauto = 2465.0;
	
	public static int priceAdjust = 0;
	
	public static String currency = " Knuts";
		
	public static void main(String [] args) {
		//user input
		Scanner input = new Scanner(System.in);
		
		boolean run;
		while (run = true) {
		//greeting
		System.out.println("\nWelcome to Weasley's Wizard Wheezes!");
		System.out.print("\nCan we help the next witch or wizard? (yes or no?): ");
		
		String option0 = input.next();
		//the core of the program
		if (option0.toLowerCase().equals("yes")) {
			priceList();
			
			challengeInfo();
			
			System.out.println("\nDo you accept the challenge? (yes or no?): ");
			Scanner input2nd = new Scanner(System.in);
			String option1 = input2nd.next();
			
			//Yes to challenge question:
			if (option1.toLowerCase().equals("yes")) {
				QnA();
				
			}
			//No to challenge question
			else {
				noPathway();
			}
		}
		// Doesn't want to be next customer:
		else {
		System.out.println("Alright! Have a good day!");
		run = false;
		System.exit(0);
		}
		}
}
	public static void priceList() {
	// item identity formatted
		String pp,ee,tw;
		pp = "||Pygmy Puffs||";
		ee = "||Extendable Ears||";
		tw = "||Trick Wand||";
	// common phrases used for item identification
		String phrase0,phrase1,phrase2,phrase3;
		phrase0 = " (Each) ";
		phrase1 = " (Bag of three) ";
		phrase2 = " (Regular) ";
		phrase3 = " (Autographed) ";
		System.out.println("Here is our price list:\n");
			format(pp + phrase0 , pricePP, currency);
			format(ee + phrase0 ,priceEEeach, currency);
			format(ee + phrase1 ,priceEEthree, currency);
			format(tw + phrase2 ,priceTWreg, currency);
			format(tw + phrase3 ,priceTWauto, currency);
	}
	
		public static void priceList_Repeat() {
		priceList();
		menuUpdate();
	}

	public static void format(String left, double middle, String right) { //format
		System.out.printf("%-50s%15f%3s%n", left, middle, right);
	}
	
	public static String challengeInfo() { //info
		String info = "\nDo you want these prices or would you prefer to try your skill for a discount?" +
		"\nYou may elect to answer a challenge question --" + "\nif you answer correctly you will get discounted prices" +
		"\nbut if you answer incorrectly you must pay a 10% penalty on your overall bill.";
		System.out.println(info);
		return info;
	}
	
	public static String QnA() { // Questions and answers (EXTRA CREDIT #1
		System.out.println("\nYou have accepted the challenge!");
		System.out.println("\nPlease answer the following question: \n");
		String[] questions = {"Who pioneered the grunge music scene in the 90s?\n",
							  "Who played Malcolm in Malcolm in the Middle?\n",
							  "What's the largest great lake?\n",
							  "What was Dr. Frankenstein's monster's name?\n"};
		Random r = new Random();
		int randomNum = r.nextInt(questions.length);
		Scanner input = new Scanner(System.in);
		
		if (randomNum == 0) {
			System.out.println(questions[0]);
			String a01, a02, a03, a04;
			a01 = " (1) Andre 3000 ";
			a02 = " (2) Nirvana "; //correct answer
			a03 = " (3) Lauryn Hill ";
			a04 = " (4) Green Day ";
			System.out.println(a01 + "\n" + a02 + "\n" + a03 + "\n" + a04);
			int answer0 = input.nextInt();
			switch (answer0) {
				case 2: //correct answer
					correctAnswer();
					break;
				default:
					wrongAnswer();
					break;
			}
		}
		else if (randomNum == 1) {
			System.out.println(questions[1]);
			String a11,a12,a13,a14;
			a11 = " (1) Jeff Leonata ";
			a12 = " (2) Macaulay Culkin ";
			a13 = " (3) Justin Berfield ";
			a14 = " (4) Frankie Muniz "; // correct answer
			System.out.println(a11 + "\n" + a12 + "\n" + a13 + "\n" + a14);
			int answer1 = input.nextInt(); 
			switch (answer1) {
				case 4:
					correctAnswer();
					break;
				default:
					wrongAnswer();
					break;
			}
		}
		else if (randomNum == 2) {
			System.out.println(questions[2]);
			String a21,a22,a23,a24;
			a21 = " (1) Lake Superior";
			a22 = " (2) Lake Ontario";
			a23 = " (3) Lake Michigan";
			a24 = " (4) Lake Victoria ";
			System.out.println(a21 + "\n" + a22 + "\n" + a23 + "\n" + a24);
			int answer2 = input.nextInt(); 
			switch (answer2) {
				case 1:
					correctAnswer(); // correct answer
					break;
				default:
					wrongAnswer(); 
					break;
			}
		}
		else {
			System.out.println(questions[3]);
			String a31,a32,a33,a34;
			a31 = " (1) Frankenstein ";
			a32 = " (2) Slenderman ";
			a33 = " (3) The Monster ";
			a34 = " (4) Freshman ";
			System.out.println(a31 + "\n" + a32 + "\n" + a33 + "\n" + a34);
			int answer3 = input.nextInt(); 
			switch (answer3) {
				case 3:
					correctAnswer();
					break;
				default:
					wrongAnswer(); 

					break;
			}
		}
		return questions.toString();
	}
	
	
	public static String noPathway() {
		String noPath = "Ok! No problem! \n";
		System.out.println(noPath);
		priceAdjust = 0;
		menuUpdate();
		return noPath;
	}
	
	public static String correctAnswer() {
		double disVal = 0.10;
		double percent = disVal*100.0;
		String test = "true";
		System.out.println("You are correct! You get a " + percent + " percent discount off your entire purchase! ");
		priceAdjust = 1;
		menuUpdate();
		return test;
	}
	
	public static String wrongAnswer() { 
		double penaltyVal = 0.10;
		double penalty = penaltyVal*100;
		String wrong = "Wrong answer! You will now recieve a " + penalty + " percent upcharge on your entire order :( sorry!";
		System.out.println(wrong);
		priceAdjust = 2;
		menuUpdate();
		return wrong;
	}
	
	public static void menuUpdate() {
		boolean run;
		while (run = true) {
		System.out.println(" \n Please choose an option: ");
		System.out.println(" \n 1) Update Pygmy Puffs Order " +
						   " \n 2) Update Extendable Ears Order " +
						   " \n 3) Update Trick Wands Order " +
						   " \n 4) Show price list " +
						   " \n 5) Check out ");
		Scanner menu = new Scanner(System.in);
		int menuInput = menu.nextInt();
		if ((menuInput > 5) || (menuInput < 1)) {
			System.out.println("ERROR: Please enter an integer value between 1-5");
		}
		else {
		
			switch (menuInput) {
			case 1:
				updatePP();
				break;
			case 2:
				updateEE();
				break;
			case 3:
				updateTW();
				break;
			case 4:
				priceList_Repeat();
				break;
			case 5:
				checkOut();
				break;
		}
		break;
			}
		}
	}
	
	public static void updatePP() {
		Scanner quantity = new Scanner(System.in);
		boolean run;
		while (run = true) {
			if (numPP == 0) {
				System.out.println("\n0 Pygmy Puffs ordered\n");
			}
			else {
				System.out.println("\nYou currently have " + numPP + " Pygmy Puffs ordered\n");
			}
			System.out.println("How many Pygmy Puffs would you like for " + 
				pricePP + currency + " each." +
				"\nIf you want to remove items please enter amount in a negative integer");
			int input = quantity.nextInt();
			numPP = numPP + input;
			System.out.println("\nYou now have " + numPP + " PygmyPuffs in total");
			menuUpdate();
			break;
		}
	}
	
	public static void updateEE() {
		Scanner quantity = new Scanner(System.in);
		boolean run;
		while (run = true) {
			System.out.println("No Extendable Ears ordered");
			System.out.println("How many Extendable Ears would you like for: " + 
				"\n" + priceEEeach + currency + " (each)." +
				"\n" + priceEEthree + currency + " (per bag of 3)." +
				"\nIf you want to remove items please enter amount in a negative integer");
			System.out.println("\nHow many (each)? Enter zero if you want bag of threes");
			double inputEach = quantity.nextDouble();
			System.out.println("\nHow many bag of threes?");
			double inputBag = quantity.nextDouble();
			numEEeach = numEEeach + inputEach;
			numEEthree = numEEthree + inputBag;
			System.out.println("\nYou now have " + numEEeach + " Extendable Ears (each)");
			System.out.println("\nYou now have " + numEEthree + " Extendable Ears (per bag of 3)");
			menuUpdate();
			break;
		}
	}
	
	public static void updateTW() {
		Scanner quantity = new Scanner(System.in);
		boolean run;
		while (run = true) {
			System.out.println("No Trick Wands ordered");
			System.out.println("How many Trick Wands would you like for: " + 
				"\n" + priceTWreg+ currency + " (regular)." +
				"\n" + priceTWauto + currency + " (autographed!)." +
				"\nIf you want to remove items please enter amount in a negative integer");
			System.out.println("\nHow many regular Trick Wands do you want? Enter zero if you want an autographed one.");
			double inputReg = quantity.nextDouble();
			System.out.println("\nHow many autographed Trick Wands?");
			double inputAuto = quantity.nextDouble();
			numTWreg = numTWreg + inputReg;
			numTWauto = numTWauto + inputAuto;
			System.out.println("\nYou now have " + numTWreg + " Trick Wands (regular)");
			System.out.println("\nYou now have " + numTWauto + " Trick Wands (autographed)");
			menuUpdate();
			break;
		}
	}
	
	public static double checkOut() {
		
		String idPP, idEEeach, idEEthree, idTWreg, idTWauto;
		idPP = "Pygmy Puffs";
		idEEeach = "Extendable Ears (each)";
		idEEthree = "Extendable Ears (bag of threes)";
		idTWreg = "Trick Wands (regular)";
		idTWauto = "Trick Wands (autographed)";
		double ovrPP, ovrEEeach, ovrEEthrees, ovrTWreg, ovrTWauto;
		ovrPP = pricePP*numPP;
		ovrEEeach = priceEEeach*numEEeach;
		ovrEEthrees = priceEEthree*numEEthree;
		ovrTWreg = priceTWreg*numTWreg;
		ovrTWauto = priceTWauto*numTWauto;
		
		double total;
		total = ovrPP + ovrEEeach + ovrEEthrees + ovrTWreg + ovrTWauto;
		
		System.out.println("\n-----------------------------------"+ "\nHere is your subtotal: \n");
		format(numPP + " " + idPP + " at " + pricePP + currency, ovrPP, currency);
		format(numEEeach + " " + idEEeach + " at " + priceEEeach + currency, ovrEEeach, currency);
		format(numEEthree + " " + idEEthree + " at " + priceEEthree + currency, ovrEEthrees, currency);
		format(numTWreg + " " + idTWreg + " at " + priceTWreg + currency, ovrTWreg, currency);
		format(numTWauto + " " + idTWauto + " at " + priceTWauto + currency, ovrTWauto, currency);
		format("\n-----------------------------------" + "\nYour original total is: ", total, currency);
		
		double a;
		double c;
		
		double savings; // extra credit for savings indicator
		
		if (priceAdjust == 0) {
			a = total;
			savings = total - a;
			c = savings;
		}
		else if (priceAdjust == 1) {
			a = total*0.9;
			savings = total - a;
			c = savings;
		}
		else {
			a = total*1.10;
			savings = a - total;
			c = savings;
		}
		
		a = (int)a;
		
		format("\nYour new total with the challenge question considered (if you did it or not)", a, currency +
				"\nYou also saved " + c + currency + "\n-----------------------------------");
		
		boolean run;
		boolean run_2nd;
		
		while (run = true) {
		if (a <= 0.0) {
			System.out.println("\nYou have overpaid by " + -a + " Knuts");
			double val = -a;
			System.out.println("\nHere's your change: " + val + " Knuts (small bills only here, sorry!)");
			System.out.println("\nHave a nice day!" +
							   "\n-----------------------------------");
			break;
		}
		Scanner payment = new Scanner(System.in);
		System.out.println("\nEnter currency: " +
						   "\n[1]for Knut(s) \n[2]for Sickle(s) \n[3]for Galleon(s)");
		int currence = payment.nextInt();
		while (run_2nd = true) {
			if ((currence > 3) || (currence < 0)) {
				System.out.println("\nERROR: Please enter a valid currency or check your spelling");
			}
			else {
				break;
			}
			
		}
		System.out.println("\nQuantity of currency chosen? \n");
		
		int money = payment.nextInt();
		switch (currence) {
				case 1:
					a = a - money;
					total(a);
					break;
				case 2:
					int adjust_S = money*29;
					a = a - adjust_S;
					total(a);
					break;
				case 3:
					int adjust_G = money*473;
					a = a - adjust_G;
					total(a);
					break;
			}
		
	}
		return a;
	}
	
	public static void total(double money) {
		System.out.println("\nYour new total is now: " + money);
	}
}