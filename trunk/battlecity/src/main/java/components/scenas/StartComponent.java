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
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;

public class StartComponent extends GameComponent<GameScene> {
	
	
	private GameScene battleScene;
	private double tiempo = 0;
	private double comparador = 1;

	public StartComponent(double x, double y, GameScene battleScene2) {
		super(new Label(new Font("verdana", Font.BOLD, 24), Color.BLUE, "",
				"PRESIONE ENTER PARA INICIAR EL JUEGO"), x, y);
		
		this.setBattleScene(battleScene2);
		
	}

	@Override
	public void update(DeltaState deltaState) {
		if (deltaState.isKeyPressed(Key.ENTER)) {
			this.getGame().setCurrentScene(
					((BattleCity) this.getGame()).buildBattleScene());
		}
		if (deltaState.isKeyPressed(Key.C)) {
			// this.getGame().setCurrentScene((AsteroidsScene)
			// this.getAsteroidsScene().agregarBloque(asteroidsScene, new
			// Dimension(800, 600)));
		}

		this.tiempo += deltaState.getDelta();
		if( this.tiempo >= this.comparador){
			this.simularTitilarLetras();
			this.comparador += 0.7;
		}
		super.update(deltaState);
		
	}

	private void simularTitilarLetras() {
		if(((Label) this.getAppearance()).getColor() == Color.BLUE)
			{((Label) this.getAppearance()).setColor(Color.BLACK);}	
		else{((Label) this.getAppearance()).setColor(Color.BLUE);}
		
	}

	public GameScene getBattleScene() {
		return battleScene;
	}

	public void setBattleScene(GameScene battleScene2) {
		this.battleScene = battleScene2;
	}

}
