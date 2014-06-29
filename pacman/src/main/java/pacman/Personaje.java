package pacman;

import java.util.ArrayList;
import java.util.List;
import scenes.PacmanScene;
import colisiones.ColisionPersonajeComidaRule;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
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
	private List<ColisionPersonajeComidaRule> rules = new ArrayList<ColisionPersonajeComidaRule>();
	private static Sprite image = Sprite
			.fromImage("personajeAbiertoIzquierda.png");
	private double velocidad;
	private double velocidadInicial = 0;
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
		this.setZ(2);

	}

	private void actualizarSpriteYDireccion(DeltaState deltaState) {

		double delta = deltaState.getDelta();
		Posicion actual = this.posicionActual();
		// if(this.noHayObstaculos(actual)){
			if (deltaState.isKeyPressed(Key.UP) || this.direccion == "UP" && this.noHayObstaculos(this,actual, Direccion.ARRIBA)) {
			this.direccion = "UP";
			this.setY(this.analizarPosicionY(this.getY() - getVelocidad() * delta));
			this.intercambiarApariencia("personajeCerradoArriba.png",
					"personajeAbiertoArriba.png", deltaState);
		}
		if (deltaState.isKeyPressed(Key.DOWN) || this.direccion == "DOWN"&& this.noHayObstaculos(this,actual, Direccion.ABAJO)) {
			this.direccion = "DOWN";
			this.setY(this.analizarPosicionY(this.getY() + getVelocidad() * delta));
			this.intercambiarApariencia("personajeCerradoAbajo.png",
					"personajeAbiertoAbajo.png", deltaState);
		}
		if (deltaState.isKeyPressed(Key.RIGHT) || this.direccion == "RIGHT" && this.noHayObstaculos(this,actual, Direccion.DERECHA)) {
			this.direccion = "RIGHT";
			this.setX(this.analizarPosicionX(this.getX() + getVelocidad()
					* delta));
			this.intercambiarApariencia("personajeCerradoDerecha.png",
					"personajeAbiertoDerecha.png", deltaState);
		}
		if (deltaState.isKeyPressed(Key.LEFT) || this.direccion == "LEFT" && this.noHayObstaculos(this,actual, Direccion.IZQUIERDA)) {
			this.direccion = "LEFT";
			this.setX(this.analizarPosicionX(this.getX() - getVelocidad()
					* delta));
			this.intercambiarApariencia("personajeCerradoIzquierda.png",
					"personajeAbiertoIzquierda.png", deltaState);
		}

	}
	private boolean noHayObstaculos(Personaje personaje,
			pacman.Posicion actual, pacman.Direccion arriba) {
		return this.getScene().getGrilla().noHayBloque(personaje,actual, direccion);
	}

	private double analizarPosicionX(double x) {
		if (x + this.getAppearance().getWidth() <= 0) {
			x = ((Pacman) this.getGame()).getDimensionCuadro().getWidth()
					- this.getAppearance().getWidth();
		} else if (x + this.getAppearance().getWidth() >= ((Pacman) this.getGame()).getDimensionCuadro().getWidth()) {
			x = -this.getAppearance().getWidth();
		}
		return x;
	}

	private double analizarPosicionY(double y) {
		if (y + this.getAppearance().getHeight() <= 0) {
			y = ((Pacman) this.getGame()).getDimensionCuadro().getHeight()
					- this.getAppearance().getHeight();
		} else if (y+ this.getAppearance().getHeight() >= this.getGame().getDisplayHeight()) {
			y = -this.getAppearance().getHeight();
		}
		return y;

	}

	private void intercambiarApariencia(String string, String string2,
			DeltaState deltaState) {
		this.tiempo += deltaState.getDelta();
		if (this.tiempo >= this.comparador) {
			this.cambiarApariencia(string, string2);
			this.comparador += 0.1;
		}

	}

	private void initSound() {
		this.setMusic(new SoundBuilder().buildSound(this.getClass()
				.getClassLoader().getResourceAsStream("faster.wav")));
	}

	private void cambiarApariencia(String string, String string2) {
		if (this.bocaAbierta) {
			this.setAppearance(Sprite.fromImage(string));
			this.bocaAbierta = false;
		} else {
			this.setAppearance(Sprite.fromImage(string2));
			this.bocaAbierta = true;
		}
	}

	public boolean noHayObstaculos(Posicion actual) {
		return this.getScene().getGrilla().noHayBloque(actual);
	}

	public Posicion posicionActual() {
		return this.getScene().getGrilla().getPosicion(this.getX(), this.getY());
	}

	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);

		for (ColisionPersonajeComidaRule rule : this.getRules()) {
			if (rule.mustApply(this, this.getScene())) {
				rule.apply(this, this.getScene());
				break;
			}
		}
		this.actualizarSpriteYDireccion(deltaState);

		// this.contadorFantasmas += deltaState.getDelta();
		// if(this.contadorFantasmas >= 5.0){
		// this.contadorFantasmas = 0.0;
		// this.getScene().agregarFantasmas(new Fantasma(700,100,0,
		// this.getScene().getGame().getDisplayWidth(),0,
		// this.getScene().getGame().getDisplayHeight()));
		// }

	}

	public void resetCentrar() {
		this.setVelocidad(velocidadInicial);
		this.setX(xInicial);
		this.setY(yInicial);
	}

	// Accesors

	public double getAncho() {
		return this.getAppearance().getWidth();

	}

	private void initRules() {
		for (Comida comida : this.getScene().getComidas()) {
			this.rules.add(new ColisionPersonajeComidaRule(comida));
		}

	}

	public void removeRule(ColisionPersonajeComidaRule colisionPersonaComida) {
		this.getRules().remove(colisionPersonaComida);

	}

	public List<ColisionPersonajeComidaRule> getRules() {
		if (this.rules.isEmpty()) {
			this.initRules();
		}
		return this.rules;
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

	public Sound getMusic() {
		return music;
	}

	public void setMusic(Sound music) {
		this.music = music;
	}

}
