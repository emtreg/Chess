package chess;

public class Knight extends Piece{
	
	static final int maxMoves = 8;
	
	String pieceType = "knight";
	
	
	public Knight(Tile currentTile, String color) {
		super(currentTile, color);
		
		if (color.equals("white")) {
			tag = "wN";
			
		} else {
			tag = "bN";
		}
	}
	
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
	
	/*if file increases/decreases by 1 and rank increases/decreases by 2 or if
	 * file increases/decreases by 2 and rank increases/decreases by 1 the move is valid (L-shaped)*/
	
	public boolean isValidPath(Tile start, Tile end) {
		
		int startLet = start.letter_rank;
		int startNum = start.number_rank;
		int endLet = end.letter_rank;
		int endNum = end.number_rank;
		
		if(endLet - startLet == 1 && endNum - startNum == 2 
				|| startLet - endLet == 1 && startNum - endNum == 2 
				|| endLet - startLet == 1 && startNum - endNum == 2 
				|| startLet - endLet == 1 && endNum - startNum == 2 
				|| endLet - startLet == 2 && endNum - startNum == 1
				|| startLet - endLet == 2 && startNum - endNum == 1
				|| endLet - startLet == 2 && startNum - endNum == 1
				|| startLet - endLet == 2 && endNum - startNum == 1) {
			
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

	public Tile[] possibleMove()
	{
		Tile[] reachableTiles = new Tile[maxMoves];
		
		int x = currentTile.letter_rank;
		int y = currentTile.number_rank;
		int count = 0;
		int file = currentTile.letter_rank;
		int rank = currentTile.number_rank;
		
		if((x+1) < 8 && (y+2) < 8) {	
			
			file = x+1;
			rank = y+2;
			
			checkTile(file, rank, count, reachableTiles);
	
		}
		
		if((x-1) >= 0 && (y+2) < 8) {
			
			file = x-1;
			rank = y+2;
			
			checkTile(file, rank, count, reachableTiles);
				
		}
		
		if((x-1) >= 0 && (y-2) >= 0) {	
			
			file = x-1;
			rank = y-2;
			
			checkTile(file, rank, count, reachableTiles);	
		}
		
		if((x+1) < 8 && (y-2) >= 0) {	
			
			file = x+1;
			rank = y-2;
			
			checkTile(file, rank, count, reachableTiles);	
		}
		
		if((x+2) < 8 && (y+1) < 8) {	
			
			file = x+2;
			rank = y+1;
			
			checkTile(file, rank, count, reachableTiles);		
		}
		
		if((x-2) >= 0 && (y-1) >= 0) {
			
			file = x-2;
			rank = y-1;
			
			checkTile(file, rank, count, reachableTiles);
		}
		
		if((x-2) >= 0 && (y+1) < 8) { 
			
			file = x-2;
			rank = y+1;
			
			checkTile(file, rank, count, reachableTiles);
		}
		
		if((x+2) < 8 && (y-1) >= 0) {
			
			file = x+2;
			rank = y-1;
			
			checkTile(file, rank, count, reachableTiles);
		}
					
		return reachableTiles;
	}
}
