package scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import battlecity.BattleCity;
import battlecity.Bala;
import battlecity.Bloque;
import battlecity.Cemento;
import battlecity.Ladrillo;
import battlecity.Pasto;
import battlecity.Tanque;
import battlecity.TanqueEnemigo;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;

public class BattleCityScene extends GameScene {


	private Tanque nave;
	private List<Bala> balas = new ArrayList<Bala>();
	private List<TanqueEnemigo> tanquesEnemigos = new ArrayList<TanqueEnemigo>(); // pool de tanques enemigos	

	private List<Bloque> bloques = new ArrayList<Bloque>(); //Va a contener a todos los tipos de terrenos
	private Marcador marcadorVidas; 
	private Texto labelVidas;
	private Marcador marcadorPuntos; 
	private Texto labelMarcador;
	private GameComponent<GameScene> backGroundOne;
	private GameComponent<GameScene> backGroundTwo;
	
	public BattleCityScene(BattleCity game){
		this.agregarTanquesEnemigos(new TanqueEnemigo(300,100,0, game.getDisplayWidth(),0, game.getDisplayHeight()));
		this.agregarTanquesEnemigos(new TanqueEnemigo(600,100,0, game.getDisplayWidth(),0, game.getDisplayHeight()));
		//Hay que crear una interfaz para los enemigos,,sino cuando apretas las teclas se mueven, como si fuera el tuyo
		
		this.buildBackgroundOne(game.getDisplayWidth(),game.getDisplayHeight());		
		this.buildBackgroundTwo(game.getDisplayWidth(),game.getDisplayHeight());
		this.buildLadrillos();
		this.buildCementos();
		this.buildPastos();
	}
	
	private void buildPastos() {
		Pasto pasto = new Pasto(150,100);
		//this.bloques.add(pasto);
		this.addComponent(pasto);
	}

	private void buildCementos() {
		Cemento cemento = new Cemento(200,100);
		this.bloques.add(cemento);
		this.addComponent(cemento);
	}

	private void buildLadrillos() {
		Ladrillo ladri = new Ladrillo(100,100);
		this.bloques.add(ladri);
		this.addComponent(ladri);
		
	}

	private void buildBackgroundOne(int ancho, int alto) {
		this.backGroundOne = new GameComponent<GameScene>(new Rectangle(Color.BLACK,
				ancho-150, alto), 0, 0);
		this.backGroundOne.setZ(-1);
		this.addComponent(this.backGroundOne);
	}
	private void buildBackgroundTwo(int ancho, int alto) {
		this.backGroundTwo = new GameComponent<GameScene>(new Rectangle(Color.GRAY,
				ancho, alto), 0, 0);
		this.backGroundTwo.setZ(-2);
		this.addComponent(this.backGroundTwo);
	}
	
	public void agregarTanquesEnemigos(TanqueEnemigo tanque){
		this.tanquesEnemigos.add(tanque);
		this.addComponent(tanque);
	}
	
	public Tanque getNave() {
		return nave;
	}

	public void setNave(Tanque nave) {
		this.addComponent(nave);
		this.nave = nave;
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
		if (this.marcadorVidas.getValue() <= 0 || this.marcadorPuntos.getValue() >=500) {
			this.fin();
		}
	}

	void fin() {
		this.getGame().setCurrentScene(
				((BattleCity) this.getGame()).buildEndScene(this.getMarcadorVidas(),this));
	}
	
	public GameComponent<GameScene> getBackGroundTwo() {
		return backGroundTwo;
	}

	public void setBackGroundTwo(GameComponent<GameScene> backGroundTwo) {
		this.backGroundTwo = backGroundTwo;
	}

	public GameComponent<GameScene> getBackGroundOne() {
		return backGroundOne;
	}

	public void setBackGroundOne(GameComponent<GameScene> backGroundOne) {
		this.backGroundOne = backGroundOne;
	}

	public Texto getLabelVidas() {
		return labelVidas;
	}

	public void setLabelVidas(Texto labelVidas) {
		this.labelVidas = labelVidas;
		this.addComponent(labelVidas);
	}

	public Texto getLabelMarcador() {
		return labelMarcador;
	}

	public void setLabelMarcador(Texto labelMarcador) {
		this.labelMarcador = labelMarcador;
		this.addComponent(labelMarcador);
	}

	public List<Bloque> getBloques() {
		return bloques;
	}

	public void setBloques(List<Bloque> bloques) {
		this.bloques = bloques;
	}
	
	public List<Bala> getBalas() {
		return balas;
	}

	public void setBalas(List<Bala> balas) {
		this.balas = balas;
	}

	public List<TanqueEnemigo> getTanquesEnemigos() {
		return tanquesEnemigos;
	}
	
	public void setTanquesEnemigos(List<TanqueEnemigo> tanquesEnemigos) {
		this.tanquesEnemigos = tanquesEnemigos;
	}
}

