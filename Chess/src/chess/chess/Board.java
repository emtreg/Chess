package chess;

/** The Board class represents a board object
 * it creates and prints out the game board
 * 
 *  @author Justin Saganowski
 *  @author Emily Tregelles
 *  */


public class Board {
	
	/** A 2d Tile array is created to represent the game board. The board is then 
	   printed out according to the assignment specification */
	
	static Tile chess_board[][] = new Tile[8][8];
	
	/** the initialBoard method populates the board with both players' pieces
	 * @return nothing
	 */
	
	public static void initialBoard()
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				chess_board[i][j] = new Tile(i, j);
				
				/**Initialize white pieces*/
				
				if (i == 6) 
				{
					chess_board[i][j].isOccupied = true;
					chess_board[i][j].occupying_piece = new Pawn(chess_board[i][j], "white");
				}
				else if (i == 7 && (j == 0 || j == 7))
				{
					chess_board[i][j].isOccupied = true;
					chess_board[i][j].occupying_piece = new Rook(chess_board[i][j], "white");
				}
				else if (i == 7 && (j == 1 || j == 6))
				{
					chess_board[i][j].isOccupied = true;
					chess_board[i][j].occupying_piece = new Knight(chess_board[i][j], "white");
				}
				else if (i == 7 && (j == 2 || j == 5))
				{
					chess_board[i][j].isOccupied = true;
					chess_board[i][j].occupying_piece = new Bishop(chess_board[i][j], "white");
				}
				else if (i == 7 && j == 3)
				{
					chess_board[i][j].isOccupied = true;
					chess_board[i][j].occupying_piece = new Queen(chess_board[i][j], "white");
				}
				else if (i == 7 && j == 4)
				{
					chess_board[i][j].isOccupied = true;
					chess_board[i][j].occupying_piece = new King(chess_board[i][j], "white");
				}
				
				/**Initialize black pieces*/
				
				else if (i == 1) 
				{
					chess_board[i][j].isOccupied = true;
					chess_board[i][j].occupying_piece = new Pawn(chess_board[i][j], "black");
				}
				else if (i == 0 && (j == 0 || j == 7))
				{
					chess_board[i][j].isOccupied = true;
					chess_board[i][j].occupying_piece = new Rook(chess_board[i][j], "black");
				}
				else if (i == 0 && (j == 1 || j == 6))
				{
					chess_board[i][j].isOccupied = true;
					chess_board[i][j].occupying_piece = new Knight(chess_board[i][j], "black");
				}
				else if (i == 0 && (j == 2 || j == 5))
				{
					chess_board[i][j].isOccupied = true;
					chess_board[i][j].occupying_piece = new Bishop(chess_board[i][j], "black");
				}
				else if (i == 0 && j == 3)
				{
					chess_board[i][j].isOccupied = true;
					chess_board[i][j].occupying_piece = new Queen(chess_board[i][j], "black");
				}
				else if (i == 0 && j == 4)
				{
					chess_board[i][j].isOccupied = true;
					chess_board[i][j].occupying_piece = new King(chess_board[i][j], "black");
				}
			}
		}
	}
	
	/** The makeBoard method prints out the game board
	 * @return nothing
	 */
	
	public static void makeBoard()
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				 if (chess_board[i][j].color.equals("white"))
				 {
					 if (chess_board[i][j].isOccupied == true)
					 {
						 System.out.print(chess_board[i][j].occupying_piece.tag + " ");
					 }
					 else {System.out.print("   ");}
				 }
				 else
				 {
					 if (chess_board[i][j].isOccupied == true)
					 {
						 System.out.print(chess_board[i][j].occupying_piece.tag + " ");
					 }
					 else {System.out.print("## ");}
				 }
			}

			System.out.print(" " + (8-i));
			System.out.print("\n");
		}
		System.out.println(" a  b  c  d  e  f  g  h \n");
	}

}
