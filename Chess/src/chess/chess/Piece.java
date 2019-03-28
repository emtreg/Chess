package chess;

/** 
 * The Piece class outlines the methods that the various chess pieces utilize
 * 
 *  @author Justin Saganowski
 *  @author Emily Tregelles
 *  */


public abstract class Piece {
	
	public Tile currentTile;
	public String color;
	public String tag; 
	public String pieceType;
	public boolean first_move;
	
	
	/** constructor for Piece object
	 * @param currentTile tile where piece is currently positioned
	 * @param color color of piece
	 */
	
	public Piece(Tile currentTile, String color)
	{
		this.currentTile = currentTile;
		this.color = color;
	}
	
	/** The isValidPath method checks if the path taken by a piece from start to end tile is legal
	 * @param start tile where piece is currently located
	 * @param end destination tile of piece
	 * @return boolean
	 */

	public abstract boolean isValidPath(Tile start, Tile end);
	
	/** The move method checks if it is a valid move and that the end tile is currently reachable 
	 * by the piece. If so, update the currentTile to the end tile.
	 * @param end tile destination tile
	 * @return boolean
	 */
	
	public abstract void move(Tile end);
	
	/** The possibleMove method determines which locations on the board can be legally reached by the piece
	 * @return array of tiles that piece can legally move to
	 */
	
	public abstract Tile[] possibleMove();
	
	/** The validOutOfCheck method checks if moving a king to the specified tile will successfully get it out of check
	   * @param end_tile end tile of king piece
	   * @return true if move gets king out of check, false otherwise
	   */
	
	public abstract boolean validOutOfCheck(Tile end_tile); 
	
	/** The move_check method determines if a move is legal
	 * @param end destination tile
	 * @return boolean
	 */
	
	public abstract boolean move_check(Tile end); 

}
