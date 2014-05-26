package battlecity;

import java.util.ArrayList;
import java.util.List;

import scenes.BattleCityScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;

public class Cemento extends Bloque{

	private List<ColisionCementoBalaRule> rules = new ArrayList<ColisionCementoBalaRule>();
	
	public Cemento(int x, int y) {
		super(Sprite.fromImage("/cemento.png"), x, y);
		//super(new Rectangle(Color.RED, 25,25), x, y);
		// TODO Auto-generated constructor stub
	}

	public void update(DeltaState deltaState) {
		for(ColisionCementoBalaRule rule : this.getRules()) {
			if(rule.mustApply(this, this.getScene())) {
				rule.apply(this, this.getScene());
				break;
			}
		}
		
		super.update(deltaState);
		
		
	}
	
	public List<ColisionCementoBalaRule> getRules() {
		this.initRules();
		return rules;
	}

	private void initRules() {
		for (Bala bala : this.getScene().getBalas()) {
			this.rules.add(new ColisionCementoBalaRule(bala));
		}

	}
	
	public void removeRule(ColisionLadrilloBalaRule colisionBalaRule) {
		
		this.getRules().remove(colisionBalaRule);		
	}

}