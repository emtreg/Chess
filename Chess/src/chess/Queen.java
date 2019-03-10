package chess;

public class Queen extends Piece{
	
	String pieceType = "queen";
	
	public Queen(Tile currentTile, String color)
	{
		super(currentTile, color);
		if (color.equals("white"))
		{
			tag = "wQ";
		}
		else
		{
			tag = "bQ";
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
