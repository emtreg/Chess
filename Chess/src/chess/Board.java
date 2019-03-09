package chess;
/* This class creates and prints out the game board */

public class Board {
	
	/* A 2d Tile array is created to represent the game board. The board is then 
	   printed out according to the assignment specification */
	
	static Tile chess_board[][] = new Tile[8][8];
	
	public static void makeBoard()
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				 chess_board[i][j] = new Tile(i, j);
				 if (chess_board[i][j].color.equals("white"))
				 {
					 System.out.print("   ");
				 }
				 else
				 {
					 System.out.print("## ");
				 }
			}
			System.out.print(" " + (8-i));
			System.out.print("\n");
		}
		System.out.println(" a  b  c  d  e  f  g  h");
	}

}
