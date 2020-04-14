
public class War {
	
	public static void main(String [] args) {
		System.out.println("Welcome to the Game of War!");
		int deck_size = 52; // Deck of 52 Cards (Standard deck)
		MultiDS<Card> deck = new MultiDS<Card>(deck_size);

		for (Card.Suits s: Card.Suits.values()) { // nested for-each loop to create the cards
			for (Card.Ranks r: Card.Ranks.values()) {
				Card create = new Card(s,r);
				deck.addItem(create);
			}
		}
		
		deck.shuffle(); //shuffling
	
		MultiDS<Card> p1 = new MultiDS<Card>(deck_size/2); //player 1 (drawing deck of 26)
		MultiDS<Card> p2 = new MultiDS<Card>(deck_size/2); //player 2 (drawing deck of 26)
	
		for (int i = 0; i < deck_size; i ++) { //loop to alternatively draw cards for each player 
			if (i%2 == 0) { //even goes to player 1
				p1.addItem(deck.getItem(i));
			} else { 		//odd goes to player 2
				p2.addItem(deck.getItem(i));
			}
		}
	
		System.out.println("\nHere is Player 1's Hand:" + "\nContents:\n" + p1.toString());
		System.out.println("\nHere is Player 2's Hand:" + "\nContents:\n" + p2.toString());
		
		//reversing array so it plays the "top" card in my code
		p1.reverse();
		p2.reverse();
		
		
		System.out.println("\nStarting the War!\n");

		MultiDS<Card> p1_dis = new MultiDS<Card>(deck_size/2); //player 1 discard pile
		MultiDS<Card> p2_dis = new MultiDS<Card>(deck_size/2); //player 2 discard pile
		
		int rounds = 0; // for rounds and getting specific card
		int num = deck_size/2; // 26
		
		for (int k = 0; k < num; k++) {
			rounds++;
			int result = p1.getItem(k).compareTo(p2.getItem(k)); //intially compared cards
			
			if (result > 0) {
				p1_dis.addItem(p1.getItem(k));
				p1_dis.addItem(p2.getItem(k));
				p1.shiftRight();
				p1.removeItem();
				System.out.println("Round: " + rounds + " | Player 1 WINS | " + p1.getItem(k) + " beats " + p2.getItem(k));
			}	else if (result < 0) {
						p2_dis.addItem(p2.getItem(k));
						p2_dis.addItem(p1.getItem(k));
						p2.shiftRight();
						p2.removeItem();
						System.out.println("Round: " + rounds + " | Player 2 WINS | " + p1.getItem(k) + " loses to " + p2.getItem(k));
			}	else {
						System.out.println("Round: " + rounds + " | A TIE! (WAR)  | " + p1.getItem(k) + " and " + p2.getItem(k));		
			}
		}
		
		//Pile copied to hand and then shuffled
		p1 = p1_dis;
		p2 = p2_dis;
		p1.shuffle();
		p2.shuffle();
		
		System.out.println("\nOut of cards to draw, turning discard pile into drawing pile..." + "\nShuffling...");
		System.out.println("\nNew Hands:");
		System.out.println("\nHere is Player 1's Hand:" + "\n\n[Contents]:\n" + p1.toString());
		System.out.println("\nHere is Player 2's Hand:" + "\n\n[Contents]:\n" + p2.toString());
		
		
	}
}