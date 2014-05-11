package asteroids;

import scenes.AsteroidsScene;

public interface BalaRule {
	
	boolean mustApply(Bloque bloque, AsteroidsScene scene);
	void apply(Bloque bloque, AsteroidsScene scene);

}
