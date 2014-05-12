package asteroids;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import scenes.AsteroidsScene;
import utils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;

public abstract class Bloque extends GameComponent<AsteroidsScene> {

	private Vector2D velocidad;
	private List<BalaRule> rules = new ArrayList<BalaRule>();

	public Bloque(Rectangle rectangle, int x, int y) {
		super(rectangle, x, y);
		double randomx = (double)(Math.random()*(150-(-50)+1)-50);
		double randomy = (double)(Math.random()*(150-(-50)+1)-50);
		this.velocidad = new Vector2D(randomx,randomy);
	}

	@Override
	public void update(DeltaState deltaState) {
		Vector2D posicionPropuesta = this.getPosicion().suma(
				getVelocidad().producto(deltaState.getDelta()));

		Vector2D newDelta = getVelocidad().producto(deltaState.getDelta());
		this.setX(this.getX() + newDelta.getX());
		this.setY(this.getY() + newDelta.getY());

		actualizarPosicion(posicionPropuesta);
		
		for(BalaRule rule : this.getRules()) {
			if(rule.mustApply(this, this.getScene())) {
				rule.apply(this, this.getScene());
				break;
			}
		}

	}

	public Vector2D getPosicion() {
		return new Vector2D(this.getX(), this.getY());
	}

	private void actualizarPosicion(Vector2D posicionPropuesta) {

		double x = posicionPropuesta.getX();
		double y = posicionPropuesta.getY();

		if (x + this.getAppearance().getWidth() <= 0) {
			x = this.getGame().getDisplayWidth();
		} else if (x >= this.getGame().getDisplayWidth()) {
			x = -this.getAppearance().getWidth();
		}

		if (y + this.getAppearance().getWidth() <= 0) {
			y = this.getGame().getDisplayHeight();
		} else if (y >= this.getGame().getDisplayHeight()) {
			y = -this.getAppearance().getHeight();
		}
		this.setX(x);
		this.setY(y);

	}

	public List<BalaRule> getRules() {
		this.initRules();
		return rules;
	}

	private void initRules() {
		for (Bala bala : this.getScene().getBalas()) {
			this.rules.add(new ColisionBalaRule(bala));
		}

	}

	public Vector2D getVelocidad() {
		return velocidad;
	}

	public void removeRule(ColisionBalaRule colisionBalaRule) {
		
			this.getRules().remove(colisionBalaRule);		
	}

	}


