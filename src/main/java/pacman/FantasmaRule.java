package pacman;

import scenes.PacmanScene;
import utils.Vector2D;

public interface FantasmaRule {
	
	boolean mustApply(Personaje tanque, PacmanScene scene);
	void apply(Personaje tanque, PacmanScene scene);

}
