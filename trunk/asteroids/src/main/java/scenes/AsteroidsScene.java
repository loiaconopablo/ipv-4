package scenes;

import java.util.ArrayList;
import java.util.List;

import asteroids.Nave;

import com.uqbar.vainilla.GameScene;

public class AsteroidsScene extends GameScene {


	private Nave nave;
	private List<Bala> balas = new ArrayList<Bala>();
	private List<Bloque> bloques = new ArrayList<Bloque>();
	private Marcador marcadorVidas;
	private Marcador marcadorPuntos;

	public Nave getNave() {
		return nave;
	}

	public void setNave(Nave nave) {
		this.addComponent(nave);
		this.nave = nave;
	}

	public List<Bala> getBalas() {
		return balas;
	}

	public void setBalas(List<Bala> balas) {
		this.addComponent(balas);
		this.balas = balas;
	}

	public List<Bloque> getBloques() {
		return bloques;
	}

	public void setBloques(List<Bloque> bloques) {
		this.addComponent(bloques);
		this.bloques = bloques;
	}

	public Marcador getMarcadorVidas() {
		return marcadorVidas;
	}

	public void setMarcadorVidas(Marcador marcadorVidas) {
		this.marcadorVidas = marcadorVidas;
		this.addComponent(marcadorVidas);
	}

	public Marcador getMarcadorPuntos() {
		return marcadorPuntos;
	}

	public void setMarcadorPuntos(Marcador marcadorPuntos) {
		this.marcadorPuntos = marcadorPuntos;
		this.addComponent(marcadorPuntos);
	}

//	public AsteroideScene agregarBloques(AsteroideScene arkanoidScene, Dimension dimension) {
//		int alto = 25;
//		int currentX = alto;
//		for (int i = 0; i < 4; i++) {
//			double anchoBloque = dimension.getWidth() / 5;
//			arkanoidScene.addBloque(new BloqueSimple(Color.CYAN, (int) anchoBloque, alto, currentX, alto));
//			arkanoidScene.addBloque(new BloqueSimple(Color.CYAN, (int)anchoBloque, alto, currentX, 75));
//			arkanoidScene.addBloque(new BloqueSimple(Color.CYAN, (int) anchoBloque, alto, currentX, 125));
//			currentX = currentX + (int) anchoBloque + alto;
//		}
//		return arkanoidScene;
//	}

	public void revisarFinDelJuego() {
		if (this.getBloques().isEmpty()) {
			this.fin();
		}
	}

	void fin() {
		this.getGame().setCurrentScene(
				((Asteroide) this.getGame()).buildEndScene(this.getMarcadorVidas(),this));

	}

}

