package battlecity;

import scenes.BattleCityScene;
import utils.Vector2D;

import com.uqbar.vainilla.GameComponent;


public class ColisionNaveRule extends ColisionRule{
	
private GameComponent<BattleCityScene> objetoArebotar;
	
	public ColisionNaveRule(GameComponent<BattleCityScene> objetoArebotar) {
		super(objetoArebotar);
		this.objetoArebotar = objetoArebotar;
	}
	
	
	public void apply(Tanque nave, BattleCityScene scene) {
//		super.apply(nave, nuevaPosicion, scene);
		//nave.removeRule(this);
		//nave.getRules().remove(this);
		//scene.getBloques().remove(this.objetoArebotar);
		this.objetoArebotar.destroy();
	    scene.getMarcadorVidas().descontarMarcador();
//		scene.getMarcadorPuntos().subirMarcador();	
		nave.setX(nave.getxInicial());
		nave.setY(nave.getyInicial());
		scene.revisarFinDelJuego();
		
	}


	
	

}
