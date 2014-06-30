package scenes;

import scenes.Marcador;

import com.uqbar.vainilla.GameScene;
import components.scenas.WinOrLoseComponent;


public class EndScene extends GameScene {

	public EndScene( double x, double y, Marcador marcadorPuntos, PacmanScene battleScene) {
		super(new WinOrLoseComponent(x, y, marcadorPuntos, battleScene));
	}

}

