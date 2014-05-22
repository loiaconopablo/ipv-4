package asteroids;

import scenes.AsteroidsScene;
import utils.Vector2D;

import com.uqbar.vainilla.GameComponent;


public class ColisionNaveRule extends ColisionRule{
	
private GameComponent<AsteroidsScene> objetoArebotar;
	
	public ColisionNaveRule(GameComponent<AsteroidsScene> objetoArebotar) {
		super(objetoArebotar);
		this.objetoArebotar = objetoArebotar;
	}
	
	
	public void apply(Nave nave, AsteroidsScene scene) {
//		super.apply(nave, nuevaPosicion, scene);
		//nave.removeRule(this);
		nave.getRules().remove(this);
		scene.getBloques().remove(this.objetoArebotar);
		this.objetoArebotar.destroy();
	    scene.getMarcadorVidas().descontarMarcador();
//		scene.getMarcadorPuntos().subirMarcador();	
		nave.setX(nave.getxInicial());
		nave.setY(nave.getyInicial());
		scene.revisarFinDelJuego();
		
	}


	
	

}
