package chess.chess;

public class Knight extends Piece{
	
	static final int maxMoves = 8;
	
	//String pieceType = "knight";
	
	
	public Knight(Tile currentTile, String color) {
		super(currentTile, color);
		if (color.equals("white")) {tag = "wN";} else {tag = "bN";}
		pieceType = "knight";
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
	
	public void move(Tile end) {
		
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
		
		if(endLet - startLet == 1 && endNum - startNum == 2 //file increases by 1, rank increases by 2
				|| startLet - endLet == 1 && startNum - endNum == 2 //file decreases by 1, rank decreases by 2
				|| endLet - startLet == 1 && startNum - endNum == 2 //file increases by 1, rank decreases by 2
				|| startLet - endLet == 1 && endNum - startNum == 2 //file decreases by 1, rank increases by 2
				|| endLet - startLet == 2 && endNum - startNum == 1 //file increases by 2, rank increases by 1
				|| startLet - endLet == 2 && startNum - endNum == 1 //file decreases by 2, rank decreases by 1
				|| endLet - startLet == 2 && startNum - endNum == 1 //file increases by 2, rank decreases by 1
				|| startLet - endLet == 2 && endNum - startNum == 1) { //file decreases by 2, rank increases by 1
			
			return true;
			
		} else {
		
		return false;	
		
		}
	}

	public Tile[] possibleMove()
	{
		Tile[] reachableTiles = new Tile[maxMoves];
		
		int x = currentTile.letter_rank;
		int y = currentTile.number_rank;
		int count = 0;
		
		if((x+1 <= 7) && (y+2 <= 7)) {	//file increases by 1, rank increases by 2
			
			if(Board.chess_board[x+1][y+2].isOccupied == true) {		
				if(!Board.chess_board[x+1][y+2].occupying_piece.color.equals(color)) {						
					reachableTiles[count] = Board.chess_board[x+1][y+2];
					count++;
				}			
			} else {			
				reachableTiles[count] = Board.chess_board[x+1][y+2];
				count++;		
			}	
		}
		
		if((x-1 >= 0) && (y+2 <= 7)) {
			
			if(Board.chess_board[x-1][y+2].isOccupied == true) {		
				if(!Board.chess_board[x-1][y+2].occupying_piece.color.equals(color)) {							
					reachableTiles[count] = Board.chess_board[x-1][y+2];
					count++;
				}			
			} else {			
				reachableTiles[count] = Board.chess_board[x-1][y+2];
				count++;		
			}				
		}
		
		if((x-1 >= 0) && (y-2 >= 0)) {	
			
			if(Board.chess_board[x-1][y-2].isOccupied == true) {		
				if(!Board.chess_board[x-1][y-2].occupying_piece.color.equals(color)) {							
					reachableTiles[count] = Board.chess_board[x-1][y-2];
					count++;
				}			
			} else {			
				reachableTiles[count] = Board.chess_board[x-1][y-2];
				count++;		
			}				
		}
		
		if((x+1 <= 7) && (y-2 >= 0)) {	
			
			if(Board.chess_board[x+1][y-2].isOccupied == true) {		
				if(!Board.chess_board[x+1][y-2].occupying_piece.color.equals(color)) {							
					reachableTiles[count] = Board.chess_board[x+1][y-2];
					count++;
				}			
			} else {			
				reachableTiles[count] = Board.chess_board[x+1][y-2];
				count++;		
			}
		}
		
		if((x+2 <= 7) && (y+1 <= 7)) {	
			
			if(Board.chess_board[x+2][y+1].isOccupied == true) {		
				if(!Board.chess_board[x+2][y+1].occupying_piece.color.equals(color)) {							
					reachableTiles[count] = Board.chess_board[x+2][y+1];
					count++;
				}			
			} else {			
				reachableTiles[count] = Board.chess_board[x+2][y+1];
				count++;		
			}
		}
		
		if((x-2 >= 0) && (y-1 >= 0)) {
			
			if(Board.chess_board[x-2][y-1].isOccupied == true) {		
				if(!Board.chess_board[x-2][y-1].occupying_piece.color.equals(color)) {							
					reachableTiles[count] = Board.chess_board[x-2][y-1];
					count++;
				}			
			} else {			
				reachableTiles[count] = Board.chess_board[x-2][y-1];
				count++;		
			}
		}
		
		if((x-2 >= 0) && (y+1 <= 7)) { 
			
			if(Board.chess_board[x-2][y+1].isOccupied == true) {		
				if(!Board.chess_board[x-2][y+1].occupying_piece.color.equals(color)) {							
					reachableTiles[count] = Board.chess_board[x-2][y+1];
					count++;
				}			
			} else {			
				reachableTiles[count] = Board.chess_board[x-2][y+1];
				count++;		
			}
		}
		
		if((x+2 <= 7) && (y-1 >= 0)) {
			
			if(Board.chess_board[x+2][y-1].isOccupied == true) {		
				if(!Board.chess_board[x+2][y-1].occupying_piece.color.equals(color)) {							
					reachableTiles[count] = Board.chess_board[x+2][y-1];
					count++;
				}			
			} else {			
				reachableTiles[count] = Board.chess_board[x+2][y-1];
				count++;		
			}
		}
					
		return reachableTiles;
	}
	
	public boolean validOutOfCheck(Tile end_tile){return true;}

}
