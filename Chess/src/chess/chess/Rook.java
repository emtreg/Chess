package chess;

/** The Rook class represents a rook object
 * 
 *  @author Justin Saganowski
 *  @author Emily Tregelles
 *  */

public class Rook extends Piece {
	
	/** constructor for Rook object
	 * @param currentTile tile where piece is currently positioned
	 * @param color color of piece
	 */
	
	public Rook(Tile currentTile, String color)
	{
		super(currentTile, color);
		if (color.equals("white")){tag = "wR";} else {tag = "bR";}
		pieceType = "rook";
		first_move = true;
	}
	
	/** The move_check method determines if a move is legal
	 * @param end destination tile
	 * @return boolean
	 */
	
	public boolean move_check(Tile end)
	{
		Tile moves[] = possibleMove();
		for (int i = 0; i < moves.length; i++){
			if (end.equals(moves[i]))
			{return true;}
		}
		return false;
	}
	
	/** The move method checks if it is a valid move and that the end tile is currently reachable 
	 * by the piece. If so, update the currentTile to the end tile.
	 * @param end tile destination tile
	 * @return void
	 */
	
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
	
	/** The isValidPath method checks if the rook is moving laterally
	 * If one of the ranks is the same between start to end, it is a valid move
	 * @param start tile where piece is currently located
	 * @param end destination tile of piece
	 * @return boolean
	 */
	
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
	
	/** The possibleMove method determines which locations on the board can be legally reached by the piece
	 * @return array of tiles that piece can legally move to
	 */
	
	public Tile[] possibleMove()
	{
		int cnt = 0;
		Tile[] reachableTiles = new Tile[20];
				
		/**First check forward tiles*/
		
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
		
		/**Then check backward tiles*/
		
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
		
		/**Then check right tiles*/
		
		for (int i = currentTile.number_rank+1; i < 8; i++)
		{
			if (Board.chess_board[currentTile.letter_rank][i].isOccupied == false)
			{
				reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank][i];
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
					reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank][i];
					cnt++;
					break;
				}
			}
		}
		
		/**Finally check left tiles*/
		
		for (int i = currentTile.number_rank-1; i >= 0; i--)
		{
			if (Board.chess_board[currentTile.letter_rank][i].isOccupied == false)
			{
				reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank][i];
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
					reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank][i];
					cnt++;
					break;
				}
			}
		}
		
		return reachableTiles;
	}
	
	public boolean validOutOfCheck(Tile end_tile){return true;}

}