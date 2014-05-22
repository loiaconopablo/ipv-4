package battlecity;

import scenes.BattleCityScene;
import utils.Vector2D;

public interface TanqueRule {
	
	boolean mustApply(Tanque nave, BattleCityScene scene);
	void apply(Tanque nave, BattleCityScene scene);

}
