package chess;

/** The King class represents a king object
 * 
 *  @author Justin Saganowski
 *  @author Emily Tregelles
 *  */

public class King extends Piece{
	
	/** constructor for King object
	 * @param currentTile tile where piece is currently positioned
	 * @param color color of piece
	 */
	
	static final int maxMoves = 8;
	
	public King(Tile currentTile, String color) {
		
		super(currentTile, color);
		if (color.equals("white")) {tag = "wK";} else {tag = "bK";}
		pieceType = "king";
		first_move = true;}
	
	
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

	public void move(Tile end) {
		
		int end_x = end.letter_rank;
		int end_y = end.number_rank;
		int start_x = currentTile.letter_rank;
		int start_y = currentTile.number_rank;
		
		if (isValidPath(Board.chess_board[start_x][start_y], Board.chess_board[end_x][end_y]) == true)
		{
			
			Tile moves[] = possibleMove();
			
			for (int i = 0; i < moves.length; i++) {
				
				if (end.equals(moves[i])) {
					
					Board.chess_board[end_x][end_y].isOccupied = true;
					Board.chess_board[start_x][start_y].isOccupied = false;
					currentTile = end;
					first_move = false;
					break;
				}
			}
		}
	}	
	

	/** The isValidPath method checks if the king is moving one space either laterally or diagonally
	 *if:
	 *file and rank both increase/decrease by one or
	 *only file increases/decreases by one or
	 *only rank increases/decreases by one or
	 *file decreases by one and rank increases by one or
	 *file increases by one and rank decreases by one 
	 *the move is valid (moves one either laterally or diagonally)
	 * @param start tile where piece is currently located
	 * @param end destination tile of piece
	 * @return boolean
	 */
	
	
	public boolean isValidPath(Tile start, Tile end) {
		
		int startLet = start.letter_rank;
		int startNum = start.number_rank;
		int endLet = end.letter_rank;
		int endNum = end.number_rank;
		
		if(startLet - endLet == 1 && startNum - endNum == 1 
				|| endLet - startLet == 1 && endNum - startNum == 1
				|| startLet == endLet && startNum - endNum == 1
				|| startLet == endLet && endNum - startNum == 1
				|| startLet - endLet == 1 && startNum == endNum
				|| endLet - startLet == 1 && startNum == endNum
				|| startLet - endLet == 1 && endNum - startNum == 1
				|| endLet - startLet == 1 && startNum - endNum == 1
				|| endLet - startLet == 2 
				|| startLet - endLet == 2) {
					
			return true;
		}
		
		return false;
	}
	
	/** The possibleMove method determines which locations on the board can be legally reached by the piece
	 * @return array of tiles that piece can legally move to
	 */

	public Tile[] possibleMove() {
		
		Tile[] reachableTiles = new Tile[maxMoves];
		
		int x = currentTile.letter_rank;
		int y = currentTile.number_rank;
		int count = 0;
	
		
		if(x+1 <= 7) {
			
			if(Board.chess_board[x+1][y].isOccupied == true) {		
				if(!Board.chess_board[x+1][y].occupying_piece.color.equals(color)) {				
					
					reachableTiles[count] = Board.chess_board[x+1][y];
					count++;
				}			
			} else {			
				reachableTiles[count] = Board.chess_board[x+1][y];
				count++;		
			}	
		}
		
		if(x-1 >= 0) {
			
			if(Board.chess_board[x-1][y].isOccupied == true) {		
				if(!Board.chess_board[x-1][y].occupying_piece.color.equals(color)) {				
					
					reachableTiles[count] = Board.chess_board[x-1][y];
					count++;
				}			
			} else {			
				reachableTiles[count] = Board.chess_board[x-1][y];
				count++;		
			}			
		}
		
		if(y+1 <= 7) {
			
			if(Board.chess_board[x][y+1].isOccupied == true) {		
				if(!Board.chess_board[x][y+1].occupying_piece.color.equals(color)) {				
					
					reachableTiles[count] = Board.chess_board[x][y+1];
					count++;
				}			
			} else {			
				reachableTiles[count] = Board.chess_board[x][y+1];
				count++;		
			}				
		}
		
		if(y-1 >= 0) {
			
			if(Board.chess_board[x][y-1].isOccupied == true) {		
				if(!Board.chess_board[x][y-1].occupying_piece.color.equals(color)) {				
					
					reachableTiles[count] = Board.chess_board[x][y-1];
					count++;
				}			
			} else {			
				reachableTiles[count] = Board.chess_board[x][y-1];
				count++;		
			}							
		}
		
		if((x+1 < 8) && (y+1 <= 7)) {
			
			if(Board.chess_board[x+1][y+1].isOccupied == true) {		
				if(!Board.chess_board[x+1][y+1].occupying_piece.color.equals(color)) {				
					
					reachableTiles[count] = Board.chess_board[x+1][y+1];
					count++;
				}			
			} else {			
				reachableTiles[count] = Board.chess_board[x+1][y+1];
				count++;		
			}					
		}
		
		if((x-1 >= 0) && (y-1 >= 0)) {
			
			if(Board.chess_board[x-1][y-1].isOccupied == true) {		
				if(!Board.chess_board[x-1][y-1].occupying_piece.color.equals(color)) {				
					
					reachableTiles[count] = Board.chess_board[x-1][y-1];
					count++;
				}			
			} else {			
				reachableTiles[count] = Board.chess_board[x-1][y-1];
				count++;		
			}				
		}
		
		if((x+1 <= 7) && (y-1 >= 0)) {
			
			if(Board.chess_board[x+1][y-1].isOccupied == true) {		
				if(!Board.chess_board[x+1][y-1].occupying_piece.color.equals(color)) {				
					
					reachableTiles[count] = Board.chess_board[x+1][y-1];
					count++;
				}			
			} else {			
				reachableTiles[count] = Board.chess_board[x+1][y-1];
				count++;		
			}		
		}
		
		if((x-1 >= 0) && (y+1 <= 7)) {
			
			if(Board.chess_board[x-1][y+1].isOccupied == true) {		
				if(!Board.chess_board[x-1][y+1].occupying_piece.color.equals(color)) {				
					
					reachableTiles[count] = Board.chess_board[x-1][y+1];
					count++;
				}			
			} else {			
				reachableTiles[count] = Board.chess_board[x-1][y+1];
				count++;		
			}		
		}
			
		return reachableTiles;
	}
	
	/**
	   * The validOutOfCheck method checks if moving a king to the specified tile will successfully get it out of check
	   * @param end_tile end tile of king piece
	   * @return boolean
	   */
	
	public boolean validOutOfCheck(Tile end_tile)
	{
		for (int i = 0; i < 8; i++)
		{ /**traverse the board*/
			for (int j = 0; j < 8; j++)
			{
				if (Board.chess_board[i][j].isOccupied)
				{
					if (!(Board.chess_board[i][j].occupying_piece.color.equals(color)))
					{ /**if the piece is an enemy piece, check the piece's possible move array and see if the proposed move is in it*/
						for (int k = 0; k < Board.chess_board[i][j].occupying_piece.possibleMove().length; k++)
						{
							if (Board.chess_board[i][j].occupying_piece.possibleMove()[k] != null)
							{
								if (Board.chess_board[i][j].occupying_piece.possibleMove()[k].equals(end_tile))
								{ /**if the proposed move is in one of these possibleMove arrays, it will not get the king out of check*/
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}

}
