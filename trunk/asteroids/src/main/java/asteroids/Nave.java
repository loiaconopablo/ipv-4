package asteroids;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import scenes.AsteroidsScene;
import utils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;

public class Nave extends GameComponent<AsteroidsScene> {

	private double xInicial;
	private double yInicial;
	private double xMin;
	private double xMax;
	private double yMin;
	private double yMax;
	private List<NaveRule> rules = new ArrayList<NaveRule>(); 

	private Vector2D velocidadPolar = new Vector2D(0, 0);
	private double rapidezDisparo = 100;

	public Nave(Color color, int ancho, int alto, double x, double y,
			double xMin, double xMax, double yMin, double yMax) {
		super(new Rectangle(color, ancho, alto), x, y);
		this.xInicial = x;
		this.yInicial = y;
		this.setxMin(xMin);
		this.setxMax(xMax);
		this.setyMin(yMin);
		this.setyMax(yMax);
	}

	@Override
	public void update(DeltaState deltaState) {
		actualizarVelocidad(deltaState);
		actualizarPosicion(deltaState.getDelta());
		
		for(NaveRule rule : this.getRules()) {
			if(rule.mustApply(this, this.getScene())) {
				rule.apply(this, this.getScene());
				break;
			}
		}
	}
	
	

	private void actualizarVelocidad(DeltaState deltaState) {
		double deltaAngle = Math.PI; // media vuelta por segundo
		double deltaSpeed = 5;

		double ro = velocidadPolar.getX();
		double theta = velocidadPolar.getY();

		if (deltaState.isKeyBeingHold(Key.UP)) {
			ro += deltaSpeed * deltaState.getDelta();
		} else if (deltaState.isKeyBeingHold(Key.DOWN)) {
			ro = Math.max(0, ro - (deltaSpeed * deltaState.getDelta()));
		} else if (deltaState.isKeyBeingHold(Key.RIGHT)) {
			theta += deltaAngle * deltaState.getDelta();
			// Si me pase del PI le resto una vuelta
			theta = theta > Math.PI ? theta - 2 * Math.PI : theta;
		} else if (deltaState.isKeyBeingHold(Key.LEFT)) {
			theta = theta - (deltaAngle * deltaState.getDelta());
			// Si me pase del -PI le sumo una vuelta
			theta = theta < -Math.PI ? theta + 2 * Math.PI : theta;
		}
		if (deltaState.isKeyPressed(Key.ENTER)) {
			disparar();
		}
		velocidadPolar = new Vector2D(ro, theta);
	}

	private void disparar() {
		Bala bala = new Bala<GameScene>(this.getX() + this.getAncho() / 2, this
				.getY() + this.getAncho() / 2,

		this.velocidadPolar.suma(new Vector2D(rapidezDisparo, 0))
				.toCartesians());
		this.getScene().addComponent(bala);
		this.getScene().addBala(bala);
	}

	private void actualizarPosicion(double delta) {

		Vector2D cartesianPosition = getPosition().suma(
				getPolarVelocity().toCartesians().producto(delta));

		setXVisible(cartesianPosition.getX());
		setYVisible(cartesianPosition.getY());
	}

	public double getAncho() {
		return this.getAppearance().getWidth();

	}

	// Accesors

	public Vector2D getPolarVelocity() {
		return velocidadPolar;
	}

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

	private Vector2D getPosition() {
		return new Vector2D(this.getX(), this.getY());
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

	public List<NaveRule> getRules() {
		if(this.rules.isEmpty()) {
			this.initRules();
		}
		return this.rules;
	}


	public void setRules(List<NaveRule> rules) {
		this.rules = rules;
	}

	private void initRules() {
		for (Bloque bloque : this.getScene().getBloques() ) {
			this.rules.add(new ColisionNaveRule(bloque));
		}
		
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
}
