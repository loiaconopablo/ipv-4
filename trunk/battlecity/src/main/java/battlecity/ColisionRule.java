package battlecity;


import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

import scenes.BattleCityScene;



public class ColisionRule implements BalaRule {

	private GameComponent<BattleCityScene> objetoArebotar;

	public ColisionRule(GameComponent<BattleCityScene> objetoArebotar) {
		super();
		this.objetoArebotar = objetoArebotar;
	}

	@Override
	public boolean mustApply(Bala bala, BattleCityScene scene) {
		return this.colisiona(objetoArebotar, bala);
	}

	private boolean colisiona(GameComponent<BattleCityScene> objetoArebotar, Bala bala) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				bala.getX(), bala.getY(), bala
						.getAppearance().getWidth() / 2, objetoArebotar.getX(),
				objetoArebotar.getY(), objetoArebotar.getAppearance().getWidth(), objetoArebotar
						.getAppearance().getHeight());
	}
	
	@Override
	public void apply(Bala bala, BattleCityScene scene) {
		bala.volverASetearseAsuDueño();
		scene.removeComponent(bala);
		//bala.destroy();
	}


}