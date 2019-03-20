package chess;

public class Pawn extends Piece{
	
	String pieceType = "pawn";
	boolean firstMove = true;
	
	public Pawn(Tile currentTile, String color)
	{
		super(currentTile, color);
		if (color.equals("white"))
		{
			tag = "wp";
		}
		else
		{
			tag = "bp";
		}
	}
	
	public void move(Tile end)
	{
		int end_x = end.letter_rank;
		int end_y = end.number_rank;
		int start_x = currentTile.letter_rank;
		int start_y = currentTile.number_rank;
		
		if (isValidPath(Board.chess_board[start_x][start_y], Board.chess_board[end_x][end_y]) == true)
		{
			Board.chess_board[end_x][end_y].isOccupied = true;
			Board.chess_board[start_x][start_y].isOccupied = false;
			
			currentTile = end;
		}
	}
	
	public boolean isValidPath(Tile start, Tile end)
	{
		//If it is a white pawn's first move they can go two or one spaces ahead
		if ((firstMove == true) && color.equals("white") && ((start.letter_rank - end.letter_rank) == 2)
				|| ((start.letter_rank - end.letter_rank) == 1) && start.number_rank == end.number_rank && end.isOccupied == false)
		{
			firstMove = false;
			return true;
		}
		
		//If it is a black pawn's first move they can go two or one spaces ahead
		if ((firstMove == true) && (color.equals("white") == false) && ((end.letter_rank - start.letter_rank) == 2) 
				|| ((end.letter_rank - start.letter_rank) == 1) && start.number_rank == end.number_rank && end.isOccupied == false)
		{
			firstMove = false;
			return true;
		}
		
		//If it is not a white pawn's first move they can go one space ahead
		if ((firstMove == false) && color.equals("white") && ((start.letter_rank - end.letter_rank) == 1) && 
				start.number_rank == end.number_rank && end.isOccupied == false)
		{
			return true;
		}
		
		//If it is not a black pawn's first move they can go one space ahead
		if ((firstMove == false) && (color.equals("white") == false) && ((end.letter_rank - start.letter_rank) == 1) && 
				start.number_rank == end.number_rank && end.isOccupied == false)
		{
			return true;
		}
		
		//If a there is an enemy piece in one of two forward diagonal spaces, a white pawn can take that piece and move there.
		if (color.equals("white") && ((start.letter_rank - end.letter_rank) == 1) && 
				(((start.number_rank - end.number_rank) == 1) || ((end.number_rank - start.number_rank) == 1)))
		{
			if (end.isOccupied == true && end.occupying_piece.color.equals("white") == false)
			{
				if (firstMove == true){firstMove=false;}
				return true;
			}
		}
		
		//If there is an enemy piece in one of two forward diagonal spaces, a black pawn can take that piece and move there.
		if ((!(color.equals("white"))) && ((end.letter_rank - start.letter_rank) == 1) &&
				((start.number_rank - end.number_rank) == 1) || ((end.number_rank - start.number_rank) == 1))
		{
			if (end.isOccupied == true && end.occupying_piece.color.equals("white"))
			{
				if (firstMove == true){firstMove=false;}
				return true;
			}
		}
		
		return false;
	}

	public Tile[] possibleMove()
	{
		Tile[] reachableTiles = new Tile[4];
		int cnt = 0;
		
		//White pieces first
		if (color.equals("white"))
		{
			if (currentTile.letter_rank - 1 >= 0)
			{if (Board.chess_board[currentTile.letter_rank - 1][currentTile.number_rank].isOccupied == false)
			{
				reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank - 1][currentTile.number_rank];
				cnt++;
			}}
			
			if (currentTile.letter_rank - 2 >= 0)
			{if (firstMove == true)
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
		
		//Now for the black pieces
		else if (color.equals("white") == false)
		{
			if (currentTile.letter_rank + 1 <= 7)
			{if (Board.chess_board[currentTile.letter_rank + 1][currentTile.number_rank].isOccupied == false)
			{
				reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank + 1][currentTile.number_rank];
				cnt++;
			}}
			
			if (currentTile.letter_rank + 2 <= 7)
			{if (firstMove == true)
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
		
		//for (int i = 0; i < cnt; i++){System.out.print(reachableTiles[i].letter_rank + ", " + reachableTiles[i].number_rank + "\t");}
		//System.out.print("\n");
		
		return reachableTiles;
	}

}
