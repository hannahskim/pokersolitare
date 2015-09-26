import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Code to test the Card, Deck and Hand classes
 * 
 * @author Ridout
 * @version October 2013
 */

public class CardTestCode2013
{
	public static void main(String[] args) throws FileNotFoundException
	{
		// Code to test the Card class
		// Comment out any code you haven't written a method for yet
			
		String studentName = "Solutions";
		System.out.println("Card classes test code for: " + studentName);

		// Check the Deck class and the Hand class
		// Create a new shuffled Deck
		Deck myDeck = new Deck();
		myDeck.shuffle();

		// Make the first Hand from all of the Cards in the Deck
		// Check the getValue method by looking at the total value of all Cards
		int totalValue = 0;
		Hand firstHand = new Hand();
		while (myDeck.noOfCardsLeft() > 0)
		{
			Card nextCard = myDeck.deal();
			totalValue += nextCard.getValue();
			firstHand.addCard(nextCard);
		}
		System.out.println("\nTotal value for a deck: " + totalValue);
		
		// Make a second Hand from the Cards in a String
		Hand secondHand = new Hand("9H 9C 6D KH JS 5C AD 7S 8S JH 6C 8H TD " + 
					   "3C 3D 6H AH QD 2S 9S AC 8D TC 5D 7D QH " +
						   "4D QC QS 3H 6S KD KS TS 7H 7C 4C 9D JC " +
					   "AS 8C KC 2C 4S 2H TH 5S 2D 3S 5H 4H JD");

		// Display the shuffled cards and the ordered cards
		System.out.println("\nShuffled Cards");
		System.out.println(firstHand);
		System.out.println("Cards sorted by Suit");
		firstHand.sortBySuit();
		System.out.println(firstHand);
		System.out.println("Cards sorted by Rank");
		secondHand.sortByRank();
		System.out.println(secondHand);
		
		// Check the clear method
		secondHand.clear();
		System.out.println("\nEmpty Hand: *" + secondHand + "*");
		
		// Code to test the isAce method
		// This code should only display the 4 Aces
		System.out.println("\nDisplay only the aces");
		myDeck.shuffle();
		while (myDeck.noOfCardsLeft() > 0)
		{
			Card nextCard = myDeck.deal();
			if (nextCard.isAce())
			    System.out.print(nextCard + " ");
		}

		// Check the Blackjack methods with some hands in a file
		System.out.println ("\n\nBlack jack hands test");
		Scanner handFile = new Scanner(new File("hands.txt"));
		totalValue = 0;
		while (handFile.hasNextLine())
		{
			String handStr = handFile.nextLine();
			Hand nextHand = new Hand(handStr);
			int value = nextHand.getValue();
			totalValue += value;
			System.out.println(nextHand + " Value: " + value);
		}
		handFile.close();
		
		System.out.println("Total value for all file hands: " + totalValue);
		System.out.println("\nCard classes test is finished");
	}
}
