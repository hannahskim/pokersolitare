/** Checks the type for all possible Poker Hands
 * Works for different size hands (e.g. 5 or 7 card)
 * @author G. Ridout
 * @version October 2013
 */

public class AllPossiblePokerHands
{
	final static int HAND_SIZE = 5;
	
	public static void main(String[] args)
	{
		PokerDeck myDeck = new PokerDeck(HAND_SIZE);
    	int[] noOfEachType = new int[10];

		System.out.println("Generating and analysing all possible " + HAND_SIZE + " card hands");

		// Generate each hand and tally the number of each hand's type
		// Also total the number of hands analysed - time this operation
		long startTime = System.currentTimeMillis();
		int noOfHands = 0;
		PokerHand nextHand;
		while ((nextHand = myDeck.getNextHand()) != null )
		{
			noOfEachType[nextHand.getType()]++;
			noOfHands++;
		}
		long endTime = System.currentTimeMillis();
		
		// Display a summary of the results including the run time
		double totalPercent = 0;
		System.out.println("Type of Hand      Number of Hands   Percentage");

		for (int rank = 0; rank <= 9; rank++)
		{
			double percent = 100.0 * noOfEachType[rank] / noOfHands;
			totalPercent += percent;
			System.out.printf("%-18s%,12d%14.4f%n",PokerHand.TYPES[rank], noOfEachType[rank], percent);
		}
		System.out.printf("Totals    %,20d%14.4f%n", noOfHands, totalPercent);

		long totalTime = (endTime - startTime);
		System.out.println("Total time: " + (totalTime / 1000.0) + " seconds");
		
		System.out.println("Program complete");
	} // main method
} // AllPossiblePokerHands class

