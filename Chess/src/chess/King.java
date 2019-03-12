package chess;

public class King extends Piece{
	
	String pieceType = "king";
	
	static final int maxMoves = 8;
	boolean first_move = true;
	
	public King(Tile currentTile, String color) {
		
		super(currentTile, color);
		
		if (color.equals("white")) {
			tag = "wK";
			
		} else {
			tag = "bK";
		}
	}
	

	public void move(Tile end) {
		
		if (isValidPath(currentTile, end) == true) {
			Tile moves[] = possibleMove();
			
			for (int i = 0; i < moves.length; i++) {
				
				if (end.equals(moves[i])) {			
					currentTile.isOccupied = false;
					currentTile = end;
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
				|| endLet - startLet == 1 && startNum - endNum == 1) {
					
			return true;
		}
		
		return false;
	}
	
	/*helper method for possibleMoves, checks occupation status/occupying piece color and adds tile to
	 * reachableTiles array if move is valid */
	
	public void checkTile(int file, int rank, int count, Tile[] reachableTiles) {
		
		if(Board.chess_board[file][rank].isOccupied == true) {		
			if(Board.chess_board[file][rank].occupying_piece.color.equals(color)) {				
				return;			
			} else {				
				reachableTiles[count] = Board.chess_board[file][rank];
				count++;
			}			
		} else {			
			reachableTiles[count] = Board.chess_board[file][rank];
			count++;		
		}
		
		file = currentTile.letter_rank;
		rank = currentTile.number_rank;
		
	}

	public Tile[] possibleMove() {
		
		Tile[] reachableTiles = new Tile[maxMoves];
		
		int x = currentTile.letter_rank;
		int y = currentTile.number_rank;
		int count = 0;
		int file = currentTile.letter_rank;
		int rank = currentTile.number_rank;
		
		if((x+1) < 8) {
			
			file = x+1;		
			checkTile(file, rank, count, reachableTiles);		
		}
		
		if((x-1) >= 0) {
			
			file = x-1;
			checkTile(file, rank, count, reachableTiles);		
		}
		
		if((y+1) < 8) {
			
			rank = y+1;
			checkTile(file, rank, count, reachableTiles);		
		}
		
		if((y-1) >= 0) {
			
			rank = y-1;
			checkTile(file, rank, count, reachableTiles);			
		}
		
		if((x+1) < 8 && (y+1) < 8) {
			
			file = x+1;
			rank = y+1;
			checkTile(file, rank, count, reachableTiles);		
		}
		
		if((x-1) >= 0 && (y-1) >= 0) {
			
			file = x-1;
			rank = y-1;
			checkTile(file, rank, count, reachableTiles);		
		}
		
		if((x+1) < 8 && (y-1) >= 0) {
			
			file = x+1;
			rank = y-1;
			checkTile(file, rank, count, reachableTiles);		
		}
		
		if((x-1) >= 0 && (y+1) < 8) {
			
			file = x-1;
			rank = y+1;
			checkTile(file, rank, count, reachableTiles);
		}
			
		return reachableTiles;
	}

}
