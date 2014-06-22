package pacman;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import scenes.PacmanScene;
import utils.Vector2D;
import colisiones.ColisionBalaBloqueRule;
import colisiones.ColisionBalaTanqueRule;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;

public class Comida extends GameComponent<PacmanScene> {
	private Vector2D velocity;
	private List<ColisionBalaBloqueRule> rules = new ArrayList<ColisionBalaBloqueRule>();
	private Personaje propietario;
	
	
	public Comida(Personaje dueño, double x, double y, Vector2D velocity) {
		super(new Circle(Color.RED, 7), x, y);
		this.setZ(-1);
		this.setVelocity(velocity);
		this.setPropietario(dueño);
	}

	@Override
	public void update(DeltaState deltaState) {
				
		for(ColisionBalaBloqueRule rule : this.getRules()) {
			if(rule.mustApply(this, this.getScene())) {
				rule.apply(this, this.getScene());
				break;
			}
		}
	}
	public boolean seFueDelJuego() {
		return(	this.getGame().getDisplayHeight() <= this.getY()+this.getAppearance().getHeight() ||
		this.getY() <= 0 ||
		this.getGame().getDisplayWidth()-150 <= this.getX()+this.getAppearance().getWidth() ||
		this.getX() <= 0); 
		}

	public List<ColisionBalaBloqueRule> getRules() {
		if(this.rules.isEmpty()) {
			this.initRules();
		}
		return rules;
	}

	public Vector2D getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}

	private void initRules() {
		
		for (Bloque bloque : this.getScene().getBloques() ) {
			this.rules.add(bloque.crearSuColisionConBala());
		}
		
		for (Personaje tanque : this.getScene().getFantasmas()) {
			this.rules.add(new ColisionBalaTanqueRule(tanque));
		}
		
	}

	public void setRules(List<ColisionBalaBloqueRule> rules) {
		this.rules = rules;
	}

	public Personaje getPropietario() {
		return propietario;
	}

	public void setPropietario(Personaje propietario) {
		this.propietario = propietario;
	}

	public void removeRule(ColisionBalaBloqueRule colisionBalaBloqueRule) {
		this.getRules().remove(colisionBalaBloqueRule);		
	}


}
