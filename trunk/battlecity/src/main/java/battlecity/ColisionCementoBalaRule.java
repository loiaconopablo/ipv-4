package battlecity;

import scenes.BattleCityScene;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class ColisionCementoBalaRule {
	
	private GameComponent<BattleCityScene> objetoArebotar;

	public ColisionCementoBalaRule(GameComponent<BattleCityScene> objetoArebotar) {
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
		scene.getBalas().remove(this.objetoArebotar);
		((Bala) this.objetoArebotar).volverASetearseAsuDueño();	
		this.objetoArebotar.destroy();
		scene.revisarFinDelJuego();
		
	}

}

