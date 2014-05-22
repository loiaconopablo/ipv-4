package battlecity;

import java.awt.Color;

import utils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;

public class Bala<T extends GameScene> extends GameComponent<GameScene> {

	private double velocity;

	public Bala(double x, double y, double velocity) {
		super(new Circle(Color.RED, 7), x, y);
		this.velocity = velocity;
	}

	@Override
	public void update(DeltaState deltaState) {
		double delta= deltaState.getDelta()*velocity;
		this.setX(this.getX() + delta);
		//this.setY(this.getY() + delta);
//		if(this.seFueDelJuego()){
//			this.getScene().removeComponent(this);
			//faltaria remover la regla de los bloques con esta bala que se fue
//		}
	}

	private boolean seFueDelJuego() {
		return(this.getX()+this.getAppearance().getWidth() > this.getGame().getDisplayWidth() || 
				this.getX()+this.getAppearance().getWidth() < this.getGame().getDisplayWidth() || 
				this.getY()+this.getAppearance().getHeight() < this.getGame().getDisplayHeight()||
				this.getY()+this.getAppearance().getHeight() > this.getGame().getDisplayHeight());
	}

}
