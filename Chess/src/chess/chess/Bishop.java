package chess.chess;

public class Bishop extends Piece {
	
	static final int maxMoves = 13;
	
	//String pieceType = "bishop";

	public Bishop(Tile currentTile, String color) {
		super(currentTile, color);
		if(color.equals("white")) {tag = "wB";} else {tag = "bB";}
		pieceType = "bishop";
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
	
	@Override
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
	
	
	/*if file and rank both increase/decrease by the same amount, file increases and rank decreases by the same amount,
	 * or file decreases and rank increases by the same amount then it is a valid (diagonal) move
	 */
	
	
	@Override
	public boolean isValidPath(Tile start, Tile end) {
		
		int startLet = start.letter_rank;
		int startNum = start.number_rank;
		int endLet = end.letter_rank;
		int endNum = end.number_rank;
		
		if(startLet - endLet == startNum - endNum || endLet - startLet == endNum - startNum
				|| startLet - endLet == endNum - startNum || endLet - startLet == startNum - endNum) {
			
			return true;
		}
		
		return false;
	}

	
	@Override
	public Tile[] possibleMove() {

		Tile[] possibleMoves = new Tile[maxMoves];
			
		int count = 0;
		
		//check upper right diagonals
		
		for(int x = currentTile.letter_rank + 1, y = currentTile.number_rank + 1; x <= 7 && y <= 7; x++, y++) {
			
			if(Board.chess_board[x][y].isOccupied) {			
				if(Board.chess_board[x][y].getOccupyingPiece().color.equals(color)) {				
					break;			
				} else {					
					possibleMoves[count] = Board.chess_board[x][y];
					count++;
					break;
				}			
			} else {				
				possibleMoves[count] = Board.chess_board[x][y];
				count++;
			}			
		}
		
		//check lower left diagonals
			
		for(int x = currentTile.letter_rank - 1, y = currentTile.number_rank - 1; x >= 0 && y >= 0; x--, y--) {
			
			if(Board.chess_board[x][y].isOccupied) {			
				if(Board.chess_board[x][y].getOccupyingPiece().color.equals(color)) {				
					break;			
				} else {					
					possibleMoves[count] = Board.chess_board[x][y];
					count++;
					break;
				}			
			} else {				
				possibleMoves[count] = Board.chess_board[x][y];
				count++;
			}			
		}
		
		//check lower right diagonals
		
		for(int x = currentTile.letter_rank + 1, y = currentTile.number_rank - 1; x <= 7 && y >= 0; x++, y--) {
			
			if(Board.chess_board[x][y].isOccupied) {			
				if(Board.chess_board[x][y].getOccupyingPiece().color.equals(color)) {				
					break;			
				} else {					
					possibleMoves[count] = Board.chess_board[x][y];
					count++;
					break;
				}			
			} else {				
				possibleMoves[count] = Board.chess_board[x][y];
				count++;
			}			
		}
		
		//check upper left diagonals
		
		for(int x = currentTile.letter_rank - 1, y = currentTile.number_rank + 1; x >= 0 && y <= 7; x--, y++) {
			
			if(Board.chess_board[x][y].isOccupied) {			
				if(Board.chess_board[x][y].getOccupyingPiece().color.equals(color)) {				
					break;			
				} else {					
					possibleMoves[count] = Board.chess_board[x][y];
					count++;
					break;
				}			
			} else {				
				possibleMoves[count] = Board.chess_board[x][y];
				count++;
			}			
		}
		
		return possibleMoves;
	}
	
	public boolean validOutOfCheck(Tile end_tile){return true;}
	
}