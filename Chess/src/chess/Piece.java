package chess;
/*This class outlines the methods that the various chess pieces will utilize. All the 
 * individual piece classes will be subclasses of this. 
 */
public abstract class Piece {
	
	public Tile currentTile;
	public String color; 
	
	public Piece(Tile currentTile)
	{
		this.currentTile = currentTile;
	}
	
	public abstract boolean isValidPath(Tile start, Tile end);
	
	public abstract void move();
	
	public abstract Tile[] possibleMove();

}
