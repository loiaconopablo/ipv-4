package battlecity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import scenes.BattleCityScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

public class Ladrillo extends Bloque{

	private List<ColisionBalaLadrilloRule> rules = new ArrayList<ColisionBalaLadrilloRule>();
	
	public Ladrillo(int x, int y) {
		super(Sprite.fromImage("/ladrillo.png"), x, y);
	}

	@Override
	public ColisionBalaBloqueRule crearSuColisionConBala() {
		return new ColisionBalaLadrilloRule(this);
	}

	
}
