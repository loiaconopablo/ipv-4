package asteroids;

import java.awt.Color;

import scenes.AsteroidsScene;

import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

public class BloqueGrande extends Bloque{

	public BloqueGrande(int x, int y) {
		super(Sprite.fromImage("/asteroideGrande.png"), x, y);
		//super(new Rectangle(Color.RED, 70,70), x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void desintegrarse(AsteroidsScene scene) {

		Bloque bloqueizquierda = scene.dameUnBloqueMediano();
		Bloque bloquederecha = scene.dameUnBloqueMediano();
		scene.addBloque(bloquederecha);
		scene.addBloque(bloqueizquierda);
		bloqueizquierda.setX(this.getX()-100);
		bloquederecha.setX(this.getX() + 100 );
		bloqueizquierda.setY(this.getY());
		bloquederecha.setX(this.getY());
		
	}

}
