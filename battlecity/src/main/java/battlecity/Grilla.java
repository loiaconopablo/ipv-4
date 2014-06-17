package battlecity;

import com.uqbar.vainilla.GameComponent;

public class Grilla {
	
	private Posicion mapa [][] = new Posicion[12][16];

	public Posicion[][] getMapa() {
		return mapa;
	}

	public void setMapa(Posicion[][] mapa) {
		this.mapa = mapa;
	}
	
	public void setPosicion(int x, int y, Posicion posicion){
		this.mapa[y][x] = posicion;
	}

	public Posicion getPosicionArriba(Posicion posicion){
		if(posicion.getY() != 0){
			return this.getMapa()[posicion.getY() -1][posicion.getX()];
			}
		return null;
	}
	
	public Posicion getPosicionAbajo(Posicion posicion){
		if(posicion.getY() != 11){
			return this.getMapa()[posicion.getY() + 1][posicion.getX()];
			}
		return null;
	}
	
	public Posicion getPosicionIzquierda(Posicion posicion){
		if(posicion.getX() != 0){
			return this.getMapa()[posicion.getY() ][posicion.getX() - 1];
			}
		return null;
	}
	
	public Posicion getPosicionDerecha(Posicion posicion){
		if(posicion.getX() != 15){
			return this.getMapa()[posicion.getY()][posicion.getX() + 1];
			}
		return null;
	}
}
