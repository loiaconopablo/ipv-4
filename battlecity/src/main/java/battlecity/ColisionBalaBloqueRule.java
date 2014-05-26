package battlecity;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

import scenes.BattleCityScene;

public abstract class ColisionBalaBloqueRule {
	
	private GameComponent<BattleCityScene> objetoArebotar;

	public ColisionBalaBloqueRule(GameComponent<BattleCityScene> objetoArebotar) {
		this.objetoArebotar = objetoArebotar;
	}
	public GameComponent<BattleCityScene> getObjetoArebotar() {
		return objetoArebotar;
	}

		
	public boolean mustApply(GameComponent<BattleCityScene> objetoQuePega,
			BattleCityScene scene) {
		return this.colisiona(objetoArebotar, objetoQuePega) ;
	}
	
	private boolean colisiona(GameComponent<BattleCityScene> objetoArebotar,
			GameComponent<BattleCityScene> objetoQuePega) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				objetoArebotar.getX(),
				objetoArebotar.getY(),
				objetoArebotar.getAppearance().getWidth()/2,
				objetoQuePega.getX(),
				objetoQuePega.getY(),
				objetoQuePega.getAppearance().getWidth(),
				objetoQuePega.getAppearance().getHeight());
	}

	public abstract void apply(GameComponent<BattleCityScene> objetoArebotar, BattleCityScene scene);
	
	
	public void setObjetoArebotar(GameComponent<BattleCityScene> objetoArebotar) {
		this.objetoArebotar = objetoArebotar;
	}
	
	

}
