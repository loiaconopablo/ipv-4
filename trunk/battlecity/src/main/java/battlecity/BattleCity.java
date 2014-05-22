package battlecity;

import java.awt.Color;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import scenes.BattleCityScene;
import scenes.Marcador;
import utils.Tuning;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;

public class BattleCity extends Game {

	private Dimension dimension;
	private GameScene principalScene;

	@Override
	protected void initializeResources() {
		Tuning.load();
		dimension = new Dimension(800, 600);
	}

	@Override
	protected void setUpScenes() {
		GameScene asteroidsScene = buildBattleScene();
	
		this.setCurrentScene(asteroidsScene);
		this.setPrincipalScene(asteroidsScene);
	}

	public GameScene buildBattleScene() {
	
				
		Tanque tanque = new Tanque(300,500,0, dimension.getWidth(),0, dimension.getHeight());
		BattleCityScene battleScene = new BattleCityScene(this);
		
		//this.factoryBloques(3, battleScene, BloqueChico.class);
	
		battleScene.setNave(tanque);
		
		battleScene.setMarcadorPuntos(new Marcador(dimension.getWidth() / 4,
				-8, Color.blue, 0));

		battleScene.setMarcadorVidas(new Marcador(
				3 * dimension.getWidth() / 4, -8,
				Color.green, 3));
		
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
		return "Asteroids";
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
	
}