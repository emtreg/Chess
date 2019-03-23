package chess;
import java.util.Scanner;

/* This is the main class that executes the flow of the game */

public class Game {
	
	static int turn = 1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Fill the board with starting pieces and display initial game board.
		Board.initialBoard();
		Board.makeBoard();
		
		
		/*I haven't done anything with the player classes yet, I am just putting these
		  here to implement later.*/ 
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
				
				/* In order to fix the mapping I had to pass the coordinates to the chess
				 * board flipped, so instead of letter, number I passed them as 
				 * number, letter. Then I had to subtract 1 from the number value and then
				 * set it to be 7 - itself. */
				
				int w_start_let = Tile.translateLetterToInt(input.substring(0, 1));
				int w_start_num = Integer.parseInt(input.substring(1, 2));
				w_start_num = 7 - (w_start_num-1);
				int w_end_let = Tile.translateLetterToInt(input.substring(2, 3));
				int w_end_num = Integer.parseInt(input.substring(3, 4));
				w_end_num = 7 - (w_end_num-1);
								 
				Board.chess_board[w_start_num][w_start_let].occupying_piece.move(Board.chess_board[w_end_num][w_end_let]);
				
				/*
				
				//Castling move
				
				//if piece to be moved is a king and it is a valid castling move path
				
				if(Board.chess_board[w_start_num][w_start_let].occupying_piece.pieceType.equals("king") 
						&& (w_end_let - w_start_let == 2 || w_start_let - w_end_let == 2)) {
					
					//if moving king to the right
					
					if(w_end_let - w_start_let == 2) {
					
						//if this is the king's first move and the two tiles to its right are empty
						
						if(Board.chess_board[w_start_num][w_start_let].occupying_piece.first_move == true 
								|| !Board.chess_board[w_start_num][w_start_let+1].isOccupied
								|| !Board.chess_board[w_end_num][w_end_let].isOccupied) {
							
							//if there is a rook to the right of the destination tile that has not been moved previously
							
							if(w_end_let+1 < 8) {
								
								if(Board.chess_board[w_end_num][w_end_let+1].isOccupied
										&& Board.chess_board[w_end_num][w_end_let+1].occupying_piece.pieceType.equals("rook")
										&& Board.chess_board[w_end_num][w_end_let+1].occupying_piece.first_move == true) {
										// && move won't put king in check
									
									//move king
									
									Board.chess_board[w_start_num][w_start_let].isOccupied = false;
									Board.chess_board[w_start_num][w_start_let].occupying_piece.currentTile = Board.chess_board[w_end_num][w_end_let];
									Board.chess_board[w_end_num][w_end_let].isOccupied = true;
									Board.chess_board[w_end_num][w_end_let].occupying_piece = Board.chess_board[w_start_num][w_start_let].occupying_piece;
									Board.chess_board[w_start_num][w_start_let].occupying_piece = null;
									
									//move rook
									
									Board.chess_board[w_end_num][w_end_let+1].isOccupied = false;
									Board.chess_board[w_end_num][w_end_let+1].occupying_piece.currentTile = Board.chess_board[w_end_num][w_end_let-2];
									Board.chess_board[w_end_num][w_end_let-1].isOccupied = true;
									Board.chess_board[w_end_num][w_end_let-1].occupying_piece = Board.chess_board[w_end_num][w_end_let+1].occupying_piece;
									Board.chess_board[w_end_num][w_end_let+1].occupying_piece = null;
									
								}							
							} 														
						}
						
					//if moving king to the left
						
					} else if(w_start_let - w_end_let == 2) {
						
						if(Board.chess_board[w_start_num][w_start_let].occupying_piece.first_move == true 
								|| !Board.chess_board[w_start_num][w_start_let-1].isOccupied
								|| !Board.chess_board[w_end_num][w_end_let].isOccupied) {
							
							//if there is a rook to the right of the destination tile that has not been moved previously
							
							if(w_end_let-1 >= 0) {
								
								if(Board.chess_board[w_end_num][w_end_let-1].isOccupied
										&& Board.chess_board[w_end_num][w_end_let-1].occupying_piece.pieceType.equals("rook")
										&& Board.chess_board[w_end_num][w_end_let-1].occupying_piece.first_move == true) {
										// && move won't put king in check
									
									//move king
									
									Board.chess_board[w_start_num][w_start_let].isOccupied = false;
									Board.chess_board[w_start_num][w_start_let].occupying_piece.currentTile = Board.chess_board[w_end_num][w_end_let];
									Board.chess_board[w_end_num][w_end_let].isOccupied = true;
									Board.chess_board[w_end_num][w_end_let].occupying_piece = Board.chess_board[w_start_num][w_start_let].occupying_piece;
									Board.chess_board[w_start_num][w_start_let].occupying_piece = null;
									
									//move rook
									
									Board.chess_board[w_end_num][w_end_let-1].isOccupied = false;
									Board.chess_board[w_end_num][w_end_let-1].occupying_piece.currentTile = Board.chess_board[w_end_num][w_end_let-2];
									Board.chess_board[w_end_num][w_end_let+1].isOccupied = true;
									Board.chess_board[w_end_num][w_end_let+1].occupying_piece = Board.chess_board[w_end_num][w_end_let+1].occupying_piece;
									Board.chess_board[w_end_num][w_end_let-1].occupying_piece = null;
									
								}							
							} 														
						}											
					}
				}
				
				*/
				
				
				//if piece captured, add to player2's graveyard
				
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
				
				turn++;

			}
			else
			{
				System.out.println("Black's move: ");
				
				input = scan.nextLine();
				input = input.trim();
				input = input.replaceAll(" ", "");

				int b_start_let = Tile.translateLetterToInt(input.substring(0, 1));
				int b_start_num = Integer.parseInt(input.substring(1, 2));
				b_start_num = 7 - (b_start_num-1);
				int b_end_let = Tile.translateLetterToInt(input.substring(2, 3));
				int b_end_num = Integer.parseInt(input.substring(3, 4));
				b_end_num = 7 - (b_end_num-1);
				
				//Board.chess_board[b_start_num][b_start_let].occupying_piece.possibleMove();
				
				Board.chess_board[b_start_num][b_start_let].occupying_piece.move(Board.chess_board[b_end_num][b_end_let]);
				
				
				//if piece captured, add to player1's graveyard
				
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
												
				turn++;
				
				Board.makeBoard();
			}
			//This is just to debug so the game doesn't go on forever.
			//if (turn==4){break;}
			
		}

	}
	

}
