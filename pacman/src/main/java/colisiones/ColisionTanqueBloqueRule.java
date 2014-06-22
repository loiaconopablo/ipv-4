package colisiones;

import scenes.PacmanScene;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class ColisionTanqueBloqueRule{

	private GameComponent<PacmanScene> objetoArebotar;
	
	public ColisionTanqueBloqueRule(GameComponent<PacmanScene> objetoArebotar) {
		this.objetoArebotar = objetoArebotar;
	}
	
	public GameComponent<PacmanScene> getObjetoArebotar() {
		return objetoArebotar;
	}
		
	public boolean mustApply(GameComponent<PacmanScene> objetoAlQuePega,
			PacmanScene scene) {
		return this.colisiona(objetoArebotar, objetoAlQuePega) ;
	}
	
	private boolean colisiona(GameComponent<PacmanScene> objetoArebotar,
			GameComponent<PacmanScene> objetoQuePega) {
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

	
	public void setObjetoArebotar(GameComponent<PacmanScene> objetoArebotar) {
		this.objetoArebotar = objetoArebotar;
	}
	
	public void apply(GameComponent<PacmanScene> objeto,
			PacmanScene scene) {
		if(this.getObjetoArebotar().getY()>objeto.getY()+objeto.getAppearance().getHeight()){
			objeto.setY(Math.min(objeto.getY()+objeto.getAppearance().getHeight(),
					this.getObjetoArebotar().getY()));
		}
		else{
			objeto.setY(Math.max(objeto.getY(),this.getObjetoArebotar().getY()-(this.getObjetoArebotar().getAppearance().getHeight()/2)));
		}
		
		objeto.setX(objeto.getX());
	//	((Tanque)objeto).quedarseQuieto();
		//System.out.println("Choco");
	}

}
