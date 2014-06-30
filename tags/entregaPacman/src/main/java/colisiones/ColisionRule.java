package colisiones;

import scenes.PacmanScene;

import com.uqbar.vainilla.GameComponent;

public interface ColisionRule {

	boolean mustApply(GameComponent<PacmanScene> objetoQuePega,PacmanScene scene); 
	boolean colisiona(GameComponent<PacmanScene> objetoArebotar,GameComponent<PacmanScene> objetoQuePega);
	abstract void apply(GameComponent<PacmanScene> objetoArebotar, PacmanScene scene);
	
}
