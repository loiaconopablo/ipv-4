package colisiones;

import scenes.BattleCityScene;

import com.uqbar.vainilla.GameComponent;

public class ColisionBalaPastoRule extends ColisionBalaBloqueRule {
	
	private GameComponent<BattleCityScene> objetoArebotar;

	public ColisionBalaPastoRule(GameComponent<BattleCityScene> objetoArebotar) {
		super(objetoArebotar);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void apply(GameComponent<BattleCityScene> objetoArebotar,
			BattleCityScene scene) {
		// TODO Auto-generated method stub

	}

}
