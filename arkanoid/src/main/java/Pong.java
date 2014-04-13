

import java.awt.Color;
import java.awt.Dimension;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;

public class Pong extends Game {

	private Dimension dimension;

	@Override
	protected void initializeResources() {
		Tuning.load();
		dimension = new Dimension(800, 600);
	}

	@Override
	protected void setUpScenes() {
		GameScene pongScene = buildPongScene();

		this.setCurrentScene(pongScene);
	}

	public GameScene buildPongScene() {
		PongScene pongScene = new PongScene();
		
		int raquetaAncho = (int) dimension.getWidth() / 8;
		int raquetaAlto = 3;
		double raquetaX = dimension.getWidth() / 2 - raquetaAncho / 2;
		
		pongScene.setRaqueta(new Raqueta(raquetaX, 9 * dimension
				.getHeight() / 10, raquetaAncho, raquetaAlto, Color.BLACK,
				Double.parseDouble("500"), 0, dimension.getWidth(),
				Tuning.newInstance("player.strategy", RaquetaPlayerStrategy.class)));

		pongScene.setPelota(new Pelota((int) dimension.getWidth()
				/ Tuning.getInteger("pelota.radio"), dimension.getWidth()
				/ Tuning.getDouble("pelota.x", 2.0), dimension.getHeight()
				/ Tuning.getDouble("pelota.y", 2.0), new Vector2D(Tuning
				.getDouble("pelota.direccion.x", 0.0), Tuning.getDouble(
				"pelota.direccion.y", 1.0)), Tuning.getDouble("pelota.speed", 0.7)));
		pongScene.getPelota().setVelocidadStep(Tuning.getDouble("pelota.delta"));


		pongScene.setBloque(new Bloque(raquetaX/2,2 * dimension.getHeight() / 10, raquetaAncho, 10,
				Color.GRAY));

		pongScene.setBloque(new Bloque(raquetaX,3 * dimension.getHeight() / 10, raquetaAncho*2 , 10,
				Color.GRAY));
		pongScene.setBloque(new Bloque(raquetaX*2,1 * dimension.getHeight() / 10, raquetaAncho/2, 10,
				Color.GRAY));
		pongScene.setBloque(new Bloque(30,1 * dimension.getHeight() / 10, raquetaAncho+10, 10,
				Color.GRAY));
		
		
		return pongScene;
	}

	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {
		return "Pong";
	}

	public static void main(String[] args) {
		new DesktopGameLauncher(new Pong()).launch();
	}
}