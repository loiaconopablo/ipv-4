package colisiones;

import pacman.Comida;
import scenes.PacmanScene;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class ColisionBalaCementoRule extends ColisionBalaBloqueRule{
	
//	private GameComponent<BattleCityScene> objetoArebotar;

	public ColisionBalaCementoRule(GameComponent<PacmanScene> objetoArebotar) {
		super(objetoArebotar);
	}

	@Override
	public void apply(GameComponent<PacmanScene> objeto, PacmanScene scene) {
		scene.getComidas().remove(objeto);
		scene.removeComponent(objeto);
		//this.getObjetoArebotar().destroy();
		scene.revisarFinDelJuego();
		
	}

}

