package battlecity;

import java.awt.Color;

import scenes.BattleCityScene;

import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

public class Pasto extends Bloque{

	public Pasto(int x, int y) {
		//super(Sprite.fromImage("/asteroideChico.png"), x, y);
		super(new Rectangle(Color.GREEN, 25,25), x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void desintegrarse(BattleCityScene scene) {
		// el bloque chico no se desintegra, desaparece
		
	}

}
