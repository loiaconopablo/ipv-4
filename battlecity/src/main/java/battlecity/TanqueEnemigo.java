package battlecity;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Sprite;

public class TanqueEnemigo extends Tanque {

	
	
	public TanqueEnemigo(double posx, double posy, double xMin, double xMax,
			double yMin, double yMax) {
		super(posx, posy, xMin, xMax, yMin, yMax);
		this.setAppearance(Sprite.fromImage("enemigoAbajo.png"));
		// TODO Auto-generated constructor stub
	}

	


}
