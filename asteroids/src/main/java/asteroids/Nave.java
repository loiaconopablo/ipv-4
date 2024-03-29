package asteroids;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import scenes.AsteroidsScene;
import utils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;
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
	
	//private double angulo = velocidadPolar.getY();
	private static Sprite image = Sprite.fromImage("/nave_1.png");
	

	public Nave(Color color, int ancho, int alto, double x, double y,
			double xMin, double xMax, double yMin, double yMax) {
		super(image, x, y);
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

		for (NaveRule rule : this.getRules()) {
			if (rule.mustApply(this, this.getScene())) {
				rule.apply(this, this.getScene());
				break;
			}
		}
		this.actualizarSprite(deltaState);
		super.update(deltaState);
		
	}

	private void actualizarSprite(DeltaState deltaState) {
		
		//CUALQUIERA DE LOS METODOS FUNCIONA;, uno esta calculado en grados y otro en Radian
		
		double valor = 360/72;
		double angu = Math.toDegrees(this.getVelocidadPolar().getY());
//		double valor = (2*(Math.PI))/72;
//		double angu = this.getVelocidadPolar().getY();

		for ( double i = 0; i<73; i++){
			if(angu>=(i*valor) && angu<((i+1)*valor)){
				this.setAppearance(Sprite.fromImage("/nave_"+(int)(i+1)+".png"));
				//System.out.println("nave_"+(i+1)+".png");
			}
		}
	}

	
	private void actualizarVelocidad(DeltaState deltaState) {
		double deltaSpeed = 20;

		double ro = velocidadPolar.getX();
		double theta = velocidadPolar.getY();

		if (deltaState.isKeyBeingHold(Key.UP)) {
			ro += deltaSpeed * deltaState.getDelta();
		} else if (deltaState.isKeyBeingHold(Key.DOWN)) {
			ro = Math.max(0, ro - (deltaSpeed * deltaState.getDelta()));
		} else if (deltaState.isKeyBeingHold(Key.RIGHT)) {
			theta = theta + (getVelocidadAngular() * deltaState.getDelta());
			theta = ajustarAnguloEntrePiYMenosPi(theta);
		} else if (deltaState.isKeyBeingHold(Key.LEFT)) {
			theta = theta - (getVelocidadAngular() * deltaState.getDelta());
			theta = ajustarAnguloEntrePiYMenosPi(theta);
		}
		if (deltaState.isKeyPressed(Key.ENTER)) {
			disparar();
		}
		velocidadPolar = new Vector2D(ro, (theta));
	}
	
	protected double getVelocidadAngular() {
		return 2*Math.PI; // media vuelta por segundo
	}

	protected double ajustarAnguloEntrePiYMenosPi(double theta) {
		theta = theta > getVelocidadAngular() ? theta -  getVelocidadAngular() : theta;
		theta = theta < 0 ? theta + getVelocidadAngular() : theta;
		return theta;
	}
	
	private void disparar() {

		Bala<GameScene> bala = new Bala<GameScene>(this.getX()
				+ this.getAncho() / 2, this.getY() + this.getAncho() / 2,
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

	private void initRules() {
		for (Bloque bloque : this.getScene().getBloques()) {
			this.rules.add(new ColisionNaveRule(bloque));
		}

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
		if (this.rules.isEmpty()) {
			this.initRules();
		}
		return this.rules;
	}

	public void setRules(List<NaveRule> rules) {
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
	
	public Vector2D getVelocidadPolar() {
		return velocidadPolar;
	}

	public void setVelocidadPolar(Vector2D velocidadPolar) {
		this.velocidadPolar = velocidadPolar;
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
		Nave.image = image;
	}
}
