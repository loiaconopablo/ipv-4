package battlecity;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

import scenes.BattleCityScene;

public class ColisionBalaLadrilloRule extends ColisionBalaBloqueRule {

	private GameComponent<BattleCityScene> objetoArebotar;

	public ColisionBalaLadrilloRule(GameComponent<BattleCityScene> objetoArebotar) {
		super(objetoArebotar);
	}
	
	@Override
	public void apply(GameComponent<BattleCityScene> objeto, BattleCityScene scene) {
		scene.getBalas().remove(objeto);
		((Bala) objeto).volverASetearseAsuDueño();	
		scene.removeComponent(objeto);
		this.getObjetoArebotar().destroy();
		scene.revisarFinDelJuego();
		
	}

}
