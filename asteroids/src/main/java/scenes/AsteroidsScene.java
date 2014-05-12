package scenes;

import java.util.ArrayList;
import java.util.List;

import asteroids.Asteroids;
import asteroids.Bala;
import asteroids.Bloque;
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

	public void addBala (Bala bala){
		this.addComponent(bala);
		this.balas.add(bala);
	}
	
	public void addBloque (Bloque bloque){
		this.addComponent(bloque);
		this.bloques.add(bloque);
	}
	
	public List<Bala> getBalas() {
		return balas;
	}

	public void setBalas(List<Bala> balas) {
		this.balas = balas;
	}

	public List<Bloque> getBloques() {
		return bloques;
	}

	public void setBloques(List<Bloque> bloques) {
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

	public void revisarFinDelJuego() {
		if (this.marcadorVidas.getValue() <= 0 || this.marcadorPuntos.getValue() >=1500) {
			this.fin();
		}
	}

	void fin() {
		this.getGame().setCurrentScene(
				((Asteroids) this.getGame()).buildEndScene(this.getMarcadorVidas(),this));

	}

}

