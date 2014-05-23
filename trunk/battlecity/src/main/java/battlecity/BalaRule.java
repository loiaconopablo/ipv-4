package battlecity;

import scenes.BattleCityScene;
import utils.Vector2D;

public interface BalaRule {
	
	boolean mustApply(Bala bala, BattleCityScene scene);
	void apply(Bala bala, BattleCityScene scene);
	

}


