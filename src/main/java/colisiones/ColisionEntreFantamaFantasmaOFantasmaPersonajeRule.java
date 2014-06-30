package colisiones;

import scenes.PacmanScene;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

public abstract class ColisionEntreFantamaFantasmaOFantasmaPersonajeRule implements ColisionRule {
		
		private GameComponent<PacmanScene> objetoArebotar;

		public ColisionEntreFantamaFantasmaOFantasmaPersonajeRule(GameComponent<PacmanScene> objetoArebotar) {
			this.objetoArebotar = objetoArebotar;
		}
		public GameComponent<PacmanScene> getObjetoArebotar() {
			return objetoArebotar;
		}

		public boolean mustApply(GameComponent<PacmanScene> objetoQuePega,
				PacmanScene scene) {
			return this.colisiona(objetoArebotar, objetoQuePega) ;
		}
		
		public boolean colisiona(GameComponent<PacmanScene> objetoArebotar,
				GameComponent<PacmanScene> objetoQuePega) {
			return CollisionDetector.INSTANCE.collidesRectAgainstRect(
					objetoArebotar.getX(), 
					objetoArebotar.getY(), 
					(int) objetoArebotar.getAppearance().getWidth(), 
					(int) objetoArebotar.getAppearance().getHeight(), 
					objetoQuePega.getX(),
					objetoQuePega.getY(), 
					(int) objetoQuePega.getAppearance().getWidth(), 
					(int) objetoQuePega.getAppearance().getHeight());
		}

		public abstract void apply(GameComponent<PacmanScene> objetoArebotar, PacmanScene scene);
		
		
		public void setObjetoArebotar(GameComponent<PacmanScene> objetoArebotar) {
			this.objetoArebotar = objetoArebotar;
		}
		
		

	}