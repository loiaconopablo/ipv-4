package pacman;

import com.uqbar.vainilla.appearances.Sprite;

public class FantasmaCeleste extends Fantasma {

	public FantasmaCeleste(double posx, double posy, double xMin, double xMax,
			double yMin, double yMax) {
		super(posx, posy, xMin, xMax, yMin, yMax);
		this.setFantasmaEnemigoA(Sprite.fromImage("enemigoCelesteA.png"));
		this.setFantasmaEnemigoB(Sprite.fromImage("enemigoCelesteB.png"));
		this.setAppearance(this.getFantasmaEnemigoA());	
	}

}
