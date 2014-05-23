package battlecity;

import java.awt.Color;

import scenes.BattleCityScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

public class Ladrillo extends Bloque{

	public Ladrillo(int x, int y) {
		//super(Sprite.fromImage("/asteroideChico.png"), x, y);
		super(new Rectangle(Color.RED, 25,25), x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Bloque bloque, BattleCityScene scene,
			DeltaState deltaState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void choqueConBala(BattleCityScene scene) {
		scene.removeComponent(this);
	}


}
