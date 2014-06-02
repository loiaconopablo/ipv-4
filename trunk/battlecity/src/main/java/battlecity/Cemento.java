package battlecity;

import java.util.ArrayList;
import java.util.List;

import scenes.BattleCityScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;

public class Cemento extends Bloque{

	private List<ColisionBalaCementoRule> rules = new ArrayList<ColisionBalaCementoRule>();
	
	public Cemento(int x, int y) {
		super(Sprite.fromImage("cemento.png"), x, y);
		//super(new Rectangle(Color.RED, 25,25), x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ColisionBalaBloqueRule crearSuColisionConBala() {
		return new ColisionBalaCementoRule(this);
	}

}