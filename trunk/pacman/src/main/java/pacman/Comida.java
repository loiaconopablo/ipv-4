package pacman;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import scenes.PacmanScene;
import utils.Vector2D;
import colisiones.ColisionComidaBloqueRule;
import colisiones.ColisionBalaTanqueRule;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;

public class Comida extends GameComponent<PacmanScene> {
	private List<ColisionComidaBloqueRule> rules = new ArrayList<ColisionComidaBloqueRule>();
	
	public Comida(double x, double y) {
		super(new Circle(Color.RED, 7), x, y);
		this.setZ(1);
	}

	@Override
	public void update(DeltaState deltaState) {
				
//		for(ColisionBalaBloqueRule rule : this.getRules()) {
//			if(rule.mustApply(this, this.getScene())) {
//				rule.apply(this, this.getScene());
//				break;
//			}
//		}
	}
	public boolean seFueDelJuego() {
		return(	this.getGame().getDisplayHeight() <= this.getY()+this.getAppearance().getHeight() ||
		this.getY() <= 0 ||
		this.getGame().getDisplayWidth()-150 <= this.getX()+this.getAppearance().getWidth() ||
		this.getX() <= 0); 
		}

	public List<ColisionComidaBloqueRule> getRules() {
		if(this.rules.isEmpty()) {
			this.initRules();
		}
		return rules;
	}


	private void initRules() {
	
		for (Personaje tanque : this.getScene().getFantasmas()) {
			this.rules.add(new ColisionBalaTanqueRule(tanque));
		}
		
	}

	public void setRules(List<ColisionComidaBloqueRule> rules) {
		this.rules = rules;
	}

	public void removeRule(ColisionComidaBloqueRule colisionBalaBloqueRule) {
		this.getRules().remove(colisionBalaBloqueRule);		
	}


}
