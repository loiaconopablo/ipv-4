import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.events.constants.Key;


public class WinOrLoseComponent extends GameComponent<GameScene> {
	
	private ArkanoidScene arkanoidScene;

	public WinOrLoseComponent(double x, double y, Marcador marcadorPuntos, ArkanoidScene arkanoidScene) {
		super(new Label(new Font("verdana",  Font.BOLD, 24), Color.BLUE, "",
				marcadorPuntos.fin()?"PERDISTE!, Presione N para un juego nuevo":"No hay mas ladrillos, Presione C para continuar"), x, y);
		this.setArkanoidScene(arkanoidScene);
	}
	
	
	@Override
	public void update(DeltaState deltaState) {
		if(deltaState.isKeyPressed(Key.N)) {
			this.getGame().setCurrentScene(((Arkanoid)this.getGame()).buildArkanoidScene());
		}
		if (deltaState.isKeyPressed(Key.C)) {
			this.getGame().setCurrentScene((ArkanoidScene) this.getArkanoidScene().agregarBloques(arkanoidScene, new Dimension(800, 600)));
		}
	
		super.update(deltaState);
	}


	public ArkanoidScene getArkanoidScene() {
		return arkanoidScene;
	}


	public void setArkanoidScene(ArkanoidScene arkanoidScene) {
		this.arkanoidScene = arkanoidScene;
	}
	
}
