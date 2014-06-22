package pacman;

import java.util.ArrayList;
import java.util.List;

import scenes.PacmanScene;
import colisiones.ColisionBalaBloqueRule;
import colisiones.ColisionBalaCementoRule;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;

public class Pared extends Bloque{

	private List<ColisionBalaCementoRule> rules = new ArrayList<ColisionBalaCementoRule>();
	
	public Pared(int x, int y) {
		super(Sprite.fromImage("pared.png"), x, y);
		//super(new Rectangle(Color.RED, 25,25), x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ColisionBalaBloqueRule crearSuColisionConBala() {
		return new ColisionBalaCementoRule(this);
	}

}