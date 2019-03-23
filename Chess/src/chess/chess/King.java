package chess;

public class King extends Piece{
	
	String pieceType = "king";
	
	static final int maxMoves = 8;
	boolean first_move = true;
	
	public King(Tile currentTile, String color) {
		
		super(currentTile, color);
		if (color.equals("white")) {tag = "wK";} else {tag = "bK";}}

	public void move(Tile end) {
		
		int end_x = end.letter_rank;
		int end_y = end.number_rank;
		int start_x = currentTile.letter_rank;
		int start_y = currentTile.number_rank;
		
		if (isValidPath(Board.chess_board[start_x][start_y], Board.chess_board[end_x][end_y]) == true)
		{
			
			if(end_x - start_x == 2 
				|| start_x - end_x == 2) {
				
				return;
				
			} else {
			
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
	}
	
	/*if:
	 *file and rank both increase/decrease by one or
	 *only file increases/decreases by one or
	 *only rank increases/decreases by one or
	 *file decreases by one and rank increases by one or
	 *file increases by one and rank decreases by one 
	 *the move is valid (moves one either laterally or diagonally)
	 **/
	
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
	
	/*helper method for possibleMoves, checks occupation status/occupying piece color and adds tile to
	 * reachableTiles array if move is valid */
	
	public void checkTile(int file, int rank, int count, Tile[] reachableTiles) {
		
		if(Board.chess_board[file][rank].isOccupied == true) {		
			if(!Board.chess_board[file][rank].occupying_piece.color.equals(color)) {				
				
				reachableTiles[count] = Board.chess_board[file][rank];
				count++;
			}			
		} else {			
			reachableTiles[count] = Board.chess_board[file][rank];
			count++;		
		}
	
	}

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

}
