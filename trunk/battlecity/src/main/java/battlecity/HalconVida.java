package battlecity;

import scenes.BattleCityScene;
import colisiones.ColisionBalaHalconVidaRule;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public class HalconVida extends GameComponent<BattleCityScene> {

	public HalconVida(Sprite sprite, int x, int y) {
		super(sprite, x, y);
	}

	public ColisionBalaHalconVidaRule crearSuColisionConBala(){
		return new ColisionBalaHalconVidaRule(this);
	}
}
