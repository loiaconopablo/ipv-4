package asteroids;


import java.awt.Color;
import java.awt.Dimension;

import scenes.AsteroidsScene;

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
		
		int naveAncho = (int) dimension.getWidth() / 8;
		int naveAlto = 5;
		double x = dimension.getWidth() / 2;
		double y = dimension.getHeight() / 2; 
		
		Nave nave = new Nave(Color.BLACK, naveAncho, naveAlto, x, y, Double.parseDouble("500"), 0, dimension.getWidth(),0, dimension.getHeight());

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