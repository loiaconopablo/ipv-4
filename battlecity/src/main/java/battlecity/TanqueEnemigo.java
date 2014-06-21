package battlecity;

import java.awt.Color;

import utils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;

public class TanqueEnemigo extends Tanque {

	private double tiempoDeDisparo = 0;
	private double comparadorDeDisparo = 1;
	
	private Vector2D velocidadPolar = new Vector2D(0, Math.PI / 2);
	private double rapidezDisparo = 300;
	
	public TanqueEnemigo(double posx, double posy, double xMin, double xMax,
			double yMin, double yMax) {
		super(posx, posy, xMin, xMax, yMin, yMax);
		this.setAppearance(Sprite.fromImage("/tanqueEnemigoAbajo.png"));
	}

	@Override
	public void update(DeltaState deltaState) {
		this.tiempoDeDisparo += deltaState.getDelta();
		if( this.tiempoDeDisparo >= this.comparadorDeDisparo){
			this.disparar();
			this.comparadorDeDisparo += 1.0;
		}
		Posicion actual = this.posicionActual();
		if(this.sePuedeMover(actual, Direccion.IZQUIERDA)){
			if(this.noHayObstaculos(actual)){
				this.moverAIzquierda(deltaState);			
			}
		}
	}

	

	private void moverAIzquierda(DeltaState deltaState) {
		double delta = deltaState.getDelta();
		double deltaSpeed = 20;
		double ro = velocidadPolar.getX();
		double anguloDisparo = velocidadPolar.getY();
		
		
		this.setX(Math.max(this.getX() - getVelocidad() * delta, getxMin()));
		this.setAppearance(Sprite.fromImage("/tanqueIzquierda.png"));
		anguloDisparo = Math.PI;
	}
	
	private void moverADerecha(DeltaState deltaState) {
		double delta = deltaState.getDelta();
		double deltaSpeed = 20;
		double ro = velocidadPolar.getX();
		double anguloDisparo = velocidadPolar.getY();
		
		this.setX(Math.min(getxMax() - this.getAppearance().getWidth(),
				this.getX() + getVelocidad() * delta));
		this.setAppearance(Sprite.fromImage("/tanqueDerecha.png"));
		anguloDisparo = 2 * Math.PI;

		
	}
	
	private void moverAbajo(DeltaState deltaState) {
		double delta = deltaState.getDelta();
		double deltaSpeed = 20;
		double ro = velocidadPolar.getX();
		double anguloDisparo = velocidadPolar.getY();
		
		this.setY(Math.min(getyMax() - this.getAppearance().getHeight(),
				this.getY() + getVelocidad() * delta));
		this.setAppearance(Sprite.fromImage("/tanqueAbajo.png"));
		anguloDisparo = Math.PI / 2;
		ro = Math.max(0, ro - (deltaSpeed * deltaState.getDelta()));		
	}
	
	private void moverArriba(DeltaState deltaState) {
		double delta = deltaState.getDelta();
		double deltaSpeed = 20;
		double ro = velocidadPolar.getX();
		double anguloDisparo = velocidadPolar.getY();
		
		
		this.setY(Math.max(this.getY() - getVelocidad() * delta, getyMin()));
		this.setAppearance(Sprite.fromImage("/tanqueArriba.png"));
		anguloDisparo = -Math.PI / 2;
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

	public void disparar() {
		if (this.isTieneBala()) {
			Bala bala = new BalaEnemiga(this,this.getX() + this.getAncho() / 2,
					this.getY() + this.getAncho() / 2, this.velocidadPolar.suma(
							new Vector2D(rapidezDisparo, 0)).toCartesians());
			this.getScene().addComponent(bala);
			this.getScene().getBalas().add(bala);
			this.setTieneBala(false);
		}
	}
	public double getTiempoDeDisparo() {
		return tiempoDeDisparo;
	}

	public void setTiempoDeDisparo(double tiempoDeDisparo) {
		this.tiempoDeDisparo = tiempoDeDisparo;
	}
	
	

}
