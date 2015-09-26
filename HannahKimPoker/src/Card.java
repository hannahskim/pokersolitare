import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Comparator;

import javax.swing.ImageIcon;


/** Keeps track of Card object
* @author ICS4U Hannah Kim
* @version October 2013
*/

public class Card extends Rectangle implements Comparable<Card>
{
	// D-1, C-2, H-3, S-4
	private int suit;
	// 2-10, J-11, Q-12, K-13, A-14
	private int rank;
	private boolean isFaceUp;
	
	private static final String RANKS = " A23456789TJQK";
	private static final String SUITS = " DCHS";
	
    // Constant Comparator object for comparing cards by suits
    public static final Comparator < Card >
	RANK_ORDER = new RankOrder ();
    
    // Code to keep track of an image for each Card
    private final static Image background = new ImageIcon("images\\redback.png").getImage();
    public final static int WIDTH = background.getWidth(null);
    public final static int HEIGHT = background.getHeight(null);
    private Image image;
	
    /** Constructor to make a card
     * @param rank the given rank
     * @param suit the given suit
     */
	public Card (int rank, int suit)
	{
		super(0, 0, 0, 0);
		this.rank = rank;
		this.suit = suit;
	
		// Load up the appropriate image file for this card
		String imageFileName = “” + “ dchs”.charAt(suit) + rank + “.png”;
		imageFileName = “images\\” + imageFileName;
		image = new ImageIcon(imageFileName).getImage();
		
		// Set the size of the card based on the image size
		setSize(image.getWidth(null), image.getHeight(null));
	}
	
	/** Draws a card in a Graphics context
	* @param g Graphics to draw the card in
	*/ public void draw(Graphics g)
	{
	if (isFaceUp)
	g.drawImage(image, x, y, null);
	else
	g.drawImage(background, x, y, null);
	}
	
	/** Calls the translate method from the Rectangle class
     */
	public void move (Point initialPos, Point finalPos)
	{
	translate (finalPos.x - initialPos.x, finalPos.y - initialPos.y);
	}
	
    /** Constructor to make a new card with given string
     * @param cardStr the given card in string
     */
	public Card (String cardStr)
	{
		// Rank
	rank = RANKS.indexOf(cardStr.charAt(0)); 

	// Suit
	suit = SUITS.indexOf(cardStr.charAt(1));
	}
	
	// "JS" "5C" "TD"
    /** Changes card as a String ("JS" "5C" "TD")
     * @return the card as a String
     */
	public String toString()
	{
	    return String.format("%c%c", RANKS.charAt(rank), SUITS.charAt(suit));
	}
	
    /** Gets the rank of the card
     * @return the rank
     */
	public int getRank()
	{
		return rank;
	}
	
    /** Gets the suit of the card
     * @return the suit
     */
	public int getSuit()
	{
		return suit;
	}
	
    /** Checks if the card is ace
     * @return true if it's ace or false if it isn't
     */
	public boolean isAce()
	{
		if (rank == 1)
			return true;
		return false;
	}

    /** Returns BlackJack value 
     * 2-10: Face value. J,Q,K: 10. A: 11
     * @return the value of the card
     */
	public int getValue()
	{
	if (rank > 10)
	    return 10;
	else if (this.isAce())
		return 11;
	return rank;
	}
	
    /** Compares the suits of each cards 
     * @param other the card to compare
     * @return a value < 0 if the suit of the card is smaller than other, 
     *         a value > 0, if the suit of the card is bigger than other and
     *         0, if the suit of the card are the same
     */
	public int compareTo(Card other)
	{
		// If rank is the same, compare suits
	if (this.suit - other.suit == 0)
	    return this.rank - other.rank;
	return this.suit - other.suit;
	}
	
    /** An inner Comparator class that compares ranks
    */
    private static class RankOrder implements Comparator < Card >
    {
	/** Compares the ranks of two Card objects
	* @param first the first card to compare
	* @param second the second card to compare
	* @return a value < 0 if the first rank has a lower suit, a
	*                       a value < 0 if first rank has a higher value and
	*                       0 if the rank of the Cards are the same
	*/
	public int compare (Card first, Card second)
	{
	    if (first.rank < second.rank)
		return -1;
	    else if (first.rank > second.rank)
		return 1;
	    else
		return (first.compareTo(second));
	    }
    }
    
    /** Checks to see if the given object is a card and if it has the
     * same suit and rank as this card
     * @param other the object of compare to this card
     * @return true if the given object is a card
     *                  with the same suit and rank as the card
     */
    public boolean equals (Object other)
    {
	if (this.getClass()!= other.getClass())
		return false;
	Card otherCard = (Card)other;
	return this.suit == otherCard.suit &&
			this.rank == otherCard.rank;
    }

}
