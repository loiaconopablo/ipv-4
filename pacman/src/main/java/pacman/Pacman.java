package pacman;

import java.awt.Color;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import scenes.PacmanScene;
import scenes.EndScene;
import scenes.Marcador;
import scenes.StartScene;
import scenes.Texto;
import utils.Tuning;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;

public class Pacman extends Game {

	private Dimension dimension;
	private Dimension dimensionCuadro;
	private GameScene principalScene;

	@Override
	protected void initializeResources() {
		Tuning.load();
		setDimensionCuadro(new Dimension(800, 600));
		dimension = new Dimension(950,600);
	}

	@Override
	protected void setUpScenes() {
		GameScene pacmanScene = buildPacmanScene();
//		this.setCurrentScene(pacmanScene);
//		this.setPrincipalScene(pacmanScene);
		
		GameScene inicioScene = buildStartScene(pacmanScene);
		this.setCurrentScene(inicioScene);
		this.setPrincipalScene(inicioScene);
		
		
	}
	
	public GameScene buildPacmanScene() {
			
		Personaje tanque = new Personaje(200,500,0, dimensionCuadro.getWidth(),0, dimensionCuadro.getHeight());
		PacmanScene pacmanScene = new PacmanScene(this);
			
		pacmanScene.setFantasma(tanque);
		
		pacmanScene.setLabelMarcador(new Texto(810, 20, Color.WHITE, "MARCADOR"));
		pacmanScene.setMarcadorPuntos(new Marcador(830, 45, Color.blue, 0));
		pacmanScene.setLabelVidas(new Texto(810, 120, Color.WHITE, "VIDAS"));
		pacmanScene.setMarcadorVidas(new Marcador(830, 145,	Color.green, 3));
		
		return pacmanScene;
	}
	
	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {
		return "BattleCity";
	}

	public static void main(String[] args) {
		new DesktopGameLauncher(new Pacman()).launch();
	}

	public GameScene getPrincipalScene() {
		return principalScene;
	}

	public void setPrincipalScene(GameScene principalScene) {
		this.principalScene = principalScene;
	}

	public GameScene buildEndScene(Marcador marcadorPuntos, PacmanScene pacmanScene) {
		return new EndScene(dimension.getWidth() / 6,
				dimension.getHeight() / 10, marcadorPuntos, pacmanScene);
	}
	public GameScene buildStartScene(GameScene pacmanScene) {
		return new StartScene(dimension.getWidth() / 6,
				//dimension.getHeight() / 4, pacmanScene);
				500, pacmanScene);
	}

	public Dimension getDimensionCuadro() {
		return dimensionCuadro;
	}

	public void setDimensionCuadro(Dimension dimensionCuadro) {
		this.dimensionCuadro = dimensionCuadro;
	}
	
}