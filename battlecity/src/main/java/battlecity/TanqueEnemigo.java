package battlecity;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;

public class TanqueEnemigo extends Tanque {

	public TanqueEnemigo(double posx, double posy, double xMin, double xMax,
			double yMin, double yMax) {
		super(posx, posy, xMin, xMax, yMin, yMax);
		this.setAppearance(Sprite.fromImage("tanqueEnemigoAbajo.png"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(DeltaState deltaState) {

	}

}
