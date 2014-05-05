package asteroids;


import java.awt.Color;
import java.awt.Dimension;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;

public class Asteroids extends Game {

	private Dimension dimension;
	private GameScene principalScene;

	@Override
	protected void initializeResources() {
		Tuning.load();
		dimension = new Dimension(800, 600);
	}

	@Override
	protected void setUpScenes() {
		GameScene asteroidsScene = buildAsteroidsScene();

		this.setCurrentScene(asteroidsScene);
		this.setPrincipalScene(asteroidsScene);
	}

	public GameScene buildAsteroidsScene() {
		AsteroidsScene asteroidsScene = new AsteroidsScene();

		return asteroidsScene;
	}

	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {
		return "Asteroids";
	}

	public static void main(String[] args) {
		new DesktopGameLauncher(new Asteroids()).launch();
	}

	public GameScene getPrincipalScene() {
		return principalScene;
	}

	public void setPrincipalScene(GameScene principalScene) {
		this.principalScene = principalScene;
	}

	
}