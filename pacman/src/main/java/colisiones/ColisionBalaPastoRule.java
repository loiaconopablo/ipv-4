package colisiones;

import scenes.PacmanScene;

import com.uqbar.vainilla.GameComponent;

public class ColisionBalaPastoRule extends ColisionBalaBloqueRule {
	
	private GameComponent<PacmanScene> objetoArebotar;

	public ColisionBalaPastoRule(GameComponent<PacmanScene> objetoArebotar) {
		super(objetoArebotar);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void apply(GameComponent<PacmanScene> objetoArebotar,
			PacmanScene scene) {
		// TODO Auto-generated method stub

	}

}
