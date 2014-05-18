package asteroids;

import java.awt.Color;

import scenes.AsteroidsScene;

import com.uqbar.vainilla.appearances.Rectangle;

public class BloqueMediano extends Bloque{

	public BloqueMediano(int x, int y) {
		super(new Rectangle(Color.GREEN, 50,50), x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void desintegrarse(AsteroidsScene scene) {
		
		Bloque bloqueizquierda = scene.getBloquesChicos().get(0);
		Bloque bloquederecha = scene.getBloquesChicos().get(1);
		scene.addBloque(bloquederecha);
		scene.addBloque(bloqueizquierda);
		scene.getBloquesMedianos().add(this);
		bloqueizquierda.setX(this.getX()-100);
		bloquederecha.setX(this.getX() + 100 );
		scene.getBloquesChicos().remove(0);
		scene.getBloquesChicos().remove(1);
	}

}
