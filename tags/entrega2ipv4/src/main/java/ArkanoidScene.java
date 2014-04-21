import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Label;

public class ArkanoidScene extends GameScene {

	private Pelota pelota;
	private Raqueta raqueta;
	private List<Bloque> bloques = new ArrayList<Bloque>();
	private Marcador marcadorVidas;
	private Marcador marcadorPuntos;


	public Pelota getPelota() {
		return pelota;
	}

	public void setPelota(Pelota pelota) {
		this.addComponent(pelota);
		this.pelota = pelota;
	}

	public void setRaqueta(Raqueta raqueta) {
		this.raqueta = raqueta;
		this.addComponent(raqueta);
	}

	public Raqueta getRaqueta() {
		return raqueta;
	}

	public void addBloque(Bloque bloque) {
		this.bloques.add(bloque);
		this.addComponent(bloque);
	}

	public List<Bloque> getBloques() {
		return bloques;
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

	public ArkanoidScene agregarBloques(ArkanoidScene arkanoidScene, Dimension dimension) {
		int alto = 25;
		int currentX = alto;
		for (int i = 0; i < 4; i++) {
			double anchoBloque = dimension.getWidth() / 5;
			arkanoidScene.addBloque(new BloqueSimple(Color.CYAN, (int) anchoBloque, alto, currentX, alto));
			arkanoidScene.addBloque(new BloqueSimple(Color.CYAN, (int)anchoBloque, alto, currentX, 75));
			arkanoidScene.addBloque(new BloqueSimple(Color.CYAN, (int) anchoBloque, alto, currentX, 125));
			currentX = currentX + (int) anchoBloque + alto;
		}
		return arkanoidScene;
	}

	public void revisarFinDelJuego() {
		if (this.getBloques().isEmpty()) {
			this.fin();
		}
	}

	void fin() {
		this.getGame().setCurrentScene(
				((Arkanoid) this.getGame()).buildEndScene(this.getMarcadorVidas(),this));

	}

}