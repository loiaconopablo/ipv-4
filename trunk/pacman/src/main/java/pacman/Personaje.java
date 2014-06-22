package pacman;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import scenes.PacmanScene;
import utils.Vector2D;
import colisiones.ColisionTanqueBloqueRule;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

public class Personaje extends GameComponent<PacmanScene> {

	private double xInicial;
	private double yInicial;
	private double xMin;
	private double xMax;
	private double yMin;
	private double yMax;
	private List<ColisionTanqueBloqueRule> rules = new ArrayList<ColisionTanqueBloqueRule>();
	private static Sprite image = Sprite.fromImage("personajeAbiertoIzquierda.png");
	private double velocidad;
	private double velocidadInicial;
	private double tiempo = 1;
	private double comparador = 1;
	private double contadorFantasmas = 0;
	private boolean bocaAbierta = true;
	private boolean enMoviento = false;
	private Sound music;
	private String direccion;

	public Personaje(double posx, double posy, double xMin, double xMax,
			double yMin, double yMax) {
		super(image, posx, posy);
		this.xInicial = posx;
		this.yInicial = posy;
		this.setxMin(xMin);
		this.setxMax(xMax);
		this.setyMin(yMin);
		this.setyMax(yMax);
		this.setVelocidad(Double.parseDouble("100"));
		this.initSound();
		
	}

	private void actualizarSpriteYDireccion(DeltaState deltaState) {
		double delta = deltaState.getDelta();
		double deltaSpeed = 20;
		Posicion actual = this.posicionActual();
//		if(this.noHayObstaculos(actual)){
			

		if (deltaState.isKeyPressed(Key.UP) || this.direccion == "UP") {
			this.direccion = "UP";
			this.setY(Math.max(this.getY() - getVelocidad() * delta, getyMin()));
			this.intercambiarApariencia("personajeCerradoArriba.png","personajeAbiertoArriba.png",deltaState);
			//anguloDisparo = -Math.PI / 2;
		}
		if (deltaState.isKeyPressed(Key.DOWN)|| this.direccion == "DOWN") {
			this.direccion = "DOWN";
			this.setY(Math.min(getyMax() - this.getAppearance().getHeight(),this.getY() + getVelocidad() * delta));
			this.intercambiarApariencia("personajeCerradoAbajo.png","personajeAbiertoAbajo.png",deltaState);
			//anguloDisparo = Math.PI / 2;
		}
		if (deltaState.isKeyPressed(Key.RIGHT)|| this.direccion == "RIGHT") {
			this.direccion = "RIGHT";
			this.setX(Math.min(getxMax() - this.getAppearance().getWidth(),this.getX() + getVelocidad() * delta));
			this.intercambiarApariencia("personajeCerradoDerecha.png","personajeAbiertoDerecha.png",deltaState);
			//anguloDisparo = 2 * Math.PI;
		}
		if (deltaState.isKeyPressed(Key.LEFT)|| this.direccion == "LEFT") {
			this.direccion = "LEFT";
			this.setX(Math.max(this.getX() - getVelocidad() * delta, getxMin()));
			this.intercambiarApariencia("personajeCerradoIzquierda.png","personajeAbiertoIzquierda.png",deltaState);
			//anguloDisparo = Math.PI;
		}
		
	}

	private void intercambiarApariencia(String string, String string2, DeltaState deltaState) {
		//this.setAppearance(Sprite.fromImage(string2));
		this.tiempo += deltaState.getDelta();
		if( this.tiempo >= this.comparador){
			this.cambiarApariencia(string,string2);
			this.comparador += 0.1;
		}
		
	}

	private void initSound() 
	{this.music= new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("music.wav"));
	}

	private void cambiarApariencia(String string, String string2) {
		if(this.bocaAbierta)
		{this.setAppearance(Sprite.fromImage(string));
		this.bocaAbierta = false;}
		else{
			this.setAppearance(Sprite.fromImage(string2));
			this.bocaAbierta = true;}
	}

	public boolean noHayObstaculos(Posicion actual) {
		return this.getScene().getGrilla().noHayBloque(actual);
	}
	
	public Posicion posicionActual(){
		return this.getScene().getGrilla().getMapa()[(int)this.getY() / 50][(int)this.getX() / 50];
	}
	
	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
//		for(ColisionTanqueBloqueRule rule : this.getRules()) {
//			if(rule.mustApply(this, this.getScene())) {
//				rule.apply(this, this.getScene());
//				break;
//			}
//		}
		this.actualizarSpriteYDireccion(deltaState);
		this.contadorFantasmas += deltaState.getDelta();
		if(this.contadorFantasmas >= 5.0){
			this.contadorFantasmas = 0.0;
			this.getScene().agregarFantasmas(new Fantasma(700,100,0, this.getScene().getGame().getDisplayWidth(),0, this.getScene().getGame().getDisplayHeight()));
		}
		
	}

		
	public void resetCentrar() {
		//this.setVelocidad(velocidadInicial);
		this.setX(xInicial);
		this.setY(yInicial);
	}


	// Accesors


	public double getAncho() {
		return this.getAppearance().getWidth();

	}
	
	private void initRules() {
		for (Bloque bloque : this.getScene().getBloques() ) {
			this.rules.add(new ColisionTanqueBloqueRule(bloque));
		}

	}
	
	public List<ColisionTanqueBloqueRule> getRules() {
		if (this.rules.isEmpty()) {
			this.initRules();
		}
		return this.rules;
	}

//	public Vector2D getPolarVelocity() {
//		return velocidadPolar;
//	}

	public double getxMin() {
		return xMin;
	}

	public void setxMin(double xMin) {
		this.xMin = xMin;
	}

	public double getxMax() {
		return xMax;
	}

	public void setxMax(double xMax) {
		this.xMax = xMax;
	}

	public double getyMin() {
		return yMin;
	}

	public void setyMin(double yMin) {
		this.yMin = yMin;
	}

	public double getyMax() {
		return yMax;
	}

	public void setyMax(double yMax) {
		this.yMax = yMax;
	}

	public double getxInicial() {
		return xInicial;
	}

	public void setxInicial(double xInicial) {
		this.xInicial = xInicial;
	}

	public double getyInicial() {
		return yInicial;
	}

	public void setyInicial(double yInicial) {
		this.yInicial = yInicial;
	}

	public Sprite getImage() {
		return image;
	}

	public void setImage(Sprite image) {
		Personaje.image = image;
	}

	public double getTiempo() {
		return tiempo;
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

	public double getComparador() {
		return comparador;
	}

	public void setComparador(double comparador) {
		this.comparador = comparador;
	}

	public double getVelocidadInicial() {
		return velocidadInicial;
	}

	public void setVelocidadInicial(double velocidadInicial) {
		this.velocidadInicial = velocidadInicial;
	}
	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public boolean isEnMoviento() {
		return enMoviento;
	}

	public void setEnMoviento(boolean enMoviento) {
		this.enMoviento = enMoviento;
	}

}
