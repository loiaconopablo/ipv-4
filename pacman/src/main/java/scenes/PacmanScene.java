package scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import pacman.Comida;
import pacman.Pacman;
import pacman.Bloque;
import pacman.Pared;
import pacman.Grilla;
import pacman.TerrenoVacio;
import pacman.Posicion;
import pacman.Personaje;
import pacman.Fantasma;
import map.Map;
import map.MapManager;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

public class PacmanScene extends GameScene {


	private Personaje personaje;
	private List<Comida> comidas = new ArrayList<Comida>();
	private List<Fantasma> fantasmas = new ArrayList<Fantasma>(); // pool de fantasmas enemigos	
	private List<Bloque> bloques = new ArrayList<Bloque>(); //Va a contener a todos los tipos de bloques
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
	
	private MapManager manager = new MapManager();
	
	public PacmanScene(Pacman game){
		this.manager.build(this);
		
		//this.agregarFantasmas(new Fantasma(300,100,0, game.getDisplayWidth(),0, game.getDisplayHeight()));
		//this.agregarFantasmas(new Fantasma(600,100,0, game.getDisplayWidth(),0, game.getDisplayHeight()));
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
		if(elemento.getClass() == TerrenoVacio.class)
		{this.crearComidaEnTerreno(x, y);}
	}


	public void buildPersonaje(int x, int y) {
		int ynew = this.generarPosicion(y);
		int xnew = this.generarPosicion(x);
		Personaje pac = new Personaje(xnew,ynew,0, 800  ,0, 600);
		this.agregarALaGrilla(x,y,pac);	
		this.setFantasma(pac);		
		
	}
	
	public void crearComidaEnTerreno(int x, int y) {
		Comida comida = new Comida(x+25,y+25);
		this.getComidas().add(comida);
		this.addComponent(comida);
		
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
	
	public void agregarFantasmas(Fantasma fantasma){
		this.fantasmas.add(fantasma);
		this.addComponent(fantasma);
	}
	
	public Personaje getTanque() {
		return personaje;
	}

	public void setFantasma(Personaje fantasma) {
		this.addComponent(fantasma);
		this.personaje = fantasma;
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
		if (this.marcadorVidas.getValue() <= 0 || this.getComidas().size()==0) {
			this.setFinDeJuego(true);
			this.fin();
		}
	}
	
	

	public void fin() {
		this.getGame().setCurrentScene(
				((Pacman) this.getGame()).buildEndScene(this.getMarcadorVidas(),this));
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
	
	public List<Comida> getComidas() {
		return comidas;
	}

	public void setComidas(List<Comida> comida) {
		this.comidas = comida;
	}

	public List<Fantasma> getFantasmas() {
		return fantasmas;
	}
	
	public void setFantasmas(List<Fantasma> fantasmas) {
		this.fantasmas = fantasmas;
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

