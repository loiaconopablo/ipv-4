package pacman;

import com.uqbar.vainilla.appearances.Sprite;

public class FantasmaRojo extends Fantasma {

	public FantasmaRojo(double posx, double posy, double xMin, double xMax,
			double yMin, double yMax) {
		super(posx, posy, xMin, xMax, yMin, yMax);
		this.setFantasmaEnemigoA(Sprite.fromImage("enemigoRojoA.png"));
		this.setFantasmaEnemigoB(Sprite.fromImage("enemigoRojoB.png"));
		this.setAppearance(this.getFantasmaEnemigoA());	
	}

}
