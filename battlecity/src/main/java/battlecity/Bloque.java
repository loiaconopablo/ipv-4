package battlecity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import scenes.BattleCityScene;
import utils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

public abstract class Bloque extends GameComponent<BattleCityScene> {

	private List<BalaRule> rules = new ArrayList<BalaRule>();

	public Bloque(Sprite sprite, int x, int y) {
		super(sprite, x, y);
		
		
	}
	
	public Bloque(Rectangle rectangle, int x, int y) {
		super(rectangle, x - 800, y - 600);
		double randomx = (double)(Math.random()*(150-(-50)+1)-50);
		double randomy = (double)(Math.random()*(150-(-50)+1)-50);
	}

	@Override
	public void update(DeltaState deltaState) {		
		for(BalaRule rule : this.getRules()) {
			if(rule.mustApply(this, this.getScene())) {
				rule.apply(this, this.getScene());
				break;
			}
		}

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

	public void removeRule(ColisionBalaRule colisionBalaRule) {
		
			this.getRules().remove(colisionBalaRule);		
	}

	public abstract void desintegrarse(BattleCityScene scene);

	}


