package asteroids;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import scenes.AsteroidsScene;
import scenes.Marcador;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.events.constants.Key;

public class WinOrLoseComponent extends GameComponent<GameScene> {
	
	private AsteroidsScene asteroidsScene;

	public WinOrLoseComponent(double x, double y, Marcador marcadorPuntos, AsteroidsScene asteroidsScene) {
		super(new Label(new Font("verdana",  Font.BOLD, 24), Color.BLUE, "",
				marcadorPuntos.fin()?"PERDISTE!, Presione N para un juego nuevo":"GANASTE!!!"), x, y);
		this.setAsteroidsScene(asteroidsScene);
	}
	
	
	@Override
	public void update(DeltaState deltaState) {
		if(deltaState.isKeyPressed(Key.N)) {
			this.getGame().setCurrentScene(((Asteroids)this.getGame()).buildAsteroidsScene());
		}
		if (deltaState.isKeyPressed(Key.C)) {
		//	this.getGame().setCurrentScene((AsteroidsScene) this.getAsteroidsScene().agregarBloque(asteroidsScene, new Dimension(800, 600)));
		}
	
		super.update(deltaState);
	}


	public AsteroidsScene getAsteroidsScene() {
		return asteroidsScene;
	}


	public void setAsteroidsScene(AsteroidsScene asteroidsScene) {
		this.asteroidsScene = asteroidsScene;
	}
	
}
