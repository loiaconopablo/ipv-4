package battlecity;

import java.util.ArrayList;
import java.util.List;

import utils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;

public class TanqueEnemigo extends Tanque {

	private double tiempoDeDisparo = 0;
	private double comparadorDeDisparo = 1;
	
	private Vector2D velocidadPolar = new Vector2D(0, Math.PI / 2);
	private double rapidezDisparo = 300;
	double deltaSpeed = 20;
	double anguloDisparo;
	
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
		Posicion ESI = this.posicionExtremoSuperiorIzquierdo();
		Posicion ESD = this.posicionExtremoSuperiorDerecho();
		Posicion EII = this.posicionExtremoInferiorIzquierdo();
		Posicion EID = this.posicionExtremoInferiorDerecho();
		
//		if(this.sePuedeMover(actual, Direccion.ABAJO) & this.noHayObstaculos(actual, Direccion.ABAJO) )
//			{this.moverAbajo(deltaState);	}
		if(this.sePuedeMover(actual, Direccion.IZQUIERDA) & this.noHayObstaculos(actual, Direccion.IZQUIERDA) )
		{this.moverAIzquierda(deltaState);	}
		this.buscarTanque();
	}
	
	 public boolean noHayObstaculos(Posicion actual, Direccion direccion) {
         return this.getScene().getGrilla().noHayBloque(actual, direccion);
	 }


	private void buscarTanque() {
		Tanque tanque = this.getScene().getTanque();
		Posicion posicionTanque = this.getScene().getGrilla().getPosicion(tanque.getX(), tanque.getY());
		//System.out.println(posicionTanque.getX()+"/"+posicionTanque.getY());
		
		List<List<Posicion>> caminos = this.generarCaminos(tanque);
	}

	private List<List<Posicion>> generarCaminos(Tanque tanque) {
		List<ArrayList<Posicion>> caminos = new ArrayList<ArrayList<Posicion>>();
		Posicion posicionTanque = this.getScene().getGrilla().getPosicion(tanque.getX(), tanque.getY()); 
		//while()
		
		return null;
	}
	
	protected boolean sePuedeMover(Posicion actual, Direccion direccion) {
		if(!this.estaElTanque(actual, direccion)){
			if(direccion.equals(Direccion.IZQUIERDA)){
				return actual.getX()>=0;
			}
			if(direccion.equals(Direccion.DERECHA)){
				return actual.getX()<=15;
			}
			if(direccion.equals(Direccion.ABAJO)){
				return actual.getY()<=11;
			}
			if(direccion.equals(Direccion.ARRIBA)){
				return actual.getY()>=0;
			}
		}
		return false;
	}
	
	public boolean estaElTanque(Posicion actual, Direccion direccion){
		Posicion tanque = this.getScene().getGrilla().getPosicion(this.getScene().getTanque().getX(), this.getScene().getTanque().getY());
		if(direccion.equals(Direccion.IZQUIERDA)){
			if(this.getScene().getGrilla().getPosicionIzquierda(actual) == tanque){
				return true;
			}
		}
		if(direccion.equals(Direccion.DERECHA)){
			if(this.getScene().getGrilla().getPosicionDerecha(actual) == tanque){
				return true;
			}
		}
		if(direccion.equals(Direccion.ABAJO)){
			if(this.getScene().getGrilla().getPosicionAbajo(actual) == tanque){
				return true;
			}
		}
		if(direccion.equals(Direccion.ARRIBA)){
			if(this.getScene().getGrilla().getPosicionArriba(actual) == tanque){
				return true;
			}
		}
		return false;
		
	}

	private void moverAIzquierda(DeltaState deltaState) {
		double delta = deltaState.getDelta();
		double ro = velocidadPolar.getX();

		this.setX(Math.max(this.getX() - getVelocidad() * delta, getxMin()));
		this.setAppearance(Sprite.fromImage("/tanqueIzquierda.png"));
		anguloDisparo = Math.PI;
	}
	
	private void moverADerecha(DeltaState deltaState) {
		double delta = deltaState.getDelta();
		double ro = velocidadPolar.getX();
				
		this.setX(Math.min(getxMax() - this.getAppearance().getWidth(),
				this.getX() + getVelocidad() * delta));
		this.setAppearance(Sprite.fromImage("/tanqueDerecha.png"));
		anguloDisparo = 2 * Math.PI;
		
	}
	
	private void moverAbajo(DeltaState deltaState) {
		double delta = deltaState.getDelta();
		double ro = velocidadPolar.getX();
		
		this.setY(Math.min(getyMax() - this.getAppearance().getHeight(),
				this.getY() + getVelocidad() * delta));
		this.setAppearance(Sprite.fromImage("/tanqueAbajo.png"));
		anguloDisparo = Math.PI / 2;
		ro = Math.max(0, ro - (deltaSpeed * deltaState.getDelta()));		
	}
	
	private void moverArriba(DeltaState deltaState) {
		double delta = deltaState.getDelta();
		double ro = velocidadPolar.getX();

		this.setY(Math.max(this.getY() - getVelocidad() * delta, getyMin()));
		this.setAppearance(Sprite.fromImage("/tanqueArriba.png"));
		anguloDisparo = -Math.PI / 2;
		ro += deltaSpeed * deltaState.getDelta();

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
