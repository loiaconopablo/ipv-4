
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Label;

public class Arkanoid extends Game {

	private Dimension dimension;
	private GameScene principalScene;

	@Override
	protected void initializeResources() {
		Tuning.load();
		dimension = new Dimension(800, 600);
	}

	@Override
	protected void setUpScenes() {
		GameScene arkanoidScene = buildArkanoidScene();

		this.setCurrentScene(arkanoidScene);
		this.setPrincipalScene(arkanoidScene);
	}

	public GameScene buildArkanoidScene() {
		ArkanoidScene arkanoidScene = new ArkanoidScene();

		int raquetaAncho = (int) dimension.getWidth() / 8;
		int raquetaAlto = 3;
		double raquetaX = dimension.getWidth() / 2 - raquetaAncho / 2;

		arkanoidScene.setRaqueta(new Raqueta(raquetaX, 9 * dimension
				.getHeight() / 10, raquetaAncho, raquetaAlto, Color.BLACK,
				Double.parseDouble("500"), 0, dimension.getWidth(), Tuning
						.newInstance("player.strategy",
								RaquetaPlayerStrategy.class)));

		arkanoidScene.setPelota(new Pelota((int) dimension.getWidth()
				/ Tuning.getInteger("pelota.radio"), dimension.getWidth()
				/ Tuning.getDouble("pelota.x", 2.0), dimension.getHeight()
				/ Tuning.getDouble("pelota.y", 2.0), new Vector2D(Tuning
				.getDouble("pelota.direccion.x", 0.0), Tuning.getDouble(
				"pelota.direccion.y", 1.0)), Tuning.getDouble("pelota.speed",
				0.7)));

		arkanoidScene.getPelota().setVelocidadStep(
				Tuning.getDouble("pelota.delta"));

		arkanoidScene.setMarcadorPuntos(new Marcador(dimension.getWidth() / 4,
				dimension.getHeight() / 2, Color.blue, 0));

		arkanoidScene.setMarcadorVidas(new Marcador(
				3 * dimension.getWidth() / 4, dimension.getHeight() / 2,
				Color.green, 3));

		arkanoidScene.agregarBloques(arkanoidScene, dimension);
	
		
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

	public GameScene buildEndScene(Marcador marcadorPuntos, ArkanoidScene arkanoidScene) {
		return new EndScene(dimension.getWidth() / 6,
				dimension.getHeight() / 10, marcadorPuntos, arkanoidScene);
	}

	public GameScene getPrincipalScene() {
		return principalScene;
	}

	public void setPrincipalScene(GameScene arkanoidScene) {
		this.principalScene = arkanoidScene;
	}
}