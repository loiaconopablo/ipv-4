package battlecity;

import scenes.BattleCityScene;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class ColisionTanqueBloqueRule{

	private GameComponent<BattleCityScene> objetoArebotar;
	
	public ColisionTanqueBloqueRule(GameComponent<BattleCityScene> objetoArebotar) {
		this.objetoArebotar = objetoArebotar;
	}
	
	public GameComponent<BattleCityScene> getObjetoArebotar() {
		return objetoArebotar;
	}
		
	public boolean mustApply(GameComponent<BattleCityScene> objetoAlQuePega,
			BattleCityScene scene) {
		return this.colisiona(objetoArebotar, objetoAlQuePega) ;
	}
	
	private boolean colisiona(GameComponent<BattleCityScene> objetoArebotar,
			GameComponent<BattleCityScene> objetoQuePega) {
		return CollisionDetector.INSTANCE.collidesRectAgainstRect(
				objetoArebotar.getX(), 
				objetoArebotar.getY(), 
				(int) objetoArebotar.getAppearance().getWidth(), 
				(int) objetoArebotar.getAppearance().getHeight(), 
				objetoQuePega.getX(),
				objetoQuePega.getY(), 
				(int) objetoQuePega.getAppearance().getWidth(), 
				(int) objetoQuePega.getAppearance().getHeight());
	}

	
	public void setObjetoArebotar(GameComponent<BattleCityScene> objetoArebotar) {
		this.objetoArebotar = objetoArebotar;
	}
	
	public void apply(GameComponent<BattleCityScene> objeto,
			BattleCityScene scene) {
		objeto.setY(objeto.getY());
		objeto.setX(objeto.getX());
	//	((Tanque)objeto).quedarseQuieto();
		System.out.println("Choco");
	}

}
