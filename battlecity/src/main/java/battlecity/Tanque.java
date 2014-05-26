package battlecity;

import java.util.ArrayList;
import java.util.List;

import scenes.BattleCityScene;
import utils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
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
	private Vector2D velocidadPolar = new Vector2D(0, -Math.PI / 2);
	private double rapidezDisparo = 300;
	private static Sprite image = Sprite.fromImage("/tanqueArriba.png");
	private double velocidad;
	private boolean tieneBala;
	
	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public Tanque(double posx, double posy, double xMin, double xMax,
			double yMin, double yMax) {
		super(image, posx, posy);
		this.xInicial = posx;
		this.yInicial = posy;
		this.setxMin(xMin);
		this.setxMax(xMax);
		this.setyMin(yMin);
		this.setyMax(yMax);
		this.setVelocidad(Double.parseDouble("100"));
		this.setTieneBala(true);
	}

	private void actualizarSpriteYDireccion(DeltaState deltaState) {
		double delta = deltaState.getDelta();
		double deltaSpeed = 20;

		double ro = velocidadPolar.getX();
		double anguloDisparo = velocidadPolar.getY();

		if (deltaState.isKeyBeingHold(Key.UP)) {
			this.setY(Math.max(this.getY() - getVelocidad() * delta, getyMin()));
			this.setAppearance(Sprite.fromImage("/tanqueArriba.png"));
			anguloDisparo = -Math.PI / 2;
			ro += deltaSpeed * deltaState.getDelta();

		} else if (deltaState.isKeyBeingHold(Key.DOWN)) {
			this.setY(Math.min(getyMax() - this.getAppearance().getHeight(),
					this.getY() + getVelocidad() * delta));
			this.setAppearance(Sprite.fromImage("/tanqueAbajo.png"));
			anguloDisparo = Math.PI / 2;
			ro = Math.max(0, ro - (deltaSpeed * deltaState.getDelta()));

		} else if (deltaState.isKeyBeingHold(Key.RIGHT)) {
			this.setX(Math.min(getxMax() - this.getAppearance().getWidth(),
					this.getX() + getVelocidad() * delta));
			this.setAppearance(Sprite.fromImage("/tanqueDerecha.png"));
			anguloDisparo = 2 * Math.PI;

		} else if (deltaState.isKeyBeingHold(Key.LEFT)) {
			this.setX(Math.max(this.getX() - getVelocidad() * delta, getxMin()));
			this.setAppearance(Sprite.fromImage("/tanqueIzquierda.png"));
			anguloDisparo = Math.PI;
		}
		if (deltaState.isKeyPressed(Key.ENTER)) {
			disparar();
		}
		this.velocidadPolar = new Vector2D(ro, anguloDisparo);

	}

	@Override
	public void update(DeltaState deltaState) {
		 for (TanqueRule rule : this.getRules()) {
		 if (rule.mustApply(this, this.getScene())) {
		 rule.apply(this, this.getScene());
		 break;
		 }
		 }
		this.actualizarSpriteYDireccion(deltaState);
		super.update(deltaState);

	}

	private void disparar() {
		if (this.isTieneBala()) {
			Bala bala = new Bala(this,this.getX() + this.getAncho() / 2,
					this.getY() + this.getAncho() / 2, this.velocidadPolar.suma(
							new Vector2D(rapidezDisparo, 0)).toCartesians());
			this.getScene().addComponent(bala);
			this.getScene().getBalas().add(bala);
			this.setTieneBala(false);
		}
	}


	// Accesors

	protected Vector2D getVelocidadBala() {
		return this.velocidadPolar.suma(new Vector2D(rapidezDisparo, 0))
				.toCartesians();
	}

	public double getAncho() {
		return this.getAppearance().getWidth();

	}
	
	private void initRules() {
		for (Bloque bloque : this.getScene().getBloques()) {
			this.rules.add( new ColisionBloqueRule(bloque));
		}

	}
	
	public List<TanqueRule> getRules() {
		if (this.rules.isEmpty()) {
			this.initRules();
		}
		return this.rules;
	}

	public Vector2D getPolarVelocity() {
		return velocidadPolar;
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

	public boolean isTieneBala() {
		return tieneBala;
	}

	public void setTieneBala(boolean tieneBala) {
		this.tieneBala = tieneBala;
	}
}
