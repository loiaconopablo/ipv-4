package asteroids;

import java.awt.Color;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import scenes.AsteroidsScene;
import scenes.Marcador;
import utils.Tuning;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;

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
		int lado = 30;
		double x = dimension.getWidth() / 2;
		double y = dimension.getHeight() / 2; 
				
		Nave nave = new Nave(Color.BLACK, lado, lado, x, y, 0, dimension.getWidth(),0, dimension.getHeight());
		AsteroidsScene asteroidsScene = new AsteroidsScene();
		
		this.factoryBloques(3, asteroidsScene, BloqueChico.class);
		this.factoryBloques(3, asteroidsScene, BloqueGrande.class);
		this.factoryBloques(3, asteroidsScene, BloqueMediano.class);
		
		asteroidsScene.setNave(nave);
		
		asteroidsScene.setMarcadorPuntos(new Marcador(dimension.getWidth() / 4,
				-8, Color.blue, 0));

		asteroidsScene.setMarcadorVidas(new Marcador(
				3 * dimension.getWidth() / 4, -8,
				Color.green, 3));
		
		return asteroidsScene;
	}
	
	

	private void factoryBloques(int cant, AsteroidsScene scena, Class<? extends Bloque > tipoBloque ) {
		//Generar Bloques Grandes
		for(int i = 0; i < cant; i++)
			{int ypos = (int) (new Random().nextDouble() * this.getDisplaySize().getHeight());
			int xpos = (int) (new Random().nextDouble() * this.getDisplaySize().getWidth());	
			scena.addBloque(this.getInstancia(tipoBloque, xpos, ypos));
			}
	}
	
	public Bloque getInstancia(Class<? extends Bloque > tipoBloque, int xpos, int ypos){
		try {
			return tipoBloque.getConstructor(int.class,int.class).newInstance(xpos,ypos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
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

	public GameScene buildEndScene(Marcador marcadorPuntos, AsteroidsScene asteroidsScene) {
		return new EndScene(dimension.getWidth() / 6,
				dimension.getHeight() / 10, marcadorPuntos, asteroidsScene);
	}
	
}