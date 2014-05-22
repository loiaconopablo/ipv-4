package asteroids;

import scenes.AsteroidsScene;
import utils.Vector2D;

public interface NaveRule {
	
	boolean mustApply(Nave nave, AsteroidsScene scene);
	void apply(Nave nave, AsteroidsScene scene);

}
