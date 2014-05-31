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
