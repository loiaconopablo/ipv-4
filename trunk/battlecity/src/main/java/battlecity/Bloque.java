package battlecity;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;

import scenes.BattleCityScene;

public abstract class Bloque extends GameComponent<BattleCityScene> {

	public Bloque(Rectangle rectangle, int x, int y) {
		super(rectangle, x, y);
	}

	public abstract void update(Bloque bloque, BattleCityScene scene, DeltaState deltaState);

	public abstract void choqueConBala(BattleCityScene scene);

	}


