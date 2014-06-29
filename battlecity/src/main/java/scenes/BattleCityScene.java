package scenes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import map.MapManager;
import battlecity.BattleCity;
import battlecity.Bala;
import battlecity.Bloque;
import battlecity.Grilla;
import battlecity.HalconVida;
import battlecity.Posicion;
import battlecity.Tanque;
import battlecity.TanqueEnemigo;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

public class BattleCityScene extends GameScene {


	private Tanque tanque;
	private List<Bala> balas = new ArrayList<Bala>();
	private List<TanqueEnemigo> tanquesEnemigos = new ArrayList<TanqueEnemigo>(); // pool de tanques enemigos	

	private List<Bloque> bloques = new ArrayList<Bloque>(); //Va a contener a todos los tipos de terrenos
	private Marcador marcadorVidas; 
	private Texto labelVidas;
	private Marcador marcadorPuntos; 
	private Texto labelMarcador;
	private boolean finDeJuego = false;
	private Grilla grilla = new Grilla();
	

	public Grilla getGrilla() {
		return grilla;
	}

	public void setGrilla(Grilla grilla) {
		this.grilla = grilla;
	}

	private GameComponent<GameScene> backGroundOne;
	private GameComponent<GameScene> backGroundTwo;
	private HalconVida vida;
	
	private MapManager manager = new MapManager();
	
	public BattleCityScene(BattleCity game){
		this.manager.build(this,game);
		
//		this.agregarTanquesEnemigos(new TanqueEnemigo(600,100,0, game.getDisplayWidth(),0, game.getDisplayHeight()));
//		this.agregarTanquesEnemigos(new TanqueEnemigo(300,100,0, game.getDisplayWidth(),0, game.getDisplayHeight()));
		//Hay que crear una interfaz para los enemigos,,sino cuando apretas las teclas se mueven, como si fuera el tuyo
		this.buildBackgroundOne(game.getDisplayWidth(),game.getDisplayHeight());		
		this.buildBackgroundTwo(game.getDisplayWidth(),game.getDisplayHeight());
		
	}
	
	public void buildComponent(int x, int y, GameComponent elemento){
		y = this.generarPosicion(y);
		x = this.generarPosicion(x);
		elemento.setX(x);
		elemento.setY(y);
		this.bloques.add((Bloque) elemento);
		this.addComponent(elemento);
	}
	public void buildTanque(int x, int y, double xMax, double yMax ) {
		int ynew = this.generarPosicion(y);
		int xnew = this.generarPosicion(x);
		//Tanque tanque = new Tanque(200,500,0, dimensionCuadro.getWidth(),0, dimensionCuadro.getHeight());
		Tanque tanque = new Tanque(xnew,ynew,0, xMax  ,0, yMax);
//		this.agregarALaGrilla(x,y,tanque);	
		this.setTanque(tanque);
	}
	public void buildVidaPrincipal(int x, int y) {
		int ynew = this.generarPosicion(y);
		int xnew = this.generarPosicion(x);
		this.vida= new HalconVida(Sprite.fromImage("/halcon.png"), xnew, ynew);
		this.addComponent(vida);
	}

	private int generarPosicion(int x) {
		return x * 50;
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
	
	public Tanque getTanque() {
		return tanque;
	}

	public void setTanque(Tanque tanque) {
		this.addComponent(tanque);
		this.tanque = tanque;
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
	
	public HalconVida getVida() {
		return vida;
	}

	public void setVida(HalconVida vida) {
		this.vida = vida;
	}

	public void setMarcadorPuntos(Marcador marcadorPuntos) {
		this.marcadorPuntos = marcadorPuntos;
		this.addComponent(marcadorPuntos);
	}
	
	public void revisarFinDelJuego() {
		if (this.marcadorVidas.getValue() <= 0 || this.marcadorPuntos.getValue() >=500) {
			this.fin();
			this.setFinDeJuego(true);
		}
	}

	public void fin() {
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

	public boolean isFinDeJuego() {
		return finDeJuego;
	}

	public void setFinDeJuego(boolean finDeJuego) {
		this.finDeJuego = finDeJuego;
	}

	public MapManager getManager() {
		return manager;
	}

	public void setManager(MapManager manager) {
		this.manager = manager;
	}

	public void agregarALaGrilla(int x, int y, GameComponent elemento) {
		this.getGrilla().setPosicion(x, y,new Posicion(x,y, elemento));
	}

	

	
}

