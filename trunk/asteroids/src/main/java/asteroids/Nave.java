package asteroids;

import java.awt.Color;

import scenes.AsteroidsScene;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;

public class Nave extends GameComponent<AsteroidsScene>{
	
	private double xInicial;
	private double yInicial;
	private double xMin;
	private double xMax;
	private double yMin;
	private double yMax;
	private double velocidad;
	private double acelaracion;
	private NaveStrategy strategy = new NaveStrategy();
		
	public Nave(Color color, int ancho, int alto, double x, double y, double velocidad, double xMin, double xMax, double yMin, double yMax){
		super(new Rectangle(color, ancho, alto), x, y);
		this.setVelocidad(velocidad);
		this.xInicial = x;
		this.yInicial = y;
		this.setxMin(xMin);
		this.setxMax(xMax);
		this.setyMin(yMin);
		this.setyMax(yMax);
	}
	
	public void derecha(double delta) {
		this.setX(Math.max(this.getX()-getVelocidad()*delta, getxMin()));
		
	}

	public void izquierda(double delta) {
		this.setX(Math.min(getxMax() - this.getAppearance().getWidth(), this.getX()+getVelocidad()*delta));
		
	}
	
	//Accesors

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

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public double getAcelaracion() {
		return acelaracion;
	}

	public void setAcelaracion(double acelaracion) {
		this.acelaracion = acelaracion;
	}
}
