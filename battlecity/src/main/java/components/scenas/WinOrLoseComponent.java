package components.scenas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import scenes.BattleCityScene;
import scenes.Marcador;
import battlecity.BattleCity;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.events.constants.Key;

public class WinOrLoseComponent extends GameComponent<GameScene> {
	
	private BattleCityScene battleScene;

	public WinOrLoseComponent(double x, double y, Marcador marcadorPuntos, BattleCityScene battleScenea) {
		super(new Label(new Font("verdana",  Font.BOLD, 24), Color.BLUE, "",
				marcadorPuntos.fin()?"PERDISTE!, Presione N para un juego nuevo":"GANASTE!!!"), x, y);
		this.setBatleCityScene(battleScenea);
	}
	
	
	@Override
	public void update(DeltaState deltaState) {
		if(deltaState.isKeyPressed(Key.N)) {
			this.getGame().setCurrentScene(((BattleCity)this.getGame()).buildBattleScene());
		}
		if (deltaState.isKeyPressed(Key.C)) {
		//	this.getGame().setCurrentScene((AsteroidsScene) this.getAsteroidsScene().agregarBloque(asteroidsScene, new Dimension(800, 600)));
		}
	
		super.update(deltaState);
	}


	public BattleCityScene getAsteroidsScene() {
		return battleScene;
	}


	public void setBatleCityScene(BattleCityScene asteroidsScene) {
		this.battleScene = asteroidsScene;
	}
	
}
