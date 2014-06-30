package battlecity;

import com.uqbar.vainilla.GameComponent;

public class Posicion {
	
	private int x;
	private int y;
	private GameComponent elemento;
	
	
	public Posicion(int x, int y, GameComponent elemento){
		this.setX(x);
		this.setY(y);
		this.setElemento(elemento);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public GameComponent getElemento() {
		return elemento;
	}
	public void setElemento(GameComponent elemento) {
		this.elemento = elemento;
	}
	
	public boolean puedePasar(){
		if(this.getElemento() == null || this.getElemento().getClass() == Pasto.class)
			return true;
		else {return false;}
	}
	

	

}
