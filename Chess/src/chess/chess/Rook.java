package chess.chess;

public class Rook extends Piece {
	
	String pieceType = "rook";
	boolean first_move = true;
	
	public Rook(Tile currentTile, String color)
	{
		super(currentTile, color);
		if (color.equals("white")){tag = "wR";} else {tag = "bR";}
	}
	
	public boolean move_check(Tile end)
	{
		Tile moves[] = possibleMove();
		for (int i = 0; i < moves.length; i++){
			if (end.equals(moves[i]))
			{return true;}
		}
		return false;
	}
	
	//check if it is a valid move then check that the end tile is currently reachable
	//by the piece. If so, update the currentTile to the end tile.
	public void move(Tile end)
	{
		int end_x = end.letter_rank;
		int end_y = end.number_rank;
		int start_x = currentTile.letter_rank;
		int start_y = currentTile.number_rank;
		
		if (isValidPath(Board.chess_board[start_x][start_y], Board.chess_board[end_x][end_y]) == true)
		{
			Tile moves[] = possibleMove();
			for (int i = 0; i < moves.length; i++)
			{
				if (end.equals(moves[i]))
				{
					Board.chess_board[end_x][end_y].isOccupied = true;
					Board.chess_board[start_x][start_y].isOccupied = false;
					currentTile = end;
					first_move = false;
					break;
				}
			}
		}
	}
	
	//If one of the ranks is the same between start to end, it is a valid move.
	public boolean isValidPath(Tile start, Tile end)
	{
		if ((start.number_rank != end.number_rank) && (start.letter_rank != end.letter_rank))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public Tile[] possibleMove()
	{
		int cnt = 0;
		Tile[] reachableTiles = new Tile[20];
				
		//First check forward tiles
		for (int i = currentTile.letter_rank+1; i < 8; i++)
		{
			if (Board.chess_board[i][currentTile.number_rank].isOccupied == false)
			{
				reachableTiles[cnt] = Board.chess_board[i][currentTile.number_rank];
				cnt++;
			}
			else
			{
				if (Board.chess_board[i][currentTile.number_rank].occupying_piece.color.equals(color))
				{break;}
				else
				{
					reachableTiles[cnt] = Board.chess_board[i][currentTile.number_rank];
					cnt++;
					break;
				}
			}
		}
		
		//Then check backward tiles
		for (int i = currentTile.letter_rank-1; i >= 0; i--)
		{
			if (Board.chess_board[i][currentTile.number_rank].isOccupied == false)
			{
				reachableTiles[cnt] = Board.chess_board[i][currentTile.number_rank];
				cnt++;
			}
			else
			{
				if (Board.chess_board[i][currentTile.number_rank].occupying_piece.color.equals(color))
				{
					break;
				}
				else
				{
					reachableTiles[cnt] = Board.chess_board[i][currentTile.number_rank];
					cnt++;
					break;
				}
			}
		}
		
		//Then check right tiles
		for (int i = currentTile.number_rank+1; i < 8; i++)
		{
			if (Board.chess_board[currentTile.letter_rank][i].isOccupied == false)
			{
				reachableTiles[cnt] = Board.chess_board[i][currentTile.number_rank];
				cnt++;
			}
			else
			{
				if (Board.chess_board[currentTile.letter_rank][i].occupying_piece.color.equals(color))
				{
					break;
				}
				else
				{
					reachableTiles[cnt] = Board.chess_board[i][currentTile.number_rank];
					cnt++;
					break;
				}
			}
		}
		
		//Finally check left tiles
		for (int i = currentTile.number_rank-1; i >= 0; i--)
		{
			if (Board.chess_board[currentTile.letter_rank][i].isOccupied == false)
			{
				reachableTiles[cnt] = Board.chess_board[i][currentTile.number_rank];
				cnt++;
			}
			else
			{
				if (Board.chess_board[currentTile.letter_rank][i].occupying_piece.color.equals(color))
				{
					break;
				}
				else
				{
					reachableTiles[cnt] = Board.chess_board[i][currentTile.number_rank];
					cnt++;
					break;
				}
			}
		}
		
		/*for (int i = 0; i < cnt; i++){System.out.print(reachableTiles[i].letter_rank
				+ "," + reachableTiles[i].number_rank + "\t");}
		System.out.println("\n");*/
		
		return reachableTiles;
	}
	
	public boolean validOutOfCheck(Tile end_tile){return true;}

}