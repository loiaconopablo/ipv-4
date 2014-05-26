package battlecity;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

import scenes.BattleCityScene;

public class ColisionLadrilloBalaRule {

	private GameComponent<BattleCityScene> objetoArebotar;

	public ColisionLadrilloBalaRule(GameComponent<BattleCityScene> objetoArebotar) {
		this.objetoArebotar = objetoArebotar;
	}
		
	public boolean mustApply(Bloque bloque,
			BattleCityScene scene) {
		return this.colisiona(objetoArebotar, bloque) ;
	}
	
	private boolean colisiona(GameComponent<BattleCityScene> objetoArebotar,
			Bloque bloque) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				objetoArebotar.getX(),objetoArebotar.getY(),
				objetoArebotar.getAppearance().getWidth()/2,
				bloque.getX(),bloque.getY(),bloque.getAppearance().getWidth(),
				bloque.getAppearance().getHeight());
	}


	public void apply(Bloque bloque, BattleCityScene scene) {
		bloque.removeRule(this);
		scene.getBalas().remove(this.objetoArebotar);
		scene.getBloques().remove(bloque);
		((Bala) this.objetoArebotar).volverASetearseAsuDueño();	
		bloque.destroy();
		this.objetoArebotar.destroy();
		scene.revisarFinDelJuego();
		
	}

}
