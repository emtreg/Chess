package chess;

/** The Queen class represents a queen object
 * 
 *  @author Justin Saganowski
 *  @author Emily Tregelles
 *  */

public class Queen extends Piece{
	
	static final int maxMoves = 27;
	
	/** constructor for Queen object
	 * @param currentTile tile where piece is currently positioned
	 * @param color color of piece
	 */
	
	public Queen(Tile currentTile, String color) {
		
		super(currentTile, color);
		if (color.equals("white")) {tag = "wQ";} else {tag = "bQ";}
		pieceType = "queen";
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
	
	public void move(Tile end) {
		
		int end_x = end.letter_rank;
		int end_y = end.number_rank;
		int start_x = currentTile.letter_rank;
		int start_y = currentTile.number_rank;
		
		if (isValidPath(Board.chess_board[start_x][start_y], Board.chess_board[end_x][end_y]) == true) {
			
			Tile moves[] = possibleMove();
			
			for (int i = 0; i < moves.length; i++) {
				
				if (end.equals(moves[i])) {
					Board.chess_board[end_x][end_y].isOccupied = true;
					Board.chess_board[start_x][start_y].isOccupied = false;
					currentTile = end;
					break;
				}
			}
		}
	}
	
	/** The isValidPath method checks if the queen is moving laterally or diagonally
	 * if only file changes or only rank changes it is a valid (lateral) move
	 * if file and rank both increase/decrease by the same amount, file increases and rank decreases by the same amount,
	 * or file decreases and rank increases by the same amount then it is a valid (diagonal) move
	 * @param start tile where piece is currently located
	 * @param end destination tile of piece
	 * @return boolean
	 */
	
	public boolean isValidPath(Tile start, Tile end) {
		
		int startLet = start.letter_rank;
		int startNum = start.number_rank;
		int endLet = end.letter_rank;
		int endNum = end.number_rank;
		
		if(startLet == endLet && startNum != endNum
				|| startLet != endLet && startNum == endNum
				|| startLet - endLet == startNum - endNum 
				|| endLet - startLet == endNum - startNum
				|| startLet - endLet == endNum - startNum 
				|| endLet - startLet == startNum - endNum) {
		
		return true;
		
		}
		
		return false;
	}
	
	/** The possibleMove method determines which locations on the board can be legally reached by the piece
	 * @return array of tiles that piece can legally move to
	 */

	public Tile[] possibleMove() {
		
		Tile[] reachableTiles = new Tile[maxMoves];
		int cnt = 0;
		
		/**First check forward tiles*/
		
		for (int i = currentTile.number_rank + 1; i < 8; i++) {
			if (Board.chess_board[currentTile.letter_rank][i].isOccupied == false) {
				reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank][i];
				cnt++;
			} else {
				if (Board.chess_board[currentTile.letter_rank][i].occupying_piece.color.equals(color)) {
					break;
				} else {
					reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank][i];
					cnt++;
					break;
				}
			}
		}
				
		/**Then check backward tiles*/
		
		for (int i = currentTile.number_rank - 1; i >= 0; i--) {
			if (Board.chess_board[currentTile.letter_rank][i].isOccupied == false) {
				reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank][i];
				cnt++;
			} else {
				if (Board.chess_board[currentTile.letter_rank][i].occupying_piece.color.equals(color)) {
					break;
				} else {
					reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank][i];
					cnt++;
					break;
				}
			}
		}
				
		/**Then check right tiles*/
		
		for (int i = currentTile.letter_rank + 1; i < 8; i++) {
			if (Board.chess_board[i][currentTile.number_rank].isOccupied == false) {
				reachableTiles[cnt] = Board.chess_board[i][currentTile.number_rank];
				cnt++;
			} else {
				if (Board.chess_board[i][currentTile.number_rank].occupying_piece.color.equals(color)) {
					break;
				} else {
					reachableTiles[cnt] = Board.chess_board[i][currentTile.number_rank];
					cnt++;
					break;
				}
			}
		}
				
		/**Finally check left tiles*/
		
		for (int i = currentTile.letter_rank - 1; i >= 0; i--) {
			if (Board.chess_board[i][currentTile.number_rank].isOccupied == false) {
				reachableTiles[cnt] = Board.chess_board[i][currentTile.number_rank];
				cnt++;
			} else {
				if (Board.chess_board[i][currentTile.number_rank].occupying_piece.color.equals(color)) {
					break;
				} else {
					reachableTiles[cnt] = Board.chess_board[i][currentTile.number_rank];
					cnt++;
					break;
				}
			}
		}
								
		/**check upper right diagonals*/
		
		for(int x = currentTile.letter_rank + 1, y = currentTile.number_rank + 1; x < 8 && y < 8; x++, y++) {		
			if(Board.chess_board[x][y].isOccupied) {			
				if(Board.chess_board[x][y].getOccupyingPiece().color.equals(color)) {				
					break;			
				} else {					
					reachableTiles[cnt] = Board.chess_board[x][y];
					cnt++;
					break;
				}			
			} else {				
				reachableTiles[cnt] = Board.chess_board[x][y];
				cnt++;
			}			
		}
				
		/**check lower left diagonals*/
		
		for(int x = currentTile.letter_rank - 1, y = currentTile.number_rank - 1; x >= 0 && y >= 0; x--, y--) {				
			if(Board.chess_board[x][y].isOccupied) {			
				if(Board.chess_board[x][y].getOccupyingPiece().color.equals(color)) {				
					break;			
				} else {					
					reachableTiles[cnt] = Board.chess_board[x][y];
					cnt++;
					break;
				}			
			} else {				
				reachableTiles[cnt] = Board.chess_board[x][y];
				cnt++;
			}			
		}
				
		/**check lower right diagonals*/
		
		for(int x = currentTile.letter_rank + 1, y = currentTile.number_rank - 1; x < 8 && y >= 0; x++, y--) {					
			if(Board.chess_board[x][y].isOccupied) {			
				if(Board.chess_board[x][y].getOccupyingPiece().color.equals(color)) {				
					break;			
				} else {					
					reachableTiles[cnt] = Board.chess_board[x][y];
					cnt++;
					break;
				}			
			} else {				
				reachableTiles[cnt] = Board.chess_board[x][y];
				cnt++;
			}			
		}
				
		/**check upper left diagonals*/
		
		for(int x = currentTile.letter_rank - 1, y = currentTile.number_rank + 1; x >= 0 && y < 8; x--, y++) {					
			if(Board.chess_board[x][y].isOccupied) {			
				if(Board.chess_board[x][y].getOccupyingPiece().color.equals(color)) {				
					break;			
				} else {					
					reachableTiles[cnt] = Board.chess_board[x][y];
					cnt++;
					break;
				}			
			} else {				
				reachableTiles[cnt] = Board.chess_board[x][y];
				cnt++;
			}			
		}
				
		return reachableTiles;
	}
	
	public boolean validOutOfCheck(Tile end_tile){return true;}


}
