package battlecity;

import scenes.BattleCityScene;

public interface BalaRule {
	
	boolean mustApply(Bloque bloque, BattleCityScene scene);
	void apply(Bloque bloque, BattleCityScene scene);

}
