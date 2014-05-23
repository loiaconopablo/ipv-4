package battlecity;

import com.uqbar.vainilla.GameComponent;
import scenes.BattleCityScene;



public class ColisionRule implements BalaRule {

	private GameComponent<BattleCityScene> objetoArebotar;

	public ColisionRule(GameComponent<BattleCityScene> objetoArebotar) {
		super();
		this.objetoArebotar = objetoArebotar;
	}

	@Override
	public boolean mustApply(Bala bala, BattleCityScene scene) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void apply(Bala bala, BattleCityScene scene) {
		bala.volverASetearseAsuDueño();
		scene.removeComponent(bala);
		//bala.destroy();
	}


}