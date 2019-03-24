package chess.chess;
/*This class outlines the methods that the various chess pieces will utilize. All the 
 * individual piece classes will be subclasses of this. 
 */
public abstract class Piece {
	
	public Tile currentTile;
	public String color;
	public String tag; //This is what is shown on the board, (ex. wp for White Pawn)
	public String pieceType;
	public boolean first_move;
	
	public Piece(Tile currentTile, String color)
	{
		this.currentTile = currentTile;
		this.color = color;
	}
	
	public abstract boolean isValidPath(Tile start, Tile end);
	
	public abstract void move(Tile end);
	
	public abstract Tile[] possibleMove();
	
	public abstract boolean validOutOfCheck(Tile end_tile); //This function checks if moving the king to the end tile will get it out of check.
	
	public abstract boolean move_check(Tile end); //This function checks the end tile is in possibleMove() of the start piece.

}
