package chess;

/** The Player class represents a player object
 * 
 *  @author Justin Saganowski
 *  @author Emily Tregelles
 *  */

public class Player {
	
	String color;
	int turn = 1;
	Piece graveyard[] = new Piece[16];
	boolean king_in_check = false;
	
	
	/** constructor for Player object
	 * @param color player's color
	 */
	
	public Player(String color)
	{
		this.color = color; 
	}

}
