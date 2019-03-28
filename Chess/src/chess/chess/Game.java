package chess;
import java.util.Scanner;

/** This program simulates a game of chess between two players
 * Then Game class executes the flow of the game
 * 
 * @author Justin Saganowski
 * @author Emily Tregelles
 */

public class Game {
	
	/** keeps track of turn number */
	static int turn = 1;
	
	 /** The main method of the Game class executes the flow of the game
	   * @param args not used
	   */
	
	public static void main(String[] args) {
		
		/**Fill the board with starting pieces and display initial game board*/
		
		Board.initialBoard();
		Board.makeBoard();
		 
		/**white and black player objects created*/
		
		Player player1 = new Player("white");
		Player player2 = new Player("black");
		
		/**scanner to get user input, stored in input*/
	
		Scanner scan = new Scanner (System.in);
		
		String input;
		
		while (true)
		{
			if ((turn % 2) != 0) /**White goes first so it goes on odd turns*/
			{
				if (player1.king_in_check == true)
				{
					Tile k = findKing("white");
					if (checkMate(Board.chess_board[k.letter_rank][k.number_rank].occupying_piece)) /**If all of the king's possible move's are under attack*/
							{
								int c = 0;
								Tile[] t = Board.chess_board[k.letter_rank][k.number_rank].occupying_piece.possibleMove();
								for (int i = 0; i < t.length; i++)
								{
									if (t[i] != null)
									{
										c++;
									}
								}
								if (c == 1) /**If the king only has one possible move a friendly piece could intercept*/
								{	
									boolean piece_sacrifice = intercept(Board.chess_board[k.letter_rank][k.number_rank].occupying_piece);
									
									if (piece_sacrifice == false)
									{
										System.out.println("Checkmate! Black wins!");
										break;
									}

								}
								else
								{
									System.out.println("Checkmate! Black wins!");
									break;
								}
							}
				  }
				
				System.out.println("White's move: ");
				
				input = scan.nextLine();
				input = input.trim();
				input = input.replaceAll(" ", "");
				if (input.length() < 4){System.out.println("Re-enter move"); continue;}
				if (input.equals("resign"))
				{
					System.out.println("Black wins");
					break;
					
				} /**enter "resign" to end the game*/
				else if (input.length() == 9)
				{
					String response = scan.nextLine().trim();
					if (response.equals("draw"))
					{
						break;
					}
				}
				
				/**Take the inputed coordinates*/
				
				int w_start_y = Tile.translateLetterToInt(input.substring(0, 1)); 
				int w_start_x = Integer.parseInt(input.substring(1, 2));
				w_start_x = 7 - (w_start_x-1);
				int w_end_y = Tile.translateLetterToInt(input.substring(2, 3));
				int w_end_x = Integer.parseInt(input.substring(3, 4));
				w_end_x = 7 - (w_end_x-1);
				
				boolean temp_bool = Board.chess_board[w_end_x][w_end_y].isOccupied; /**Boolean to tell whether tile being moved to is occupied*/
				boolean pawn_first = false;
				if (Board.chess_board[w_start_x][w_start_y].occupying_piece != null /**sets pawn_first to value of pawn's first_move in case of an undo move*/
						&& Board.chess_board[w_start_x][w_start_y].occupying_piece.pieceType.equals("pawn")){
					pawn_first = Board.chess_board[w_start_x][w_start_y].occupying_piece.first_move;
				}
				
				if (Board.chess_board[w_start_x][w_start_y].occupying_piece == null) /**If user selects an empty tile as start position*/
				{
					System.out.println("That is an empty tile, choose one with a white piece");
				}
				else if (Board.chess_board[w_start_x][w_start_y].occupying_piece.color != "white") /**If user selects tile with enemy piece as start position*/
				{
					System.out.println("Not a white piece, please enter again");
				}
				else if (player1.king_in_check == false) /**If player is not currently in check it can move any piece*/
					
				{ /**Following code performs the move and adds the end tile's piece to the graveyard if applicable*/
					
					if(castling_move(w_start_x, w_start_y, w_end_x, w_end_y, player1, player2) == false) { /**check if eligible for castling move*/				
					
						if (Board.chess_board[w_start_x][w_start_y].occupying_piece.move_check(Board.chess_board[w_end_x][w_end_y])) /**check if end tile for non-king piece is in possibleMove()*/
						{	
							if (Board.chess_board[w_start_x][w_start_y].occupying_piece.pieceType.equals("pawn") && w_end_x == 0 && player1.graveyard[0] != null) { /**check if eligible for pawn promotion	*/					
								pawn_promotion(w_start_x, w_start_y, w_end_x, w_end_y, player1, player2, temp_bool, pawn_first); 
									
							} else {
							
								Board.chess_board[w_start_x][w_start_y].occupying_piece.move(Board.chess_board[w_end_x][w_end_y]);
								if(Board.chess_board[w_end_x][w_end_y].isOccupied) {	
									if(Board.chess_board[w_end_x][w_end_y].occupying_piece != null) {
										if(!Board.chess_board[w_end_x][w_end_y].occupying_piece.color.equals(Board.chess_board[w_start_x][w_start_y].occupying_piece.color)) {										
											for(int i = 0; i < player2.graveyard.length; i++) {				
												if(player2.graveyard[i] == null) {					
													player2.graveyard[i] = Board.chess_board[w_end_x][w_end_y].occupying_piece;
													break;
												}
											}					
										} 
									}
								}
								Board.chess_board[w_end_x][w_end_y].occupying_piece = Board.chess_board[w_start_x][w_start_y].occupying_piece;
								Board.chess_board[w_start_x][w_start_y].occupying_piece = null;
								
								/**The following code checks if this move put the white king in check. This is illegal, so if true the code will undo the move*/
								
								if (isKingInCheck("white"))
								{
									Board.chess_board[w_start_x][w_start_y].isOccupied = true;
									Board.chess_board[w_start_x][w_start_y].occupying_piece = Board.chess_board[w_end_x][w_end_y].occupying_piece;
									Board.chess_board[w_start_x][w_start_y].occupying_piece.currentTile = Board.chess_board[w_start_x][w_start_y];
									
									Board.chess_board[w_end_x][w_end_y].isOccupied = temp_bool;
									Piece temp_piece = null;
									
									if (temp_bool == true)
									{
										for(int i = 0; i < player2.graveyard.length; i++) 
										{				
											if(player2.graveyard[i] != null) 
											{					
												if (i == player2.graveyard.length-1 || player2.graveyard[i+1] == null)
												{
													temp_piece = player2.graveyard[i];
													player2.graveyard[i] = null;
													break;
												}
											}
										}					
									}
									
									if (Board.chess_board[w_start_x][w_start_y].occupying_piece.pieceType.equals("pawn")) /**If piece moved was a pawn, resets to previous value of first_move*/
									{Board.chess_board[w_start_x][w_start_y].occupying_piece.first_move = pawn_first;}
									Board.chess_board[w_end_x][w_end_y].occupying_piece = temp_piece;
									if (temp_piece != null){Board.chess_board[w_end_x][w_end_y].occupying_piece.currentTile = Board.chess_board[w_end_x][w_end_y];}
									System.out.println("Move invalid, you cannot put yourself in check");
								}
								else
								{
									if (checkForCheck(Board.chess_board[w_end_x][w_end_y].occupying_piece)) /**After moving the piece, check if the enemy's king is in the piece's possibleMove()*/
									{
										player2.king_in_check = true;
									}
									turn++;
								}
							}
						
						} else {System.out.println("Invalid move, try again");}
					}
				}
				else if (player1.king_in_check == true)
				{
						/**Perform move as usual*/
						/**Following code performs the move and adds the end tile's piece to the graveyard if applicable*/
					
						if (Board.chess_board[w_start_x][w_start_y].occupying_piece.move_check(Board.chess_board[w_end_x][w_end_y])) //check if end tile for non-king piece is in possibleMove()
						{
							Board.chess_board[w_start_x][w_start_y].occupying_piece.move(Board.chess_board[w_end_x][w_end_y]);
							if(Board.chess_board[w_end_x][w_end_y].isOccupied) {	
								if(Board.chess_board[w_end_x][w_end_y].occupying_piece != null) {
									if(!Board.chess_board[w_end_x][w_end_y].occupying_piece.color.equals(Board.chess_board[w_start_x][w_start_y].occupying_piece.color)) {										
										for(int i = 0; i < player2.graveyard.length; i++) {				
											if(player2.graveyard[i] == null) {					
												player2.graveyard[i] = Board.chess_board[w_end_x][w_end_y].occupying_piece;
												break;
											}
										}					
									} 
								}
							}
							Board.chess_board[w_end_x][w_end_y].occupying_piece = Board.chess_board[w_start_x][w_start_y].occupying_piece;
							Board.chess_board[w_start_x][w_start_y].occupying_piece = null;
							
							/**The following code checks if this move put the white king in check. This is illegal, so if true the code will undo the move*/
							
							if (isKingInCheck("white"))
							{
								Board.chess_board[w_start_x][w_start_y].isOccupied = true;
								Board.chess_board[w_start_x][w_start_y].occupying_piece = Board.chess_board[w_end_x][w_end_y].occupying_piece;
								Board.chess_board[w_start_x][w_start_y].occupying_piece.currentTile = Board.chess_board[w_start_x][w_start_y];
								
								Board.chess_board[w_end_x][w_end_y].isOccupied = temp_bool;
								Piece temp_piece = null;
								
								if (temp_bool == true)
								{
									for(int i = 0; i < player2.graveyard.length; i++) 
									{				
										if(player2.graveyard[i] != null) 
										{					
											if (i == player2.graveyard.length-1 || player2.graveyard[i+1] == null)
											{
												temp_piece = player2.graveyard[i];
												player2.graveyard[i] = null;
												break;
											}
										}
									}					
								}
								
								if (Board.chess_board[w_start_x][w_start_y].occupying_piece.pieceType.equals("pawn"))
								{Board.chess_board[w_start_x][w_start_y].occupying_piece.first_move = pawn_first;}
								Board.chess_board[w_end_x][w_end_y].occupying_piece = temp_piece;
								if (temp_piece != null){Board.chess_board[w_end_x][w_end_y].occupying_piece.currentTile = Board.chess_board[w_end_x][w_end_y];}
								System.out.println("Move invalid, you cannot put yourself in check");
							}
							else
							{
								if (checkForCheck(Board.chess_board[w_end_x][w_end_y].occupying_piece)) /**After moving the piece, check if the enemy's king is in the piece's possibleMove()*/
								{
									player2.king_in_check = true;
								}
								turn++;
							}
						
						} else {System.out.println("Invalid move, try again");}			
				}
				Board.makeBoard();			
			}
			else
			{
				if (player2.king_in_check)
				{
					Tile k = findKing("black");
					if (checkMate(Board.chess_board[k.letter_rank][k.number_rank].occupying_piece)) /**If all of the king's possible move's are under attack*/
							{
								int c = 0;
								Tile[] t = Board.chess_board[k.letter_rank][k.number_rank].occupying_piece.possibleMove();
								for (int i = 0; i < t.length; i++)
								{
									if (t[i] != null)
									{
										c++;
									}
								}
								if (c == 1) /**If the king only has one possible move a friendly piece could intercept*/
								{	
									boolean piece_sacrifice = intercept(Board.chess_board[k.letter_rank][k.number_rank].occupying_piece);
									
									if (piece_sacrifice == false)
									{
										System.out.println("Checkmate! White wins!");
										break;
									}
								}
								else
								{
									System.out.println("Checkmate! White wins!");
									break;
								}
							}
				}
				
				System.out.println("Black's move: ");
				
				input = scan.nextLine();
				input = input.trim();
				input = input.replaceAll(" ", "");
				if (input.length() < 4){System.out.println("Re-enter move"); continue;}
				if (input.equals("resign"))
				{
					System.out.println("White wins");
					break;
				}
				else if (input.length() == 9)
				{
					String response = scan.nextLine().trim();
					if (response.equals("draw"))
					{
						break;
					}
				}
				
				int b_start_y = Tile.translateLetterToInt(input.substring(0, 1));
				int b_start_x = Integer.parseInt(input.substring(1, 2));
				b_start_x = 7 - (b_start_x-1);
				int b_end_y = Tile.translateLetterToInt(input.substring(2, 3));
				int b_end_x = Integer.parseInt(input.substring(3, 4));
				b_end_x = 7 - (b_end_x-1);
				
				boolean temp_bool = Board.chess_board[b_end_x][b_end_y].isOccupied; /**Boolean to tell whether tile being moved to is occupied*/
				boolean pawn_first = false;
				if (Board.chess_board[b_start_x][b_start_y].occupying_piece != null 
						&& Board.chess_board[b_start_x][b_start_y].occupying_piece.pieceType.equals("pawn")){
					pawn_first = Board.chess_board[b_start_x][b_start_y].occupying_piece.first_move;
				}
				
				if (Board.chess_board[b_start_x][b_start_y].occupying_piece == null)
				{
					System.out.println("That is an empty tile, choose one with a black piece");
				}
				else if (Board.chess_board[b_start_x][b_start_y].occupying_piece.color != "black")
				{
					System.out.println("Not a black piece, please enter again");
				}
				else if (player2.king_in_check == false)
				{ 
					/**Following code performs the move and adds the end tile's piece to the graveyard if applicable*/
					
					if(castling_move(b_start_x, b_start_y, b_end_x, b_end_y, player1, player2) == false) { /**check if eligible for castling move*/
					
						if (Board.chess_board[b_start_x][b_start_y].occupying_piece.move_check(Board.chess_board[b_end_x][b_end_y])) /**check if end tile for non-king piece is in possibleMove()*/
						{
							
							if (Board.chess_board[b_start_x][b_start_y].occupying_piece.pieceType.equals("pawn") && b_end_x == 7 && player2.graveyard[0] != null) { /**check if eligible for pawn promotion*/
								
								pawn_promotion(b_start_x, b_start_y, b_end_x, b_end_y, player2, player1, temp_bool, pawn_first); 
									
							} else {
											
								Board.chess_board[b_start_x][b_start_y].occupying_piece.move(Board.chess_board[b_end_x][b_end_y]);
								if(Board.chess_board[b_end_x][b_end_y].isOccupied) {	
									if(Board.chess_board[b_end_x][b_end_y].occupying_piece != null) {
										if(!Board.chess_board[b_end_x][b_end_y].occupying_piece.color.equals(Board.chess_board[b_start_x][b_start_y].occupying_piece.color)) {										
											for(int i = 0; i < player1.graveyard.length; i++) {				
												if(player1.graveyard[i] == null) {					
													player1.graveyard[i] = Board.chess_board[b_end_x][b_end_y].occupying_piece;
													break;
												}
											}					
										} 
									}
								}
								
								Board.chess_board[b_end_x][b_end_y].occupying_piece = Board.chess_board[b_start_x][b_start_y].occupying_piece;
								Board.chess_board[b_start_x][b_start_y].occupying_piece = null;
								
								/**The following code checks if this move put the black king in check. This is illegal, so if true the code will undo the move*/
								
								if (isKingInCheck("black"))
								{
									Board.chess_board[b_start_x][b_start_y].isOccupied = true;
									Board.chess_board[b_start_x][b_start_y].occupying_piece = Board.chess_board[b_end_x][b_end_y].occupying_piece;
									Board.chess_board[b_start_x][b_start_y].occupying_piece.currentTile = Board.chess_board[b_start_x][b_start_y];
									
									Board.chess_board[b_end_x][b_end_y].isOccupied = temp_bool;
									Piece temp_piece = null;
									
									if (temp_bool == true)
									{
										for(int i = 0; i < player1.graveyard.length; i++) 
										{				
											if(player1.graveyard[i] != null) 
											{					
												if (i == player1.graveyard.length-1 || player1.graveyard[i+1] == null)
												{
													temp_piece = player1.graveyard[i];
													player1.graveyard[i] = null;
													break;
												}
											}
										}					
									}
									
									if (Board.chess_board[b_start_x][b_start_y].occupying_piece.pieceType.equals("pawn"))
									{Board.chess_board[b_start_x][b_start_y].occupying_piece.first_move = pawn_first;}
									Board.chess_board[b_end_x][b_end_y].occupying_piece = temp_piece;
									if (temp_piece != null){Board.chess_board[b_end_x][b_end_y].occupying_piece.currentTile = Board.chess_board[b_end_x][b_end_y];}
									System.out.println("Move invalid, you cannot put yourself in check");
								}
								else
								{
									if (checkForCheck(Board.chess_board[b_end_x][b_end_y].occupying_piece)) /**After moving the piece, check if the enemy's king is in the piece's possibleMove()*/
									{
										player1.king_in_check = true;
									}
									turn++;
								}
							}
						} else {System.out.println("Invalid move, try again");}
					}
				}
				else if (player2.king_in_check == true)
					
				{		/**Perform move as usual*/
						/**Following code performs the move and adds the end tile's piece to the graveyard if applicable*/
					
						if (Board.chess_board[b_start_x][b_start_y].occupying_piece.move_check(Board.chess_board[b_end_x][b_end_y])) /**check if end tile for non-king piece is in possibleMove()*/
						{
							Board.chess_board[b_start_x][b_start_y].occupying_piece.move(Board.chess_board[b_end_x][b_end_y]);
							if(Board.chess_board[b_end_x][b_end_y].isOccupied) {	
								if(Board.chess_board[b_end_x][b_end_y].occupying_piece != null) {
									if(!Board.chess_board[b_end_x][b_end_y].occupying_piece.color.equals(Board.chess_board[b_start_x][b_start_y].occupying_piece.color)) {										
										for(int i = 0; i < player1.graveyard.length; i++) {				
											if(player1.graveyard[i] == null) {					
												player1.graveyard[i] = Board.chess_board[b_end_x][b_end_y].occupying_piece;
												break;
											}
										}					
									} 
								}
							}
							Board.chess_board[b_end_x][b_end_y].occupying_piece = Board.chess_board[b_start_x][b_start_y].occupying_piece;
							Board.chess_board[b_start_x][b_start_y].occupying_piece = null;
							
							/**The following code checks if this move put the black king in check. This is illegal, so if true the code will undo the move*/
							
							if (isKingInCheck("black"))
							{
								Board.chess_board[b_start_x][b_start_y].isOccupied = true;
								Board.chess_board[b_start_x][b_start_y].occupying_piece = Board.chess_board[b_end_x][b_end_y].occupying_piece;
								Board.chess_board[b_start_x][b_start_y].occupying_piece.currentTile = Board.chess_board[b_start_x][b_start_y];
								
								Board.chess_board[b_end_x][b_end_y].isOccupied = temp_bool;
								Piece temp_piece = null;
								
								if (temp_bool == true)
								{
									for(int i = 0; i < player1.graveyard.length; i++) 
									{				
										if(player1.graveyard[i] != null) 
										{					
											if (i == player1.graveyard.length-1 || player1.graveyard[i+1] == null)
											{
												temp_piece = player1.graveyard[i];
												player1.graveyard[i] = null;
												break;
											}
										}
									}					
								}
								
								if (Board.chess_board[b_start_x][b_start_y].occupying_piece.pieceType.equals("pawn"))
								{Board.chess_board[b_start_x][b_start_y].occupying_piece.first_move = pawn_first;}
								Board.chess_board[b_end_x][b_end_y].occupying_piece = temp_piece;
								if (temp_piece != null){Board.chess_board[b_end_x][b_end_y].occupying_piece.currentTile = Board.chess_board[b_end_x][b_end_y];}							
								System.out.println("Move invalid, you cannot put yourself in check");	
							}
							else
							{
								if (checkForCheck(Board.chess_board[b_end_x][b_end_y].occupying_piece)) /**After moving the piece, check if the enemy's king is in the piece's possibleMove()*/
								{
									player1.king_in_check = true;
								}
								turn++;
							}
						
						} else {System.out.println("Invalid move, try again");}							
			}
				Board.makeBoard();			

		}}
	}
	
	 /** The checkMate method goes through a king piece's possibleMove() array
	   * If all elements are null, there is no possible move, therefore check mate
	   * @param king This is the current player's king
	   * @return boolean
	   */
	
	static boolean checkMate (Piece king){
		for (int i = 0; i < king.possibleMove().length; i++)
		{
			if (king.possibleMove()[i] != null)
				
			{ /**if there is a single possible move, return false, not a checkmate*/
				
				if (validOutOfCheck(king.possibleMove()[i], king.color) == true)
				{
					return false;
				}
			}
		}
		return true; /**otherwise, there exists no valid move which means checkmate*/
	}
	
	
	 /**
	   * The validOutOfCheck method checks if moving a king to the specified tile will successfully get it out of check
	   * @param end_tile end tile of king piece
	   * @param king_color color of king
	   * @return boolean
	   */
	
	static boolean validOutOfCheck(Tile end_tile, String king_color)
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (Board.chess_board[i][j].isOccupied == true 
						&& Board.chess_board[i][j].occupying_piece.color.equals(king_color) == false)
				{
					Tile[] moves = Board.chess_board[i][j].occupying_piece.possibleMove();
					for (int k = 0; k < moves.length; k++)
					{
						if ((moves[k] != null && end_tile.letter_rank == moves[k].letter_rank 
								&& end_tile.number_rank == moves[k].number_rank) || end_tile.equals(Board.chess_board[i][j]))
						{
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/** The checkForCheck method will go through a piece's possibleMove() array and check if the enemy king occupies one of the tiles
	   * @param p piece being checked 
	   * @return boolean
	   */

	static boolean checkForCheck(Piece p)
	{
		Tile[] moves = p.possibleMove();
		for (int i = 0; i < moves.length; i++){
			if (moves[i] != null)
			{				
				Tile t = Board.chess_board[moves[i].letter_rank][moves[i].number_rank];
				
				if(t.isOccupied) 
				{					
					if (t.occupying_piece.pieceType.equals("king") == true 
						&& p.color.equals(t.occupying_piece.color) == false)
					{
						return true;
					}				
				}
			}
		}
		return false;
	}
	
	/**The isKingInCheck method searches the board to see if the friendly king is in check
	   * @param team_color color of player/king
	   * @return boolean
	   */
	
	static boolean isKingInCheck(String team_color)
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (Board.chess_board[i][j].isOccupied && Board.chess_board[i][j].occupying_piece.color.equals(team_color) == false)
				{/**if the tile is occupied and is an enemy piece*/
					for (int k = 0; k < Board.chess_board[i][j].occupying_piece.possibleMove().length; k++)
					{
						if (Board.chess_board[i][j].occupying_piece.possibleMove()[k] != null)
						{
							if (Board.chess_board[i][j].occupying_piece.possibleMove()[k].isOccupied 
								&& Board.chess_board[i][j].occupying_piece.possibleMove()[k].occupying_piece.pieceType.equals("king"))
							{/**check if the friendly king is in any enemy's possibleMove() array*/
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**The findKing method finds the current position of a king on the chess board
	   * @param king_color color of king
	   * @return Tile where king is located
	   */
	
	
	static Tile findKing(String king_color)
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (Board.chess_board[i][j].isOccupied && Board.chess_board[i][j].occupying_piece.pieceType.equals("king")
						&& Board.chess_board[i][j].occupying_piece.color.equals(king_color))
				{
					return Board.chess_board[i][j];
				}
			}
		}
		return null;
	}
	
	/** The intercept method searches the board for friendly pieces that can intercept an enemy piece's path of check
	   * @param king piece being intercepted
	   * @return boolean 
	   */
	

	static boolean intercept(Piece king)
	{	
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (Board.chess_board[i][j].occupying_piece.color.equals(king.color))
				{
					for (int k = 0; k < king.possibleMove().length; k++)
					{
						for (int l = 0; l < Board.chess_board[i][j].occupying_piece.possibleMove().length; l++)
						{
							if (king.possibleMove()[k] != null && Board.chess_board[i][j].occupying_piece.possibleMove()[l] != null)
							{
								if (king.possibleMove()[k].equals(Board.chess_board[i][j].occupying_piece.possibleMove()[l]))
								{
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/** The capturePiece method moves a captured piece to the opponent's graveyard
	   * @param start_x rank of start tile
	   * @param start_y file of start tile
	   * @param end_x rank of end tile
	   * @param end_y file of end tile
	   * @param opponent player whose piece is being captured
	   * @return void
	   */

	
	static void capturePiece(int start_x, int start_y, int end_x, int end_y, Player opponent) {
		
		if(Board.chess_board[end_x][end_y].isOccupied) {	
			if(Board.chess_board[end_x][end_y].occupying_piece != null) {
				if(!Board.chess_board[end_x][end_y].occupying_piece.color.equals(Board.chess_board[start_x][start_y].occupying_piece.color)) {										
					for(int i = 0; i < opponent.graveyard.length; i++) {				
						if(opponent.graveyard[i] == null) {					
							opponent.graveyard[i] = Board.chess_board[end_x][end_y].occupying_piece;
							break;
						}
					}					
				} 
			}
		}	
	}
	
	/** The getInput method retrieves the user's input
	   * @return user input string
	   */
	
	static String getInput() {
		
		Scanner scan = new Scanner (System.in);		
		String input;
		
		input = scan.nextLine();
		input = input.trim();
		input = input.replaceAll(" ", "");
		
		return input;		
	}
	
	/**The pawn_promotion method checks if a pawn is eligible for promotion
	   * If user decides to promote pawn, they indicate which piece from their graveyard to promote pawn to
	   * @param start_x rank of start tile
	   * @param start_y file of start tile
	   * @param end_x rank of end tile
	   * @param end_y file of end tile
	   * @param current_player player eligible for pawn promotion
	   * @param opponent opposing player
	   * @param temp_bool temporary boolean variable
	   * @param pawn_first indicates if it is pawn's first move
	   * @return void
	   */

	static void pawn_promotion(int start_x, int start_y, int end_x, int end_y, Player current_player, Player opponent, boolean temp_bool, boolean pawn_first) {
		
		System.out.println("Eligible for pawn promotion. Would you like to promote this pawn? (Y/N): ");	
		String input = getInput();
		
		while (!input.equals("Y") && !input.equals("N")) {
			
			System.out.println("Invalid input. Enter 'Y' (yes) or 'N' (no): ");		
			input = getInput();						
		}
		
		if (input.equals("N")) {	/**If enters 'N', move pawn and go to next turn*/
			
			Board.chess_board[start_x][start_y].occupying_piece.move(Board.chess_board[end_x][end_y]);		
			capturePiece(start_x, start_y, end_x, end_y, opponent);
			
			Board.chess_board[end_x][end_y].occupying_piece = Board.chess_board[start_x][start_y].occupying_piece;
			Board.chess_board[start_x][start_y].occupying_piece = null;
			
			/**The following code checks if this move put the king in check. This is illegal, so if true the code will undo the move*/
			
			if (isKingInCheck(current_player.color))
			{
				Board.chess_board[start_x][start_y].isOccupied = true;
				Board.chess_board[start_x][start_y].occupying_piece = Board.chess_board[end_x][end_y].occupying_piece;
				Board.chess_board[start_x][start_y].occupying_piece.currentTile = Board.chess_board[start_x][start_y];
				
				Board.chess_board[end_x][end_y].isOccupied = temp_bool;
				Piece temp_piece = null;
				
				if (temp_bool == true)
				{
					for(int i = 0; i < opponent.graveyard.length; i++) 
					{				
						if(opponent.graveyard[i] != null) 
						{					
							if (i == opponent.graveyard.length-1 || opponent.graveyard[i+1] == null)
							{
								temp_piece = opponent.graveyard[i];
								opponent.graveyard[i] = null;
								break;
							}
						}
					}					
				}
				
				if (Board.chess_board[start_x][start_y].occupying_piece.pieceType.equals("pawn")) /**If piece moved was a pawn, resets to previous value of first_move*/
				{Board.chess_board[start_x][start_y].occupying_piece.first_move = pawn_first;}
				Board.chess_board[end_x][end_y].occupying_piece = temp_piece;
				if (temp_piece != null){Board.chess_board[end_x][end_y].occupying_piece.currentTile = Board.chess_board[end_x][end_y];}
				System.out.println("Move invalid, you cannot put yourself in check");
			}
			else
			{
				if (checkForCheck(Board.chess_board[end_x][end_y].occupying_piece)) /**After moving the piece, check if the enemy's king is in the piece's possibleMove()*/
				{
					opponent.king_in_check = true;
				}
				turn++;
			}
		
		} else if (input.equals("Y")) {	/**If enters 'Y', have user enter name of piece they wish to swap in for pawn*/
			
			/**match_found is true if piece with matching pieceType found in player's graveyard*/
			
			boolean match_found = false;
					
			while (match_found == false ) {						
				System.out.println("Enter name of piece you wish to promote pawn to ('queen', 'knight', 'bishop', 'rook', 'pawn'): ");
						
				input = getInput();
				
				if (input.equals("queen") || input.equals("knight") || input.equals("bishop") || input.equals("rook") || input.equals("pawn")) {
										
					for (int i = 0; i < current_player.graveyard.length; i++) { /**Search player's graveyard for first instance of piece*/
								
						if (current_player.graveyard[i] != null) {
								
							if (input.equals(current_player.graveyard[i].pieceType)) { /**if matching piece found, swap pawn with piece in graveyard and break out of loop*/
									
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
							
					if (match_found == false) { /**if no match found in graveyard, notify user and have them re-enter piece name*/									
						System.out.println("Piece not found in graveyard.");
					
					} else {	 
						
						if (isKingInCheck(current_player.color))
						{
							Board.chess_board[start_x][start_y].isOccupied = true;
							Board.chess_board[start_x][start_y].occupying_piece = Board.chess_board[end_x][end_y].occupying_piece;
							Board.chess_board[start_x][start_y].occupying_piece.currentTile = Board.chess_board[start_x][start_y];
							
							Board.chess_board[end_x][end_y].isOccupied = temp_bool;
							Piece temp_piece = null;
							
							if (temp_bool == true)
							{
								for(int i = 0; i < opponent.graveyard.length; i++) 
								{				
									if(opponent.graveyard[i] != null) 
									{					
										if (i == opponent.graveyard.length-1 || opponent.graveyard[i+1] == null)
										{
											temp_piece = opponent.graveyard[i];
											opponent.graveyard[i] = null;
											break;
										}
									}
								}					
							}
							
							if (Board.chess_board[start_x][start_y].occupying_piece.pieceType.equals("pawn")) /**If piece moved was a pawn, resets to previous value of first_move*/
							{Board.chess_board[start_x][start_y].occupying_piece.first_move = pawn_first;}
							Board.chess_board[end_x][end_y].occupying_piece = temp_piece;
							if (temp_piece != null){Board.chess_board[end_x][end_y].occupying_piece.currentTile = Board.chess_board[end_x][end_y];}
							System.out.println("Move invalid, you cannot put yourself in check");
						}
						else
						{
							if (checkForCheck(Board.chess_board[end_x][end_y].occupying_piece)) /**After moving the piece, check if the enemy's king is in the piece's possibleMove()*/
							{
								opponent.king_in_check = true;
							}
							turn++;
						}
					}
							
				} else {					
					System.out.println("Invalid piece name.");					
				}
			}				
		}
	}
	
	/** The castling_move method determines if a piece is eligible to perform a castling move
	 * if eligible, move is carried out
	 * otherwise, returns false
	 * @return user input string
	 * @param start_x rank of start tile
	 * @param start_y file of start tile
	 * @param end_x rank of end tile
	 * @param end_y file of end tile
	 * @param current_player player eligible for castling move
	 * @param opponent opposing player
	 * @return boolean
	 */
	
	static boolean castling_move(int start_x, int start_y, int end_x, int end_y, Player current_player, Player opponent) {
		
		if(Board.chess_board[start_x][start_y].occupying_piece.pieceType.equals("king") && (end_y - start_y == 2 || start_y - end_y == 2)) {
		
			if(end_y - start_y == 2) { /**if moving king to the right*/
				
				System.out.println("Perform castling move? (Y/N): ");
				String input = getInput();
				
				if(input.equals("N")) {	
					
					return false;
					
				} else if (input.equals("Y")) {
				
					if(Board.chess_board[start_x][start_y].occupying_piece.first_move == true /**if this is the king's first move and the two tiles to its right are empty*/
							&& !Board.chess_board[start_x][start_y+1].isOccupied
							&& !Board.chess_board[end_x][end_y].isOccupied) {
						
						Board.chess_board[start_x][start_y].isOccupied = false; //move king
						Board.chess_board[start_x][start_y].occupying_piece.currentTile = Board.chess_board[end_x][end_y-1];
						Board.chess_board[end_x][end_y-1].isOccupied = true;
						Board.chess_board[end_x][end_y-1].occupying_piece = Board.chess_board[start_x][start_y].occupying_piece;
						Board.chess_board[start_x][start_y].occupying_piece = null;
						
						if (isKingInCheck(current_player.color)) {
							Board.chess_board[start_x][start_y].isOccupied = true;
							Board.chess_board[start_x][start_y].occupying_piece = Board.chess_board[end_x][end_y-1].occupying_piece;
							Board.chess_board[start_x][start_y].occupying_piece.currentTile = Board.chess_board[start_x][start_y];
							Board.chess_board[end_x][end_y-1].isOccupied = false;
							Board.chess_board[end_x][end_y-1].occupying_piece = null;
							
							return false;
						}
						
						Board.chess_board[start_x][start_y].isOccupied = true;
						Board.chess_board[start_x][start_y].occupying_piece = Board.chess_board[end_x][end_y-1].occupying_piece;
						Board.chess_board[start_x][start_y].occupying_piece.currentTile = Board.chess_board[start_x][start_y];
						Board.chess_board[end_x][end_y-1].isOccupied = false;
						Board.chess_board[end_x][end_y-1].occupying_piece = null;
								
						if(end_y+1 <= 7) { /**if there is a rook to the right of the destination tile that has not been moved previously*/
							
							if(Board.chess_board[end_x][end_y+1].isOccupied
									&& Board.chess_board[end_x][end_y+1].occupying_piece.pieceType.equals("rook")
									&& Board.chess_board[end_x][end_y+1].occupying_piece.first_move == true) {
								
								Board.chess_board[start_x][start_y].isOccupied = false; //move king
								Board.chess_board[start_x][start_y].occupying_piece.currentTile = Board.chess_board[end_x][end_y];
								Board.chess_board[end_x][end_y].isOccupied = true;
								Board.chess_board[end_x][end_y].occupying_piece = Board.chess_board[start_x][start_y].occupying_piece;
								Board.chess_board[start_x][start_y].occupying_piece = null;
								
								Board.chess_board[end_x][end_y+1].isOccupied = false; //move rook
								Board.chess_board[end_x][end_y+1].occupying_piece.currentTile = Board.chess_board[end_x][end_y-2]; 
								Board.chess_board[end_x][end_y-1].isOccupied = true;
								Board.chess_board[end_x][end_y-1].occupying_piece = Board.chess_board[end_x][end_y+1].occupying_piece;
								Board.chess_board[end_x][end_y+1].occupying_piece = null;	
								
								/**The following code checks if this move put the king in check. This is illegal, so if true the code will undo the move*/
								
								if (isKingInCheck(current_player.color)) {
									Board.chess_board[start_x][start_y].isOccupied = true;
									Board.chess_board[start_x][start_y].occupying_piece = Board.chess_board[end_x][end_y].occupying_piece;
									Board.chess_board[start_x][start_y].occupying_piece.currentTile = Board.chess_board[start_x][start_y];
									Board.chess_board[end_x][end_y].isOccupied = false;
									Board.chess_board[end_x][end_y].occupying_piece = null;
									
									Board.chess_board[end_x][end_y+1].isOccupied = true; 
									Board.chess_board[end_x][end_y+1].occupying_piece = Board.chess_board[end_x][end_y-1].occupying_piece;
									Board.chess_board[end_x][end_y+1].occupying_piece.currentTile = Board.chess_board[end_x][end_y+1]; 
									Board.chess_board[end_x][end_y-1].isOccupied = false;
									Board.chess_board[end_x][end_y-1].occupying_piece = null;	
									
									return false;
								
								} else {
									
									if (checkForCheck(Board.chess_board[end_x][end_y].occupying_piece)) { /**After moving the piece, check if the enemy's king is in the piece's possibleMove()*/
										opponent.king_in_check = true;
									}
									turn++;
								}
							} 	
							
							return true;						
						}
						
					}
				}		
					
			} else if(start_y - end_y == 2) { /**if moving king to the left*/
					
				System.out.println("Perform castling move? (Y/N): ");
				String input = getInput();
				
				if(input.equals("N")) {				
					return false;
					
				} else if (input.equals("Y")) {
				
					if(Board.chess_board[start_x][start_y].occupying_piece.first_move == true 
							&& !Board.chess_board[start_x][start_y-1].isOccupied
							&& !Board.chess_board[end_x][end_y].isOccupied
							&& !Board.chess_board[end_x][end_y-1].isOccupied) {
						
						Board.chess_board[start_x][start_y].isOccupied = false; /**move king*/
						Board.chess_board[start_x][start_y].occupying_piece.currentTile = Board.chess_board[end_x][end_y+1];
						Board.chess_board[end_x][end_y+1].isOccupied = true;
						Board.chess_board[end_x][end_y+1].occupying_piece = Board.chess_board[start_x][start_y].occupying_piece;
						Board.chess_board[start_x][start_y].occupying_piece = null;
						
						if (isKingInCheck(current_player.color)) {
							Board.chess_board[start_x][start_y].isOccupied = true;
							Board.chess_board[start_x][start_y].occupying_piece = Board.chess_board[end_x][end_y+1].occupying_piece;
							Board.chess_board[start_x][start_y].occupying_piece.currentTile = Board.chess_board[start_x][start_y];
							Board.chess_board[end_x][end_y+1].isOccupied = false;
							Board.chess_board[end_x][end_y+1].occupying_piece = null;
							
							return false;
						}
						
						Board.chess_board[start_x][start_y].isOccupied = true;
						Board.chess_board[start_x][start_y].occupying_piece = Board.chess_board[end_x][end_y+1].occupying_piece;
						Board.chess_board[start_x][start_y].occupying_piece.currentTile = Board.chess_board[start_x][start_y];
						Board.chess_board[end_x][end_y+1].isOccupied = false;
						Board.chess_board[end_x][end_y+1].occupying_piece = null;
						
						if(end_y-2 >= 0) { /**if there is a rook two spaces to the left of the destination tile that has not been moved previously*/
							
							if(Board.chess_board[end_x][end_y-2].isOccupied
									&& Board.chess_board[end_x][end_y-2].occupying_piece.pieceType.equals("rook")
									&& Board.chess_board[end_x][end_y-2].occupying_piece.first_move == true) {
							
								
								Board.chess_board[start_x][start_y].isOccupied = false; /**move king*/
								Board.chess_board[start_x][start_y].occupying_piece.currentTile = Board.chess_board[end_x][end_y];
								Board.chess_board[end_x][end_y].isOccupied = true;
								Board.chess_board[end_x][end_y].occupying_piece = Board.chess_board[start_x][start_y].occupying_piece;
								Board.chess_board[start_x][start_y].occupying_piece = null;
								
								Board.chess_board[end_x][end_y-2].isOccupied = false; /**move rook*/
								Board.chess_board[end_x][end_y-2].occupying_piece.currentTile = Board.chess_board[end_x][end_y+1];
								Board.chess_board[end_x][end_y+1].isOccupied = true;
								Board.chess_board[end_x][end_y+1].occupying_piece = Board.chess_board[end_x][end_y-2].occupying_piece;
								Board.chess_board[end_x][end_y-2].occupying_piece = null;
								
								/**The following code checks if this move put the king in check. This is illegal, so if true the code will undo the move*/
								
								if (isKingInCheck(current_player.color)) {
									Board.chess_board[start_x][start_y].isOccupied = true;
									Board.chess_board[start_x][start_y].occupying_piece = Board.chess_board[end_x][end_y].occupying_piece;
									Board.chess_board[start_x][start_y].occupying_piece.currentTile = Board.chess_board[start_x][start_y];
									Board.chess_board[end_x][end_y].isOccupied = false;
									Board.chess_board[end_x][end_y].occupying_piece = null;
									
									Board.chess_board[end_x][end_y-2].isOccupied = true; 
									Board.chess_board[end_x][end_y-2].occupying_piece = Board.chess_board[end_x][end_y+1].occupying_piece;
									Board.chess_board[end_x][end_y-1].occupying_piece.currentTile = Board.chess_board[end_x][end_y-2]; 
									Board.chess_board[end_x][end_y+1].isOccupied = false;
									Board.chess_board[end_x][end_y+1].occupying_piece = null;	
									
									return false;
								
								} else {
									
									if (checkForCheck(Board.chess_board[end_x][end_y].occupying_piece)) { /**After moving the piece, check if the enemy's king is in the piece's possibleMove()*/
										opponent.king_in_check = true;
									}
									turn++;
								}
								
								return true;
							}
						}				
					}
				}		
			} 
		}	
		
		return false;
	}
}		


	
	
	
	
	

