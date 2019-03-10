package chess;
import java.util.Scanner;

/* This is the main class that executes the flow of the game */

public class Game {
	
	static int turn = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
				
				int w_start_let = Tile.translateLetterToInt(input.substring(0, 1));
				int w_start_num = Integer.parseInt(input.substring(1, 2));
				int w_end_let = Tile.translateLetterToInt(input.substring(2, 3));
				int w_end_num = Integer.parseInt(input.substring(3, 4));
				
				//This is just something I was using to debug
				//Board.chess_board[w_start_let][w_start_num].occupying_piece.tag = "XD";
				
				//This is what would initiate a move when the coordinates are properly mapped. 
				//Board.chess_board[w_start_let][w_start_num].occupying_piece.move(Board.chess_board[w_end_let][w_end_num]);

				turn++;
				
				Board.makeBoard();
			}
			else
			{
				System.out.println("Black's move: ");
				
				input = scan.nextLine();
				input = input.trim();
				input = input.replaceAll(" ", "");

				turn++;
				
				Board.makeBoard();
			}
			//This is just to debug so the game doesn't go on forever.
			if (turn==3){break;}
			
		}

	}
	

}
