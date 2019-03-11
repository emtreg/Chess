package chess;

public class Bishop extends Piece {
	
	static final int maxMoves = 13;
	
	String pieceType = "bishop";

	public Bishop(Tile currentTile, String color) {
		super(currentTile, color);
		
		if(color.equals("white")) {
			
			tag = "wB";
			
		} else {
			
			tag = "bB";
		}
	}
	
	//check if it is a valid move then check that the end tile is currently reachable
	//by the piece. If so, update the currentTile to the end tile.
	
	@Override
	public void move(Tile end) {
		
		if (isValidPath(currentTile, end) == true) {
			
			Tile moves[] = possibleMove();
			
			for (int i = 0; i < moves.length; i++) {
				
				if (end.equals(moves[i])) {
					
					currentTile.isOccupied = false;
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
		
		for(int x = currentTile.letter_rank, y = currentTile.number_rank; x < 8 && y < 8; x++, y++) {
			
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
			
		for(int x = currentTile.letter_rank, y = currentTile.number_rank; x >= 0 && y >= 0; x--, y--) {
			
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
		
		for(int x = currentTile.letter_rank, y = currentTile.number_rank; x < 8 && y >= 0; x++, y--) {
			
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
		
		for(int x = currentTile.letter_rank, y = currentTile.number_rank; x >= 0 && y < 8; x--, y++) {
			
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
	
}