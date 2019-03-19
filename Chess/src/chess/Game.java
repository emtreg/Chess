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
				//Add code here that checks if the end piece isOccupied and adds that piece to the graveyard
				Board.chess_board[w_end_num][w_end_let].occupying_piece = Board.chess_board[w_start_num][w_start_let].occupying_piece;
				Board.chess_board[w_start_num][w_start_let].occupying_piece = null;
				
				turn++;
				Board.makeBoard();
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
				
				Board.chess_board[b_start_num][b_start_let].occupying_piece.possibleMove();
				
				Board.chess_board[b_start_num][b_start_let].occupying_piece.move(Board.chess_board[b_end_num][b_end_let]);
				//Add code here that checks if the end piece isOccupied and adds that piece to the graveyard
				Board.chess_board[b_end_num][b_end_let].occupying_piece = Board.chess_board[b_start_num][b_start_let].occupying_piece;
				Board.chess_board[b_start_num][b_start_let].occupying_piece = null;
												
				turn++;
				
				Board.makeBoard();
			}
			//This is just to debug so the game doesn't go on forever.
			if (turn==4){break;}
			
		}

	}
	

}
