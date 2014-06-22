package colisiones;

import pacman.Comida;
import pacman.Personaje;
import scenes.PacmanScene;

import com.uqbar.vainilla.GameComponent;

public class ColisionBalaTanqueJugadorRule extends ColisionBalaBloqueRule {
	
		public ColisionBalaTanqueJugadorRule(GameComponent<PacmanScene> objetoArebotar) {
			super(objetoArebotar);
		}
		
		@Override
		public void apply(GameComponent<PacmanScene> objeto, PacmanScene scene) {
			((Comida) objeto).removeRule(this);
			scene.getComidas().remove(objeto);
			scene.removeComponent(objeto);
			((Personaje)this.getObjetoArebotar()).resetCentrar();
			scene.getMarcadorVidas().descontarMarcador();
			scene.revisarFinDelJuego();
		}


}
