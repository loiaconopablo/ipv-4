package colisiones;

import scenes.BattleCityScene;
import battlecity.Bala;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class ColisionBalaCementoRule extends ColisionBalaBloqueRule{
	
//	private GameComponent<BattleCityScene> objetoArebotar;

	public ColisionBalaCementoRule(GameComponent<BattleCityScene> objetoArebotar) {
		super(objetoArebotar);
	}

	@Override
	public void apply(GameComponent<BattleCityScene> objeto, BattleCityScene scene) {
		scene.getBalas().remove(objeto);
		((Bala) objeto).volverASetearseAsuDueño();	
		scene.removeComponent(objeto);
		//this.getObjetoArebotar().destroy();
		scene.revisarFinDelJuego();
		
	}

}

