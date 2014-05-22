package battlecity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import scenes.BattleCityScene;
import utils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;

public class Tanque extends GameComponent<BattleCityScene> {

	private double xInicial;
	private double yInicial;
	private double xMin;
	private double xMax;
	private double yMin;
	private double yMax;
	
	private List<TanqueRule> rules = new ArrayList<TanqueRule>();
	
	private double velocidad;
	private double velocidadInicial;
	private double rapidezDisparo = Double.parseDouble("50");
	private static Sprite image = Sprite.fromImage("tanqueArriba.png");
	

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public double getVelocidadInicial() {
		return velocidadInicial;
	}

	public void setVelocidadInicial(double velocidadInicial) {
		this.velocidadInicial = velocidadInicial;
	}

	public Tanque(double posx, double posy, double xMin, double xMax, double yMin, double yMax) {
		super(image, posx, posy);
//		this.xInicial = 300;
//		this.yInicial = 500;
		this.setVelocidadInicial(0);
		this.setVelocidad(Double.parseDouble("100"));
		this.setxMin(xMin);
		this.setxMax(xMax);
		this.setyMin(yMin);
		this.setyMax(yMax);
	}

	@Override
	public void update(DeltaState deltaState) {
		actualizarDireccion(deltaState);
//		actualizarPosicion(deltaState.getDelta());

//		for (TanqueRule rule : this.getRules()) {
//			if (rule.mustApply(this, this.getScene())) {
//				rule.apply(this, this.getScene());
//				break;
//			}
//		}
		super.update(deltaState);
		
	}

	
	private void actualizarDireccion(DeltaState deltaState) {
		double delta= deltaState.getDelta();

		if (deltaState.isKeyBeingHold(Key.UP)) {
			this.setY(Math.max(this.getY()-getVelocidad()*delta, getyMin()));
			this.setAppearance(Sprite.fromImage("tanqueArriba.png"));
			
		} else if (deltaState.isKeyBeingHold(Key.DOWN)) {
			this.setY(Math.min(getyMax() - this.getAppearance().getHeight(), this.getY()+getVelocidad()*delta));
			this.setAppearance(Sprite.fromImage("tanqueAbajo.png"));
			
		} else if (deltaState.isKeyBeingHold(Key.RIGHT)) {
			this.setX(Math.min(getxMax() - this.getAppearance().getWidth(), this.getX()+getVelocidad()*delta));
			this.setAppearance(Sprite.fromImage("tanqueDerecha.png"));
			
		} else if (deltaState.isKeyBeingHold(Key.LEFT)) {
			this.setX(Math.max(this.getX()-getVelocidad()*delta, getxMin()));
			this.setAppearance(Sprite.fromImage("tanqueIzquierda.png"));
		}
		if (deltaState.isKeyPressed(Key.ENTER)) {
			disparar();
		}
	
	}

	private void disparar() {

		Bala<GameScene> bala = new Bala<GameScene>(this.getX()
				+ this.getAncho() / 2, this.getY() + this.getAncho() / 2, rapidezDisparo);
		this.getScene().addComponent(bala);
		this.getScene().addBala(bala);
	}

//	private void actualizarPosicion(double delta) {
//
//		Vector2D cartesianPosition = getPosition().suma(
//				getPolarVelocity().toCartesians().producto(delta));
//
//		setXVisible(cartesianPosition.getX());
//		setYVisible(cartesianPosition.getY());
//	}

	public double getAncho() {
		return this.getAppearance().getWidth();

	}

//	private void initRules() {
//		for (Bloque bloque : this.getScene().getBloques()) {
//			this.rules.add(new ColisionNaveRule(bloque));
//		}
//	}
	
	public void reset() {
		this.setVelocidad(velocidadInicial);
		this.setX(xInicial);
		this.setY(yInicial);
	}

	// Accesors


	private void setYVisible(double yCartesiano) {
		if (yCartesiano > 0
				&& yCartesiano < this.getGame().getDisplayHeight()
						- this.getAncho()) {
			this.setY(yCartesiano);
		}
	}

	private void setXVisible(double xCartesiano) {
		if (xCartesiano > 0
				&& xCartesiano < this.getGame().getDisplayWidth()
						- this.getAncho()) {
			this.setX(xCartesiano);
		}
	}

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

	public List<TanqueRule> getRules() {
		if (this.rules.isEmpty()) {
			//this.initRules();
		}
		return this.rules;
	}

	public void setRules(List<TanqueRule> rules) {
		this.rules = rules;
	}

	public void removeRule(ColisionNaveRule colisionNaveRule) {
		this.getRules().remove(colisionNaveRule);

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
	
	public double getRapidezDisparo() {
		return rapidezDisparo;
	}

	public void setRapidezDisparo(double rapidezDisparo) {
		this.rapidezDisparo = rapidezDisparo;
	}


	public Sprite getImage() {
		return image;
	}

	public void setImage(Sprite image) {
		Tanque.image = image;
	}
}
