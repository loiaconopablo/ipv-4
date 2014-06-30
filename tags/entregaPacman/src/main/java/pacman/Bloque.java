package pacman;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

import scenes.PacmanScene;

public abstract class Bloque extends GameComponent<PacmanScene> {

	public Bloque(Sprite sprite, int x, int y) {
		super(sprite, x, y);
	}

	public Bloque(Rectangle rectangle, int x, int y) {
		super(rectangle, x, y);
	}

}





