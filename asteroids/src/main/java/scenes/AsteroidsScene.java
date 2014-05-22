package scenes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import asteroids.Asteroids;
import asteroids.Bala;
import asteroids.Bloque;
import asteroids.BloqueChico;
import asteroids.BloqueMediano;
import asteroids.Nave;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;

public class AsteroidsScene extends GameScene {


	private Nave nave;
	private List<Bala> balas = new ArrayList<Bala>();
	private List<Bloque> bloques = new ArrayList<Bloque>(); //incluye a los bloques grandes
	private List<BloqueMediano> bloquesMedianos = new ArrayList<BloqueMediano>(); // pool de bloques medianos		
	private List<BloqueChico> bloquesChicos = new ArrayList<BloqueChico>(); //pool de bloques chicos
	private Marcador marcadorVidas;
	private Marcador marcadorPuntos;
	
	public AsteroidsScene(){
		for(int i = 0; i <= 20; i++){
			this.bloquesChicos.add(new BloqueChico(0, 0));
			this.bloquesMedianos.add(new BloqueMediano(0, 0));
		}
	}
	
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

	public List<BloqueMediano> getBloquesMedianos() {
		return bloquesMedianos;
	}
	
	public void setBloquesMedianos(List<BloqueMediano> bloquesMedianos) {
		this.bloquesMedianos = bloquesMedianos;
	}
	
	public List<BloqueChico> getBloquesChicos() {
		return bloquesChicos;
	}
	
	public void setBloquesChicos(List<BloqueChico> bloquesChicos) {
		this.bloquesChicos = bloquesChicos;
	}
	
	public void revisarFinDelJuego() {
		if (this.marcadorVidas.getValue() <= 0 || this.marcadorPuntos.getValue() >=10) {
			this.fin();
		}
	}

	void fin() {
		this.getGame().setCurrentScene(
				((Asteroids) this.getGame()).buildEndScene(this.getMarcadorVidas(),this));

	}

	public boolean puedeAgregarBloques(){
		return this.getMarcadorPuntos().getValue() < 10;
	}

	public Bloque dameUnBloqueMediano() {
		if(this.getBloquesMedianos().isEmpty()){
			return new BloqueMediano(0,0);//creo el objeto bajo demanda
		}
		else{
			Bloque bloque = this.getBloquesMedianos().get(0);
			this.getBloquesMedianos().remove(0);
			return bloque;
		}
		
	}

	public Bloque dameUnBloqueChico() {
		if(this.getBloquesChicos().isEmpty()){
			return new BloqueChico(0,0);//creo el objeto bajo demanda
		}
		else{
			Bloque bloque = this.getBloquesChicos().get(0);
			this.getBloquesChicos().remove(0);
			return bloque;
		}
	}
}

