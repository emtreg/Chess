package chess.chess;
import java.util.Scanner;

/* This is the main class that executes the flow of the game */

public class Game {
	
	static int turn = 1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Fill the board with starting pieces and display initial game board.
		Board.initialBoard();
		Board.makeBoard();
		 
		Player player1 = new Player("white");
		Player player2 = new Player("black");
		
		Scanner scan = new Scanner (System.in);
		
		String input;
		
		while (true)
		{
			if ((turn % 2) != 0) //White goes first so it goes on odd turns
			{
				System.out.println("White's move: ");
				
				input = scan.nextLine();
				input = input.trim();
				input = input.replaceAll(" ", "");
				if (input.equals("resign")){break;} //enter "resign" to end the game
				
				int w_start_let = Tile.translateLetterToInt(input.substring(0, 1));
				int w_start_num = Integer.parseInt(input.substring(1, 2));
				w_start_num = 7 - (w_start_num-1);
				int w_end_let = Tile.translateLetterToInt(input.substring(2, 3));
				int w_end_num = Integer.parseInt(input.substring(3, 4));
				w_end_num = 7 - (w_end_num-1);
				
				if (player1.king_in_check) //Check if player1's king is in check
				{
					if (!(Board.chess_board[w_start_let][w_start_num].occupying_piece.tag.equals("wK"))) //If in check, start tile MUST be king
					{
						System.out.println("You are in check, you must move your king");
					}
					else if (checkMate(Board.chess_board[w_start_let][w_start_num].occupying_piece)) //If there is no valid move for the king, check mate
					{
						System.out.println("Checkmate! Black wins!");
					}
					else if (Board.chess_board[w_start_let][w_start_num].occupying_piece.validOutOfCheck(Board.chess_board[w_end_let][w_end_num])) //If moving to the end tile will get the king out of check, do the move
					{
						Board.chess_board[w_start_num][w_start_let].occupying_piece.move(Board.chess_board[w_end_num][w_end_let]);
						if(Board.chess_board[w_end_num][w_end_let].isOccupied) {	
							if(Board.chess_board[w_end_num][w_end_let].occupying_piece != null) {
								if(!Board.chess_board[w_end_num][w_end_let].occupying_piece.color.equals(Board.chess_board[w_start_num][w_start_let].occupying_piece.color)) {										
									for(int i = 0; i < player2.graveyard.length; i++) {				
										if(player2.graveyard[i] == null) {					
											player2.graveyard[i] = Board.chess_board[w_end_num][w_end_let].occupying_piece;
											break;
										}
									}					
								} 
							}
						}
						
						Board.chess_board[w_end_num][w_end_let].occupying_piece = Board.chess_board[w_start_num][w_start_let].occupying_piece;
						Board.chess_board[w_start_num][w_start_let].occupying_piece = null;
						player1.king_in_check = false;
						turn++;
					}
					else {System.out.println("Will not get you out of check, enter new move");} //If move will not get king out of check, do another move
					
				}
				
				else if (Board.chess_board[w_start_num][w_start_let].occupying_piece.move_check(Board.chess_board[w_end_num][w_end_let])) //check if end tile for non-king piece is in possibleMove()
				{
					Board.chess_board[w_start_num][w_start_let].occupying_piece.move(Board.chess_board[w_end_num][w_end_let]);
					if(Board.chess_board[w_end_num][w_end_let].isOccupied) {	
						if(Board.chess_board[w_end_num][w_end_let].occupying_piece != null) {
							if(!Board.chess_board[w_end_num][w_end_let].occupying_piece.color.equals(Board.chess_board[w_start_num][w_start_let].occupying_piece.color)) {										
								for(int i = 0; i < player2.graveyard.length; i++) {				
									if(player2.graveyard[i] == null) {					
										player2.graveyard[i] = Board.chess_board[w_end_num][w_end_let].occupying_piece;
										break;
									}
								}					
							} 
						}
					}
					
					Board.chess_board[w_end_num][w_end_let].occupying_piece = Board.chess_board[w_start_num][w_start_let].occupying_piece;
					Board.chess_board[w_start_num][w_start_let].occupying_piece = null;
					if (checkForCheck(Board.chess_board[w_end_num][w_end_let].occupying_piece)) //After moving the piece, check if the enemy's king is in the piece's possibleMove()
					{
						player2.king_in_check = true;
					}
					turn++;
				}
				else
				{
					System.out.println("Move invalid, enter new move");
				}

				Board.makeBoard();			
			}
			else
			{
				System.out.println("Black's move: ");
				
				input = scan.nextLine();
				input = input.trim();
				input = input.replaceAll(" ", "");
				if (input.equals("resign")){break;}
				
				int b_start_let = Tile.translateLetterToInt(input.substring(0, 1));
				int b_start_num = Integer.parseInt(input.substring(1, 2));
				b_start_num = 7 - (b_start_num-1);
				int b_end_let = Tile.translateLetterToInt(input.substring(2, 3));
				int b_end_num = Integer.parseInt(input.substring(3, 4));
				b_end_num = 7 - (b_end_num-1);
				
				if (player2.king_in_check)
				{
					if (!(Board.chess_board[b_start_let][b_start_num].occupying_piece.tag.equals("bK")))
					{
						System.out.println("You are in check, you must move your king");
					}
					else if (checkMate(Board.chess_board[b_start_let][b_start_num].occupying_piece))
					{
						System.out.println("Checkmate! White wins!");
					}
					else if (Board.chess_board[b_start_let][b_start_num].occupying_piece.validOutOfCheck(Board.chess_board[b_end_let][b_end_num]))
					{
						Board.chess_board[b_start_num][b_start_let].occupying_piece.move(Board.chess_board[b_end_num][b_end_let]);
						if(Board.chess_board[b_end_num][b_end_let].isOccupied) {	
							if(Board.chess_board[b_end_num][b_end_let].occupying_piece != null) {
								if(!Board.chess_board[b_end_num][b_end_let].occupying_piece.color.equals(Board.chess_board[b_start_num][b_start_let].occupying_piece.color)) {										
									for(int i = 0; i < player1.graveyard.length; i++) {				
										if(player1.graveyard[i] == null) {					
											player1.graveyard[i] = Board.chess_board[b_end_num][b_end_let].occupying_piece;
											break;
										}
									}					
								} 
							}
						}
						
						Board.chess_board[b_end_num][b_end_let].occupying_piece = Board.chess_board[b_start_num][b_start_let].occupying_piece;
						Board.chess_board[b_start_num][b_start_let].occupying_piece = null;
						player1.king_in_check = false;
						turn++;
					}
					else {System.out.println("Will not get you out of check, enter new move");}
					
				}
				
				else if (Board.chess_board[b_start_num][b_start_let].occupying_piece.move_check(Board.chess_board[b_end_num][b_end_let]))
				{
					Board.chess_board[b_start_num][b_start_let].occupying_piece.move(Board.chess_board[b_end_num][b_end_let]);
					if(Board.chess_board[b_end_num][b_end_let].isOccupied) {	
						if(Board.chess_board[b_end_num][b_end_let].occupying_piece != null) {
							if(!Board.chess_board[b_end_num][b_end_let].occupying_piece.color.equals(Board.chess_board[b_start_num][b_start_let].occupying_piece.color)) {										
								for(int i = 0; i < player1.graveyard.length; i++) {				
									if(player1.graveyard[i] == null) {					
										player1.graveyard[i] = Board.chess_board[b_end_num][b_end_let].occupying_piece;
										break;
									}
								}					
							} 
						}
					}
					
					Board.chess_board[b_end_num][b_end_let].occupying_piece = Board.chess_board[b_start_num][b_start_let].occupying_piece;
					Board.chess_board[b_start_num][b_start_let].occupying_piece = null;
					if (checkForCheck(Board.chess_board[b_end_num][b_end_let].occupying_piece))
					{
						player1.king_in_check = true;
					}
					turn++;
				}
				else
				{
					System.out.println("Move invalid, enter new move");
				}
								 		
				Board.makeBoard();
			}
			
			
		}

	}
	
	//checkMate goes through a king piece's possibleMove() array. If all elements are null, there is no possible move, therefore check mate.
	static boolean checkMate (Piece king){
		for (int i = 0; i < king.possibleMove().length; i++)
		{
			if (king.possibleMove()[i] != null)
			{ //if there is a single possible move, return false, not a checkmate
				if (king.validOutOfCheck(king.possibleMove()[i]) == true)
				{
					return false;
				}
			}
		}
		return true; //otherwise, there exists no valid move which means checkmate
	}
	
	//checkForCheck will go through a piece's possibleMove() array and check if the enemy king occupies one of the tiles. If so, return true.
	//This method keeps giving me the nullPointer error :(
	static boolean checkForCheck(Piece p)
	{
		Tile[] moves = p.possibleMove();
		for (int i = 0; i < moves.length; i++){
			if (moves[i] != null)
			{
				Tile t = Board.chess_board[moves[i].letter_rank][moves[i].number_rank];
				if (t.occupying_piece.pieceType.equals("king") == true 
					&& p.color.equals(t.occupying_piece.color) == false)
				{
					return true;
				}
			}
		}
		return false;
	}
	
}
