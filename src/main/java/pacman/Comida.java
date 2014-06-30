package pacman;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import scenes.PacmanScene;
import colisiones.ColisionComidaBloqueRule;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;

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
}
