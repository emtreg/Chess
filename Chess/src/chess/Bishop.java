package chess;

public class Bishop extends Piece{
	
	String pieceType = "bishop";
	
	public Bishop(Tile currentTile, String color)
	{
		super(currentTile, color);
		if (color.equals("white"))
		{
			tag = "wB";
		}
		else
		{
			tag = "bB";
		}
	}
	
	public void move(Tile end)
	{
		
	}
	
	public boolean isValidPath(Tile start, Tile end)
	{
		return true;
	}

	public Tile[] possibleMove()
	{
		Tile[] reachableTiles = new Tile[14];
		return reachableTiles;
	}
}
