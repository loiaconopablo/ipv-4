package scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import battlecity.BattleCity;
import battlecity.Bala;
import battlecity.Bloque;
import battlecity.BloqueChico;
import battlecity.BloqueMediano;
import battlecity.Tanque;
import battlecity.TanqueEnemigo;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;

public class BattleCityScene extends GameScene {


	private Tanque nave;
	private List<Bala> balas = new ArrayList<Bala>();
	private List<TanqueEnemigo> tanquesEnemigos = new ArrayList<TanqueEnemigo>(); // pool de tanques enemigos		
	private Marcador marcadorVidas;
	private Marcador marcadorPuntos;
	private GameComponent<GameScene> backGroundOne;
	private GameComponent<GameScene> backGroundTwo;
	
	public BattleCityScene(BattleCity game){
		this.tanquesEnemigos.add(new TanqueEnemigo(300,100,0, game.getDisplayWidth(),0, game.getDisplayHeight()));
		this.tanquesEnemigos.add(new TanqueEnemigo(600,100,0, game.getDisplayWidth(),0, game.getDisplayHeight()));
		
		this.buildBackgroundOne(game.getDisplayWidth(),game.getDisplayHeight());
		this.buildBackgroundTwo(game.getDisplayWidth(),game.getDisplayHeight());
	}
	
	private void buildBackgroundOne(int ancho, int alto) {
		this.backGroundOne = new GameComponent<GameScene>(new Rectangle(Color.BLACK,
				ancho, alto), 0, 0);
		this.backGroundOne.setZ(-1);
		this.addComponent(this.backGroundOne);
	}
	private void buildBackgroundTwo(int ancho, int alto) {
		this.backGroundTwo = new GameComponent<GameScene>(new Rectangle(Color.GRAY,
				ancho+100, alto+100), -20, -20);
		this.backGroundTwo.setZ(-2);
		this.addComponent(this.backGroundTwo);
	}
	
	public Tanque getNave() {
		return nave;
	}

	public void setNave(Tanque nave) {
		this.addComponent(nave);
		this.nave = nave;
	}

	public void addBala (Bala bala){
		this.addComponent(bala);
		this.balas.add(bala);
	}
	
	public List<Bala> getBalas() {
		return balas;
	}

	public void setBalas(List<Bala> balas) {
		this.balas = balas;
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
}

