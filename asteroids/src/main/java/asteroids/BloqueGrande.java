package asteroids;

import java.awt.Color;

import scenes.AsteroidsScene;

import com.uqbar.vainilla.appearances.Rectangle;

public class BloqueGrande extends Bloque{

	public BloqueGrande(int x, int y) {
		super(new Rectangle(Color.BLUE, 70,70), x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void desintegrarse(AsteroidsScene scene) {

		Bloque bloqueizquierda = scene.getBloquesMedianos().get(0);
		Bloque bloquederecha = scene.getBloquesMedianos().get(1);
		scene.addBloque(bloquederecha);
		scene.addBloque(bloqueizquierda);
		bloqueizquierda.setX(this.getX()-100);
		bloquederecha.setX(this.getX() + 100 );
		scene.getBloquesChicos().remove(0);
		scene.getBloquesChicos().remove(1);
		
	}

}