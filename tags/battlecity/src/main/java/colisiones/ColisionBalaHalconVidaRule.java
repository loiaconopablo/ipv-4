package colisiones;

import scenes.BattleCityScene;
import battlecity.Bala;

import com.uqbar.vainilla.GameComponent;

public class ColisionBalaHalconVidaRule extends ColisionBalaBloqueRule{

	private GameComponent<BattleCityScene> objetoArebotar;

	public ColisionBalaHalconVidaRule(GameComponent<BattleCityScene> objetoArebotar) {
		super(objetoArebotar);
	}
	
	@Override
	public void apply(GameComponent<BattleCityScene> objeto, BattleCityScene scene) {
		((Bala) objeto).removeRule(this);
		scene.getBalas().remove(objeto);
		((Bala) objeto).volverASetearseAsuDueño();	
		scene.removeComponent(objeto);
		this.getObjetoArebotar().destroy();
		scene.getMarcadorVidas().setValue(0);
		scene.fin();

	}
}
