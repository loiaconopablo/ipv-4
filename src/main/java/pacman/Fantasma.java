package pacman;

import utils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;

public abstract class Fantasma extends Personaje {

	private Vector2D velocidadPolar = new Vector2D(0, Math.PI / 2);
	private Sprite fantasmaEnemigoA;
	private Sprite fantasmaEnemigoB;
	private double tiempo = 1;
	private double comparador = 1;
	private boolean bocaAbierta = true;
	double deltaSpeed = 20;
	
	public Fantasma(double posx, double posy, double xMin, double xMax,
			double yMin, double yMax) {
		super(posx, posy, xMin, xMax, yMin, yMax);
	}

	@Override
	public void update(DeltaState deltaState) {
				Posicion actual = this.posicionActual();
				
		if(this.sePuedeMover(actual, Direccion.ABAJO) & this.noHayObstaculos(this,actual, Direccion.ABAJO) )
				{this.moverAbajo(deltaState);	}
		this.intercambiarApariencia(this.getFantasmaEnemigoA(),
				this.getFantasmaEnemigoB(), deltaState);	
		
	}

	private void intercambiarApariencia(Sprite fantasmaEnemigoA2,
			Sprite fantasmaEnemigoB2, DeltaState deltaState) {
		this.tiempo += deltaState.getDelta();
		if (this.tiempo >= this.comparador) {
			this.cambiarApariencia(fantasmaEnemigoA2, fantasmaEnemigoB2);
			this.comparador += 0.1;
		}
		
	}
	private void cambiarApariencia(Sprite fantasmaEnemigoA2, Sprite fantasmaEnemigoB2) {
		if (this.bocaAbierta) {
			this.setAppearance(fantasmaEnemigoA2);
			this.bocaAbierta = false;
		} else {
			this.setAppearance(fantasmaEnemigoB2);
			this.bocaAbierta = true;
		}
	}

	public Sprite getFantasmaEnemigoB() {
		return fantasmaEnemigoB;
	}

	public void setFantasmaEnemigoB(Sprite fantasmaEnemigoB) {
		this.fantasmaEnemigoB = fantasmaEnemigoB;
	}

	protected void moverAIzquierda(DeltaState deltaState) {
		double delta = deltaState.getDelta();
		double ro = velocidadPolar.getX();

		this.setX(Math.max(this.getX() - getVelocidad() * delta, getxMin()));
//		this.setAppearance(Sprite.fromImage("tanqueEnemigoIzquierda.png"));
	}
	
	protected void moverADerecha(DeltaState deltaState) {
		double delta = deltaState.getDelta();
		double ro = velocidadPolar.getX();
				
		this.setX(Math.min(getxMax() - this.getAppearance().getWidth(),
				this.getX() + getVelocidad() * delta));
//		this.setAppearance(Sprite.fromImage("tanqueEnemigoDerecha.png"));
		
	}
	
	protected void moverAbajo(DeltaState deltaState) {
		double delta = deltaState.getDelta();
		double ro = velocidadPolar.getX();
		
		this.setY(Math.min(getyMax() - this.getAppearance().getHeight(),
				this.getY() + getVelocidad() * delta));
//		this.setAppearance(Sprite.fromImage("tanqueEnemigoAbajo.png"));
		ro = Math.max(0, ro - (deltaSpeed * deltaState.getDelta()));		
	}
	
	protected void moverArriba(DeltaState deltaState) {
		double delta = deltaState.getDelta();
		double ro = velocidadPolar.getX();

		this.setY(Math.max(this.getY() - getVelocidad() * delta, getyMin()));
//		this.setAppearance(Sprite.fromImage("tanqueEnemigoArriba.png"));
		ro += deltaSpeed * deltaState.getDelta();

	}

	private boolean sePuedeMover(Posicion actual, Direccion direccion) {
		if(direccion.equals(Direccion.IZQUIERDA)){
			return actual.getX()>=0;
		}
		if(direccion.equals(Direccion.DERECHA)){
			return actual.getX()<=15;
			}
		if(direccion.equals(Direccion.ABAJO)){
			return actual.getX()<=11;
			}
		return actual.getY()>=0;
	}
	
	public Sprite getFantasmaEnemigoA() {
		return fantasmaEnemigoA;
	}

	public void setFantasmaEnemigoA(Sprite fantasmaEnemigoA) {
		this.fantasmaEnemigoA = fantasmaEnemigoA;
	}
	
	
	

}
