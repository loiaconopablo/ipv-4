package colisiones;

import pacman.Comida;
import pacman.Personaje;
import scenes.PacmanScene;

import com.uqbar.vainilla.GameComponent;

public class ColisionPersonajeComidaRule extends ColisionComidaBloqueRule{
	

	public ColisionPersonajeComidaRule(GameComponent<PacmanScene> objetoArebotar) {
		super(objetoArebotar);
	}

	@Override
	public void apply(GameComponent<PacmanScene> objeto, PacmanScene scene) {
		scene.getComidas().remove(this.getObjetoArebotar());
		((Personaje)objeto).removeRule(this);
		((Personaje)objeto).getSonidoComiendo().play();
		scene.getMarcadorPuntos().subirMarcador();
		this.getObjetoArebotar().destroy();
		scene.revisarFinDelJuego();
		
	}

}

