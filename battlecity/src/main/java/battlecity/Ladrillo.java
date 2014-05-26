package battlecity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import scenes.BattleCityScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

public class Ladrillo extends Bloque{

	private List<ColisionLadrilloBalaRule> rules = new ArrayList<ColisionLadrilloBalaRule>();
	
	public Ladrillo(int x, int y) {
		super(Sprite.fromImage("/ladrillo.png"), x, y);
	}

	
	public void update(DeltaState deltaState) {
		for(ColisionLadrilloBalaRule rule : this.getRules()) {
			if(rule.mustApply(this, this.getScene())) {
				rule.apply(this, this.getScene());
				break;
			}
		}
		
		super.update(deltaState);
		
		
	}
	
	public List<ColisionLadrilloBalaRule> getRules() {
		this.initRules();
		return rules;
	}

	private void initRules() {
		for (Bala bala : this.getScene().getBalas()) {
			this.rules.add(new ColisionLadrilloBalaRule(bala));
		}

	}
	
	public void removeRule(ColisionLadrilloBalaRule colisionBalaRule) {
		
		this.getRules().remove(colisionBalaRule);		
	}


}
