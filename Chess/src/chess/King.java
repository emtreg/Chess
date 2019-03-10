package chess;

public class King extends Piece{
	
	String pieceType = "king";
	
	public King(Tile currentTile, String color)
	{
		super(currentTile, color);
		if (color.equals("white"))
		{
			tag = "wK";
		}
		else
		{
			tag = "bK";
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
