package battlecity;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

import scenes.BattleCityScene;

public class ColisionBloqueRule extends ColisionRule implements TanqueRule{
	
	private Bloque objetoArebotar;
	
	public ColisionBloqueRule(Bloque objetoArebotar) {
		super(objetoArebotar);
		this.objetoArebotar = objetoArebotar;
	}
	
	public boolean mustApply(Tanque tanque, BattleCityScene scene) {
		return this.colisiona(objetoArebotar, tanque) ;
	}

	private boolean colisiona(GameComponent<BattleCityScene> objetoArebotar, Tanque tanque) {
		return CollisionDetector.INSTANCE.collidesRectAgainstRect(objetoArebotar.getX(), objetoArebotar.getY(),
				(int)objetoArebotar.getAppearance().getWidth(), (int)objetoArebotar.getAppearance().getHeight(),
																	tanque.getX(), tanque.getY(), 
																	(int)tanque.getAppearance().getWidth(), (int)tanque.getAppearance().getHeight());
	}
	public void apply(Tanque tanque, BattleCityScene scene) {
		tanque.setX(tanque.getX());
		tanque.setY(tanque.getY());
		
	}
	
	

}
