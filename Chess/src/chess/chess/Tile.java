package chess;

/** The Tile class represents a tile object
 * 
 *  @author Justin Saganowski
 *  @author Emily Tregelles
 *  */

public class Tile {
	
	/**Each Tile has a color, coordinates that indicate where it is on the board,
	 * and what (if any) chess piece currently occupies the tile */
	
	public String color;
	public int number_rank, letter_rank;
	public Piece occupying_piece;
	
	/**isOccupied states if a Piece is currently on the Tile. False is the default*/
	
	public boolean isOccupied = false;
	

	/** constructor for Tile object
	 * @param letter_rank tile file
	 * @param number_rank tile rank
	 */
	
	public Tile(int letter_rank, int number_rank)
	{
		this.letter_rank = letter_rank;
		this.number_rank = number_rank;
		color = assignColor(translateIntToLetter(letter_rank), number_rank);
	}
	   
	/**The translateIntToLetter method maps the integer coordinate to a letter
	 * @param let tile file
	 * @return corresponding letter or '?' on error
	 * */
	
	public String translateIntToLetter(int let)
	{
		if (let == 0){return("a");}
		else if (let == 1){return("b");}
		else if (let == 2){return("c");}
		else if (let == 3){return("d");}
		else if (let == 4){return("e");}
		else if (let == 5){return("f");}
		else if (let == 6){return("g");}
		else if (let == 7){return("h");}
		else{return("?");}
	}
	
	/**The translateLetterToInt method maps the letter coordinate to an integer
	 * @param num tile rank
	 * @return corresponding number or -1 on error
	 * */
	
	public static int translateLetterToInt(String num)
	{
		if (num.equals("a")){return(0);}
		else if (num.equals("b")){return(1);}
		else if (num.equals("c")){return(2);}
		else if (num.equals("d")){return(3);}
		else if (num.equals("e")){return(4);}
		else if (num.equals("f")){return(5);}
		else if (num.equals("g")){return(6);}
		else if (num.equals("h")){return(7);}
		else{return(-1);}
	}
	
	
	/**The assignColor method determines what color each tile is based on board coordinates
	 * @param let_rank tile file
	 * @param num_rank tile rank
	 * @return tile color
	 * */
	
	public String assignColor(String let_rank, int num_rank)
	{
		if (let_rank.equals("a") || let_rank.equals("c") || let_rank.equals("e") || 
				let_rank.equals("g"))
		{
			if ((num_rank % 2) == 0)
			{
				return("white");
			}
			else
			{
				return("black");
			}
		}
		else 
		{
			if ((num_rank % 2) == 0)
			{
				return("black");
			}
			else
			{
				return("white");
			}
		}
	}
	
	
	/**The setOccupyingPiece method sets the occupying piece of a tile
	 * @param occupying_piece piece to be set to
	 * @return nothing
	 * */
	
	public void setOccupyingPiece(Piece occupying_piece)
	{
		this.occupying_piece = occupying_piece;
	}
	
	/**The getOccupyingPiece method gets the occupying piece of a tile
	 * @return occupying piece* 
	 * */
	
	public Piece getOccupyingPiece()
	{
		return(occupying_piece);
	}
	
	
}
