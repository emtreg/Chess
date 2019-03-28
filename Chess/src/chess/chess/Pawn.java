package chess;

/** The Pawn class represents a pawn object
 * 
 *  @author Justin Saganowski
 *  @author Emily Tregelles
 *  */

public class Pawn extends Piece{
	
	/** constructor for Pawn object
	 * @param currentTile tile where piece is currently positioned
	 * @param color color of piece
	 */
	
	public Pawn(Tile currentTile, String color)
	{
		super(currentTile, color);
		if (color.equals("white")){	tag = "wp";}
		else{	tag = "bp";}
		
		pieceType = "pawn";
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
	 * @return nothing
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
	
	/** The isValidPath method checks if the pawn is moving one space forward or two spaces forward if on its first move
	 * or if pawn is moving one space diagonally to capture an enemy piece
	 * @param start tile where piece is currently located
	 * @param end destination tile of piece
	 * @return boolean
	 */
	
	public boolean isValidPath(Tile start, Tile end)
	{
		/**If it is a white pawn's first move they can go two or one spaces ahead*/
		if ((first_move == true) && color.equals("white") && ((start.letter_rank - end.letter_rank) == 2)
				|| ((start.letter_rank - end.letter_rank) == 1) && start.number_rank == end.number_rank && end.isOccupied == false)
		{
			return true;
		}
		
		/**If it is a black pawn's first move they can go two or one spaces ahead*/
		if ((first_move == true) && (color.equals("white") == false) && ((end.letter_rank - start.letter_rank) == 2) 
				|| ((end.letter_rank - start.letter_rank) == 1) && start.number_rank == end.number_rank && end.isOccupied == false)
		{
			return true;
		}
		
		/**If it is not a white pawn's first move they can go one space ahead*/
		if ((first_move == false) && color.equals("white") && ((start.letter_rank - end.letter_rank) == 1) && 
				start.number_rank == end.number_rank && end.isOccupied == false)
		{
			return true;
		}
		
		/**If it is not a black pawn's first move they can go one space ahead*/
		if ((first_move == false) && (color.equals("white") == false) && ((end.letter_rank - start.letter_rank) == 1) && 
				start.number_rank == end.number_rank && end.isOccupied == false)
		{
			return true;
		}
		
		/**If a there is an enemy piece in one of two forward diagonal spaces, a white pawn can take that piece and move there*/
		if (color.equals("white") && ((start.letter_rank - end.letter_rank) == 1) && 
				(((start.number_rank - end.number_rank) == 1) || ((end.number_rank - start.number_rank) == 1)))
		{
			if (end.isOccupied == true && end.occupying_piece.color.equals("white") == false)
			{
				return true;
			}
		}
		
		/**If there is an enemy piece in one of two forward diagonal spaces, a black pawn can take that piece and move there*/
		if ((!(color.equals("white"))) && ((end.letter_rank - start.letter_rank) == 1) &&
				((start.number_rank - end.number_rank) == 1) || ((end.number_rank - start.number_rank) == 1))
		{
			if (end.isOccupied == true && end.occupying_piece.color.equals("white"))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/** The possibleMove method determines which locations on the board can be legally reached by the piece
	 * @return array of tiles that piece can legally move to
	 */

	public Tile[] possibleMove()
	{
		Tile[] reachableTiles = new Tile[4];
		int cnt = 0;
		
		/**White pieces first*/
		
		if (color.equals("white"))
		{
			if (currentTile.letter_rank - 1 >= 0)
			{if (Board.chess_board[currentTile.letter_rank - 1][currentTile.number_rank].isOccupied == false)
			{
				reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank - 1][currentTile.number_rank];
				cnt++;
			}}
			
			if (currentTile.letter_rank - 2 >= 0)
			{if (first_move == true)
			{
				if (Board.chess_board[currentTile.letter_rank - 2][currentTile.number_rank].isOccupied == false)
			
				{
					reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank - 2][currentTile.number_rank];
					cnt++;
				}
			}}
			
			if (currentTile.letter_rank - 1 >= 0)
			{
				if (currentTile.number_rank - 1 >= 0)
				{
					if (Board.chess_board[currentTile.letter_rank-1][currentTile.number_rank-1].isOccupied == true && 
							Board.chess_board[currentTile.letter_rank-1][currentTile.number_rank-1].occupying_piece.color.equals("white") == false)
					{
						reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank-1][currentTile.number_rank-1];
						cnt++;
					}
				}
				if (currentTile.number_rank + 1 <= 7)
				{
					if (Board.chess_board[currentTile.letter_rank-1][currentTile.number_rank+1].isOccupied == true &&
							Board.chess_board[currentTile.letter_rank-1][currentTile.number_rank+1].occupying_piece.color.equals("white") == false)
					{
						reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank-1][currentTile.number_rank+1];
						cnt++;
					}
				}
			}
		}
		
		/**Now for the black pieces*/
		
		else if (color.equals("white") == false)
		{
			if (currentTile.letter_rank + 1 <= 7)
			{if (Board.chess_board[currentTile.letter_rank + 1][currentTile.number_rank].isOccupied == false)
			{
				reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank + 1][currentTile.number_rank];
				cnt++;
			}}
			
			if (currentTile.letter_rank + 2 <= 7)
			{if (first_move == true)
			{
				if (Board.chess_board[currentTile.letter_rank + 2][currentTile.number_rank].isOccupied == false)
			
				{
					reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank + 2][currentTile.number_rank];
					cnt++;
				}
			}}
			
			if (currentTile.letter_rank + 1 <= 7)
			{
				if (currentTile.number_rank - 1 >= 0)
				{
					if (Board.chess_board[currentTile.letter_rank+1][currentTile.number_rank-1].isOccupied == true && 
							Board.chess_board[currentTile.letter_rank+1][currentTile.number_rank-1].occupying_piece.color.equals("white"))
					{
						reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank+1][currentTile.number_rank-1];
						cnt++;
					}
				}
				if (currentTile.number_rank + 1 <= 7)
				{
					if (Board.chess_board[currentTile.letter_rank+1][currentTile.number_rank+1].isOccupied == true &&
							Board.chess_board[currentTile.letter_rank+1][currentTile.number_rank+1].occupying_piece.color.equals("white"))
					{
						reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank+1][currentTile.number_rank+1];
						cnt++;
					}
				}
			}
		}
		
		return reachableTiles;
	}
	
	public boolean validOutOfCheck(Tile end_tile){return true;}

}