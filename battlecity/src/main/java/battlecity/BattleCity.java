package battlecity;

import java.awt.Color;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import scenes.BattleCityScene;
import scenes.EndScene;
import scenes.Marcador;
import scenes.StartScene;
import scenes.Texto;
import utils.Tuning;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;

public class BattleCity extends Game {

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
		GameScene battleScene = buildBattleScene();
//		this.setCurrentScene(battleScene);
//		this.setPrincipalScene(battleScene);
//		
		GameScene inicioScene = buildStartScene(battleScene);
		this.setCurrentScene(inicioScene);
		this.setPrincipalScene(inicioScene);
		
		
	}

	
	
	
	
	public GameScene buildBattleScene() {
	
				
		Tanque tanque = new Tanque(300,500,0, dimensionCuadro.getWidth(),0, dimensionCuadro.getHeight());
		BattleCityScene battleScene = new BattleCityScene(this);
		
		//this.factoryBloques(3, battleScene, BloqueChico.class);
	
		battleScene.setTanque(tanque);
		
		battleScene.setLabelMarcador(new Texto(810, 20, Color.WHITE, "MARCADOR"));
		battleScene.setMarcadorPuntos(new Marcador(830, 45, Color.blue, 0));
		battleScene.setLabelVidas(new Texto(810, 120, Color.WHITE, "VIDAS"));
		battleScene.setMarcadorVidas(new Marcador(830, 145,	Color.green, 3));
		
		return battleScene;
	}
	
	
//	private void factoryBloques(int cant, BattleCity scena, Class<? extends Bloque > tipoBloque ) {
//		for(int i = 0; i < cant; i++)
//			{int ypos = (int) (new Random().nextDouble() * this.getDisplaySize().getHeight());
//			int xpos = (int) (new Random().nextDouble() * this.getDisplaySize().getWidth());	
//			scena.addBloque(this.getInstancia(tipoBloque, xpos, ypos));
//			}
//	}
//	
//	public Bloque getInstancia(Class<? extends Bloque > tipoBloque, int xpos, int ypos){
//		try {
//			return tipoBloque.getConstructor(int.class,int.class).newInstance(xpos,ypos);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			throw new RuntimeException(e);
//		}
//		
//	}

	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {
		return "BattleCity";
	}

	public static void main(String[] args) {
		new DesktopGameLauncher(new BattleCity()).launch();
	}

	public GameScene getPrincipalScene() {
		return principalScene;
	}

	public void setPrincipalScene(GameScene principalScene) {
		this.principalScene = principalScene;
	}

	public GameScene buildEndScene(Marcador marcadorPuntos, BattleCityScene battleScene) {
		return new EndScene(dimension.getWidth() / 6,
				dimension.getHeight() / 10, marcadorPuntos, battleScene);
	}
	public GameScene buildStartScene(GameScene battleScene) {
		return new StartScene(dimension.getWidth() / 6,
				dimension.getHeight() / 2, battleScene);
	}

	public Dimension getDimensionCuadro() {
		return dimensionCuadro;
	}

	public void setDimensionCuadro(Dimension dimensionCuadro) {
		this.dimensionCuadro = dimensionCuadro;
	}
	
}