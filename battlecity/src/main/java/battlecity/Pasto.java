package battlecity;

import scenes.BattleCityScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;

public class Pasto extends Bloque{

	public Pasto(int x, int y) {
		super(Sprite.fromImage("pasto.png"), x, y);
		this.setZ(1);
		//super(new Rectangle(Color.GREEN, 25,25), x, y);
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