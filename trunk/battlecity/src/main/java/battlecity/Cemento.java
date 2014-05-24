package battlecity;

import scenes.BattleCityScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;

public class Cemento extends Bloque{

	public Cemento(int x, int y) {
		super(Sprite.fromImage("cemento.png"), x, y);
		//super(new Rectangle(Color.RED, 25,25), x, y);
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