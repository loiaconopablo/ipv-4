package colisiones;

import pacman.Comida;
import pacman.Posicion;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

import scenes.PacmanScene;

public class ColisionBalaLadrilloRule extends ColisionComidaBloqueRule {

	public ColisionBalaLadrilloRule(GameComponent<PacmanScene> objetoArebotar) {
		super(objetoArebotar);
	}
	
	@Override
	public void apply(GameComponent<PacmanScene> objeto, PacmanScene scene) {
		((Comida) objeto).removeRule(this);
		scene.getComidas().remove(objeto);
		scene.getBloques().remove(this.getObjetoArebotar());
		scene.removeComponent(objeto);
		Posicion ladrillo=scene.getGrilla().getPosicion(this.getObjetoArebotar().getX(), this.getObjetoArebotar().getY());
		ladrillo.setElemento(null);
		this.getObjetoArebotar().destroy();
		scene.setFinDeJuego(true);
		
	}

}
