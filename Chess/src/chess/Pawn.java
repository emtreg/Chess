package chess;

public class Pawn extends Piece{
	
	String pieceType = "pawn";
	
	public Pawn(Tile currentTile, String color)
	{
		super(currentTile, color);
		if (color.equals("white"))
		{
			tag = "wp";
		}
		else
		{
			tag = "bp";
		}
	}
	
	/*This is the most basic implementation of move where there are no rules, any 
	 * move is legal. I am going to use this to debug then once this is working we 
	 * can add the actual rules for each piece */
	
	public void move(Tile end)
	{
		int end_x = end.letter_rank;
		int end_y = end.number_rank;
		int start_x = currentTile.letter_rank;
		int start_y = currentTile.number_rank;
		
		Board.chess_board[end_x][end_y].isOccupied = true;
		Board.chess_board[start_x][start_y].isOccupied = false;
		
		currentTile = end;
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
