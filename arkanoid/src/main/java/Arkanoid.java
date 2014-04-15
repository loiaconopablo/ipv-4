

import java.awt.Color;
import java.awt.Dimension;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;

public class Arkanoid extends Game {

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
		ArkanoidScene arkanoidScene = new ArkanoidScene();
		
		int raquetaAncho = (int) dimension.getWidth() / 8;
		int raquetaAlto = 3;
		double raquetaX = dimension.getWidth() / 2 - raquetaAncho / 2;
		
		arkanoidScene.setRaqueta(new Raqueta(raquetaX, 9 * dimension
				.getHeight() / 10, raquetaAncho, raquetaAlto, Color.BLACK,
				Double.parseDouble("500"), 0, dimension.getWidth(),
				Tuning.newInstance("player.strategy", RaquetaPlayerStrategy.class)));

		arkanoidScene.setPelota(new Pelota((int) dimension.getWidth()
				/ Tuning.getInteger("pelota.radio"), dimension.getWidth()
				/ Tuning.getDouble("pelota.x", 2.0), dimension.getHeight()
				/ Tuning.getDouble("pelota.y", 2.0), new Vector2D(Tuning
				.getDouble("pelota.direccion.x", 0.0), Tuning.getDouble(
				"pelota.direccion.y", 1.0)), Tuning.getDouble("pelota.speed", 0.7)));
		arkanoidScene.getPelota().setVelocidadStep(Tuning.getDouble("pelota.delta"));
		
		int alto = 25;
		int currentX= alto;
		for (int i = 0; i < 4; i++) {
			double anchoBloque = dimension.getWidth() / 5;
			arkanoidScene.addBloque(new BloqueSimple(Color.CYAN, (int) anchoBloque, alto, currentX, alto));
			arkanoidScene.addBloque(new BloqueSimple(Color.CYAN, (int) anchoBloque, alto, currentX, 75));
			arkanoidScene.addBloque(new BloqueSimple(Color.CYAN, (int) anchoBloque, alto, currentX, 125));
			currentX = currentX + (int) anchoBloque + alto;
		}

		
		return arkanoidScene;
	}

	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {
		return "Arkanoid";
	}

	public static void main(String[] args) {
		new DesktopGameLauncher(new Arkanoid()).launch();
	}
}