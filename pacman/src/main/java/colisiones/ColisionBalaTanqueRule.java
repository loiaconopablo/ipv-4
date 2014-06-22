package colisiones;

import pacman.Comida;
import scenes.PacmanScene;

import com.uqbar.vainilla.GameComponent;


	public class ColisionBalaTanqueRule extends ColisionBalaBloqueRule {


		public ColisionBalaTanqueRule(GameComponent<PacmanScene> objetoArebotar) {
			super(objetoArebotar);
		}
		
		@Override
		public void apply(GameComponent<PacmanScene> objeto, PacmanScene scene) {
			((Comida) objeto).removeRule(this);
			scene.getComidas().remove(objeto);
			scene.getFantasmas().remove(this.getObjetoArebotar());
			scene.removeComponent(objeto);
			scene.getMarcadorPuntos().subirMarcador();
			this.getObjetoArebotar().destroy();
			scene.revisarFinDelJuego();
			
		}

	}