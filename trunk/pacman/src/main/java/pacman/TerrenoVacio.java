package pacman;


import java.awt.Color;
import com.uqbar.vainilla.appearances.Rectangle;

public class TerrenoVacio extends Bloque{


	public TerrenoVacio(int x, int y) {
		//super(Sprite.fromImage("pasto.png"), x, y);
		super(new Rectangle(Color.BLACK, 50,50), x, y);
		//this.setZ(1);
		
	}

}