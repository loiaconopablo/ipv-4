package battlecity;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

import scenes.BattleCityScene;

public abstract class Bloque extends GameComponent<BattleCityScene> {

	public Bloque(Sprite sprite, int x, int y) {
		super(sprite, x, y);
	}

	public abstract ColisionBalaBloqueRule crearSuColisionConBala();
}





