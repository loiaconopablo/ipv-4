package battlecity;

import scenes.BattleCityScene;
import utils.Vector2D;

public interface TanqueRule {
	
	boolean mustApply(Tanque tanque, BattleCityScene scene);
	void apply(Tanque tanque, BattleCityScene scene);

}
