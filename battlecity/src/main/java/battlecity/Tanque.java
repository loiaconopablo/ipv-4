package battlecity;

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
	//private List<NaveRule> rules = new ArrayList<NaveRule>();
	private Vector2D velocidadPolar = new Vector2D(0, 0);
	private double rapidezDisparo = 100;
	//private double angulo = velocidadPolar.getY();
	private static Sprite image = Sprite.fromImage("/tanqueArriba.png");
	private double velocidad;

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public Tanque(double posx, double posy, double xMin, double xMax, double yMin, double yMax) {
		super(image, posx, posy);
		this.xInicial = posx;
		this.yInicial = posy;
		this.setxMin(xMin);
		this.setxMax(xMax);
		this.setyMin(yMin);
		this.setyMax(yMax);
		this.setVelocidad(Double.parseDouble( "100" ));
	}

	private void actualizarSprite(DeltaState deltaState) {
		double delta= deltaState.getDelta();
		double ro = velocidadPolar.getX();
		double theta = velocidadPolar.getY();
		
		if (deltaState.isKeyBeingHold(Key.UP)) {
			this.setY(Math.max(this.getY()-getVelocidad()*delta, getyMin()));
			this.setAppearance(Sprite.fromImage("/tanqueArriba.png"));
			ro+=10;
			
		} else if (deltaState.isKeyBeingHold(Key.DOWN)) {
			this.setY(Math.min(getyMax() - this.getAppearance().getHeight(), this.getY()+getVelocidad()*delta));
			this.setAppearance(Sprite.fromImage("/tanqueAbajo.png"));
			ro-=10;
		} else if (deltaState.isKeyBeingHold(Key.RIGHT)) {
			this.setX(Math.min(getxMax() - this.getAppearance().getWidth(), this.getX()+getVelocidad()*delta));
			this.setAppearance(Sprite.fromImage("/tanqueDerecha.png"));
			
		} else if (deltaState.isKeyBeingHold(Key.LEFT)) {
			this.setX(Math.max(this.getX()-getVelocidad()*delta, getxMin()));
			this.setAppearance(Sprite.fromImage("/tanqueIzquierda.png"));
		}
		if (deltaState.isKeyPressed(Key.ENTER)) {
			disparar();
		}
		this.velocidadPolar=new Vector2D(ro,theta);
		
	}	
	
	

	@Override
	public void update(DeltaState deltaState) {
		actualizarVelocidad(deltaState);
//		actualizarPosicion(deltaState.getDelta());

//		for (NaveRule rule : this.getRules()) {
//			if (rule.mustApply(this, this.getScene())) {
//				rule.apply(this, this.getScene());
//				break;
//			}
//		}
		this.actualizarSprite(deltaState);
		super.update(deltaState);
		
	}

	

	
	private void actualizarVelocidad(DeltaState deltaState) {
		double deltaSpeed = 20;

		double ro = velocidadPolar.getX();
		double theta = velocidadPolar.getY();

		if (deltaState.isKeyBeingHold(Key.UP)) {
			ro += deltaSpeed * deltaState.getDelta();
			//ro += 10;
		} else if (deltaState.isKeyBeingHold(Key.DOWN)) {
			ro = Math.max(0, ro - (deltaSpeed * deltaState.getDelta()));
//			ro -= 10;
		} else if (deltaState.isKeyBeingHold(Key.RIGHT)) {
			theta = theta + (getVelocidadAngular() * deltaState.getDelta());
			theta = ajustarAnguloEntrePiYMenosPi(theta);
//			theta +=10;
		} else if (deltaState.isKeyBeingHold(Key.LEFT)) {
			theta = theta - (getVelocidadAngular() * deltaState.getDelta());
			theta = ajustarAnguloEntrePiYMenosPi(theta);
//			theta -= 10;
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

	protected Vector2D getVelocidadBala() {
		return this.velocidadPolar.suma(new Vector2D(rapidezDisparo, 0))
				.toCartesians();
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

//	private void initRules() {
//		for (Bloque bloque : this.getScene().getBloques()) {
//			this.rules.add(new ColisionNaveRule(bloque));
//		}
//
//	}

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

//	public List<NaveRule> getRules() {
//		if (this.rules.isEmpty()) {
//			this.initRules();
//		}
//		return this.rules;
//	}

//	public void setRules(List<NaveRule> rules) {
//		this.rules = rules;
//	}
//
//	public void removeRule(ColisionNaveRule colisionNaveRule) {
//		this.getRules().remove(colisionNaveRule);
//
//	}

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
		Tanque.image = image;
	}
}
