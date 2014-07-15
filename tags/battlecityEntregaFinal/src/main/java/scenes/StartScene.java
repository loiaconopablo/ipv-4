package scenes;

import java.awt.Color;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;

import components.scenas.StartComponent;

public class StartScene extends GameScene {

	private GameComponent<GameScene> backGroundOne;
	private Sprite logo;
	public StartScene( double x, double y, GameScene battleScene) {
		super(new StartComponent(x, y, battleScene));
		this.setLogo(Sprite.fromImage("inicio.png"));
		GameComponent logoInicio = new GameComponent(logo,100,100);
		this.addComponent(logoInicio);
		
		this.backGroundOne = new GameComponent<GameScene>(new Rectangle(Color.BLACK,
				950, 600), 0, 0);
		this.backGroundOne.setZ(-1);
		this.addComponent(this.backGroundOne);
		
	}
	public Sprite getLogo() {
		return logo;
	}
	public void setLogo(Sprite logo) {
		this.logo = logo;
	}

}

