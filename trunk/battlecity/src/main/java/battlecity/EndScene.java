package battlecity;

import scenes.BattleCityScene;
import scenes.Marcador;

import com.uqbar.vainilla.GameScene;


public class EndScene extends GameScene {

	public EndScene( double x, double y, Marcador marcadorPuntos, BattleCityScene asteroidsScene) {
		super(new WinOrLoseComponent(x, y, marcadorPuntos, asteroidsScene));
	}

}

