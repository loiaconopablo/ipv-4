package colisiones;

import pacman.Personaje;
import scenes.PacmanScene;

import com.uqbar.vainilla.GameComponent;

public class ColisionEntrePersonajeYFantasmas extends
		ColisionPersonajeComidaRule {

	public ColisionEntrePersonajeYFantasmas(GameComponent<PacmanScene> objetoArebotar) {
		super(objetoArebotar);
		
	}

	@Override
	public void apply(GameComponent<PacmanScene> objeto, PacmanScene scene) {
		//scene.getFantasmas().remove(this.getObjetoArebotar());
		//((Personaje)objeto).removeRuleFantasma(this);
		scene.getMarcadorVidas().descontarMarcador();
		((Personaje)objeto).getSonidoMuerte().play();
		((Personaje)objeto).resetCentrar();
		//this.getObjetoArebotar().destroy();
		scene.revisarFinDelJuego();
		
	}
}
