package pacman;

import java.util.ArrayList;
import java.util.List;
import scenes.PacmanScene;
import colisiones.ColisionComidaBloqueRule;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;

public class Pared extends Bloque{

	
	public Pared(int x, int y) {
		super(Sprite.fromImage("pared.png"), x, y);
		//super(new Rectangle(Color.RED, 25,25), x, y);
		// TODO Auto-generated constructor stub
	}


}