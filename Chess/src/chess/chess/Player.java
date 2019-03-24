package chess.chess;

public class Player {
	
	String color;
	int turn = 1;
	Piece graveyard[] = new Piece[16];
	boolean king_in_check = false;
	
	public Player(String color)
	{
		this.color = color; 
	}

}
