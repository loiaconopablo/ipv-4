package pacman;

import com.uqbar.vainilla.appearances.Sprite;

public class FantasmaRosa extends Fantasma {

	public FantasmaRosa(double posx, double posy, double xMin, double xMax,
			double yMin, double yMax) {
		super(posx, posy, xMin, xMax, yMin, yMax);
		this.setFantasmaEnemigoA(Sprite.fromImage("enemigoRosaA.png"));
		this.setFantasmaEnemigoB(Sprite.fromImage("enemigoRosaB.png"));
		this.setAppearance(this.getFantasmaEnemigoA());	
	}

}
