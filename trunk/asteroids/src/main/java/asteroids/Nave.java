package asteroids;

import java.awt.Color;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;

public class Nave extends GameComponent<AsteroidsScene>{
		
	public Nave(Color color, int ancho, int alto, double x, double y){
		super(new Rectangle(color, ancho, alto), x, y);
	}
	
	public void derecha(double delta) {
		// TODO Auto-generated method stub
		
	}

	public void izquierda(double delta) {
		// TODO Auto-generated method stub
		
	}

}
