package asteroids;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

import scenes.AsteroidsScene;

public class ColisionBalaRule implements BalaRule {

	private GameComponent<AsteroidsScene> objetoArebotar;

	public ColisionBalaRule(GameComponent<AsteroidsScene> objetoArebotar) {
		this.objetoArebotar = objetoArebotar;
	}
		
	public boolean mustApply(Bloque bloque,
			AsteroidsScene scene) {
		return this.colisiona(objetoArebotar, bloque) ;
	}
	
	private boolean colisiona(GameComponent<AsteroidsScene> objetoArebotar,
			Bloque bloque) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				objetoArebotar.getX(),objetoArebotar.getY(),
				objetoArebotar.getAppearance().getWidth()/2,
				bloque.getX(),bloque.getY(),bloque.getAppearance().getWidth(),
				bloque.getAppearance().getHeight());
	}


	public void apply(Bloque bloque, AsteroidsScene scene) {
		bloque.removeRule(this);
		scene.getBalas().remove(this.objetoArebotar);
		bloque.desintegrarse(scene);
		scene.getBloques().remove(bloque);
		this.objetoArebotar.destroy();
		bloque.destroy();
		scene.getMarcadorPuntos().subirMarcador();	
		scene.revisarFinDelJuego();
		
	}


}
