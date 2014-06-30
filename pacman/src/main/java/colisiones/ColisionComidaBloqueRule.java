package colisiones;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

import scenes.PacmanScene;

public abstract class ColisionComidaBloqueRule implements ColisionRule{
	
	private GameComponent<PacmanScene> objetoArebotar;

	public ColisionComidaBloqueRule(GameComponent<PacmanScene> objetoArebotar) {
		this.objetoArebotar = objetoArebotar;
	}
	public GameComponent<PacmanScene> getObjetoArebotar() {
		return objetoArebotar;
	}

		
	public boolean mustApply(GameComponent<PacmanScene> objetoQuePega,
			PacmanScene scene) {
		return this.colisiona(objetoArebotar, objetoQuePega) ;
	}
	
	public boolean colisiona(GameComponent<PacmanScene> objetoArebotar,
			GameComponent<PacmanScene> objetoQuePega) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				objetoArebotar.getX(),
				objetoArebotar.getY(),
				objetoArebotar.getAppearance().getWidth()/2,
				objetoQuePega.getX(),
				objetoQuePega.getY(),
				objetoQuePega.getAppearance().getWidth(),
				objetoQuePega.getAppearance().getHeight());
	}

	public abstract void apply(GameComponent<PacmanScene> objetoArebotar, PacmanScene scene);
	
	
	public void setObjetoArebotar(GameComponent<PacmanScene> objetoArebotar) {
		this.objetoArebotar = objetoArebotar;
	}
	
	

}
