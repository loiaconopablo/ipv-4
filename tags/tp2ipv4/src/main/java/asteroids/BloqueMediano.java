package asteroids;

import java.awt.Color;

import scenes.AsteroidsScene;

import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

public class BloqueMediano extends Bloque{

	public BloqueMediano(int x, int y) {
		super(Sprite.fromImage("/asteroideMediano.png"), x, y);
		//super(new Rectangle(Color.RED, 50,50), x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void desintegrarse(AsteroidsScene scene) {
		
		Bloque bloqueizquierda = scene.dameUnBloqueChico();
		Bloque bloquederecha = scene.dameUnBloqueChico();
		scene.addBloque(bloquederecha);
		scene.addBloque(bloqueizquierda);
		scene.getBloquesMedianos().add(this);
		bloqueizquierda.setX(this.getX()-100);
		bloquederecha.setX(this.getX() + 100 );
		bloqueizquierda.setY(this.getY());
		bloquederecha.setX(this.getY());
	}

}
