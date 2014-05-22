package asteroids;

import scenes.AsteroidsScene;
import scenes.Marcador;

import com.uqbar.vainilla.GameScene;


public class EndScene extends GameScene {

	public EndScene( double x, double y, Marcador marcadorPuntos, AsteroidsScene asteroidsScene) {
		super(new WinOrLoseComponent(x, y, marcadorPuntos, asteroidsScene));
	}

}

