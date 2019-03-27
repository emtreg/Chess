package chess;

import java.util.Scanner;

public class Pawn extends Piece{
	
	public Pawn(Tile currentTile, String color)
	{
		super(currentTile, color);
		if (color.equals("white")){	tag = "wp";}
		else{	tag = "bp";}
		
		pieceType = "pawn";
		first_move = true;
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
	
	public boolean isValidPath(Tile start, Tile end)
	{
		//If it is a white pawn's first move they can go two or one spaces ahead
		if ((first_move == true) && color.equals("white") && ((start.letter_rank - end.letter_rank) == 2)
				|| ((start.letter_rank - end.letter_rank) == 1) && start.number_rank == end.number_rank && end.isOccupied == false)
		{
			//first_move = false;
			return true;
		}
		
		//If it is a black pawn's first move they can go two or one spaces ahead
		if ((first_move == true) && (color.equals("white") == false) && ((end.letter_rank - start.letter_rank) == 2) 
				|| ((end.letter_rank - start.letter_rank) == 1) && start.number_rank == end.number_rank && end.isOccupied == false)
		{
			//first_move = false;
			return true;
		}
		
		//If it is not a white pawn's first move they can go one space ahead
		if ((first_move == false) && color.equals("white") && ((start.letter_rank - end.letter_rank) == 1) && 
				start.number_rank == end.number_rank && end.isOccupied == false)
		{
			return true;
		}
		
		//If it is not a black pawn's first move they can go one space ahead
		if ((first_move == false) && (color.equals("white") == false) && ((end.letter_rank - start.letter_rank) == 1) && 
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
				//if (first_move == true){first_move=false;}
				return true;
			}
		}
		
		//If there is an enemy piece in one of two forward diagonal spaces, a black pawn can take that piece and move there.
		if ((!(color.equals("white"))) && ((end.letter_rank - start.letter_rank) == 1) &&
				((start.number_rank - end.number_rank) == 1) || ((end.number_rank - start.number_rank) == 1))
		{
			if (end.isOccupied == true && end.occupying_piece.color.equals("white"))
			{
				//if (first_move == true){first_move=false;}
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
			{if (first_move == true)
			{
				if (Board.chess_board[currentTile.letter_rank - 2][currentTile.number_rank].isOccupied == false)
			
				{
					reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank - 2][currentTile.number_rank];
					cnt++;
					//first_move = false;
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
			{if (first_move == true)
			{
				if (Board.chess_board[currentTile.letter_rank + 2][currentTile.number_rank].isOccupied == false)
			
				{
					reachableTiles[cnt] = Board.chess_board[currentTile.letter_rank + 2][currentTile.number_rank];
					cnt++;
					//first_move = false;
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
	
	public boolean validOutOfCheck(Tile end_tile){return true;}
	
	static void pawn_promotion(int start_x, int start_y, int end_x, int end_y, Player current_player, Player opponent) {
		
		System.out.println("Eligible for pawn promotion. Would you like to promote this pawn? (Y/N): ");	
		String input = getInput();
		
		while (!input.equals("Y") && !input.equals("N")) {
			
			System.out.println("Invalid input. Enter 'Y' (yes) or 'N' (no): ");		
			input = getInput();						
		}
		
		if (input.equals("N")) {	//If enters 'N', move pawn and go to next turn
			
		Board.chess_board[start_x][start_y].occupying_piece.move(Board.chess_board[end_x][end_y]);		
		capturePiece(start_x, start_y, end_x, end_y, opponent);
		
		Board.chess_board[end_x][end_y].occupying_piece = Board.chess_board[start_x][start_y].occupying_piece;
		Board.chess_board[start_x][start_y].occupying_piece = null;
		
		if (checkForCheck(Board.chess_board[end_x][end_y].occupying_piece)) { //needs to be replaced?
			opponent.king_in_check = true;
		}
		turn++;	
		
		} else if (input.equals("Y")) {	//If enters 'Y', have user enter name of piece they wish to swap in for pawn
			
			boolean match_found = false;
					
			while (match_found == false ) {						
				System.out.println("Enter name of piece you wish to promote pawn to ('queen', 'knight', 'bishop', 'rook'): ");
						
				input = getInput();
				
				if (input.equals("queen") || input.equals("knight") || input.equals("bishop") || input.equals("rook")) {
										
					for (int i = 0; i < current_player.graveyard.length; i++) { //Search player's graveyard for first instance of piece
								
						if (current_player.graveyard[i] != null) {
								
							if (input.equals(current_player.graveyard[i].pieceType)) { //if matching piece found, swap pawn with piece in graveyard and break out of loop
									
								Piece temp = current_player.graveyard[i];		
								current_player.graveyard[i] = Board.chess_board[start_x][start_y].occupying_piece;
										
								capturePiece(start_x, start_y, end_x, end_y, opponent);
									
								Board.chess_board[start_x][start_y].isOccupied = false;
								Board.chess_board[start_x][start_y].occupying_piece = null;
								Board.chess_board[end_x][end_y].isOccupied = true;
								Board.chess_board[end_x][end_y].occupying_piece = temp;
								Board.chess_board[end_x][end_y].occupying_piece.currentTile = Board.chess_board[end_x][end_y];
								
								match_found = true;						
								break;								
							}									
						}
					}					
							
					if (match_found == false) { //if no match found in graveyard, notify user and have them re-enter piece name										
						System.out.println("Piece not found in graveyard.");
					
					} else {	 //needs to be replaced?			
						if (checkForCheck(Board.chess_board[end_x][end_y].occupying_piece)) { //After moving the piece, check if the enemy's king is in the piece's possibleMove()
							opponent.king_in_check = true;
						}							
						turn++;							
					}
							
				} else {					
					System.out.println("Invalid piece name.");					
				}
			}				
		}
	}	
	
	//gets user input for pawn_promotion()
	
	static String getInput() {
		
		Scanner scan = new Scanner (System.in);		
		String input;
		
		input = scan.nextLine();
		input = input.trim();
		input = input.replaceAll(" ", "");
		
		return input;		
	}



}