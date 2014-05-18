package asteroids;

import java.awt.Color;

import scenes.AsteroidsScene;

import com.uqbar.vainilla.appearances.Rectangle;

public class BloqueChico extends Bloque{

	public BloqueChico(int x, int y) {
		super(new Rectangle(Color.RED, 25,25), x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void desintegrarse(AsteroidsScene scene) {
		// el bloque chico no se desintegra, desaparece
		
	}

}
