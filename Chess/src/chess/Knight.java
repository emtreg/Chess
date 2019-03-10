package chess;

public class Knight extends Piece{
	
	String pieceType = "knight";
	
	public Knight(Tile currentTile, String color)
	{
		super(currentTile, color);
		if (color.equals("white"))
		{
			tag = "wN";
		}
		else
		{
			tag = "bN";
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
