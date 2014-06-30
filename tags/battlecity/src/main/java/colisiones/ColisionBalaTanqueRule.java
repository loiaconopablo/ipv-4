package colisiones;

import scenes.BattleCityScene;
import battlecity.Bala;

import com.uqbar.vainilla.GameComponent;


	public class ColisionBalaTanqueRule extends ColisionBalaBloqueRule {


		public ColisionBalaTanqueRule(GameComponent<BattleCityScene> objetoArebotar) {
			super(objetoArebotar);
		}
		
		@Override
		public void apply(GameComponent<BattleCityScene> objeto, BattleCityScene scene) {
			((Bala) objeto).removeRule(this);
			scene.getBalas().remove(objeto);
			((Bala) objeto).volverASetearseAsuDueño();	
			scene.getTanquesEnemigos().remove(this.getObjetoArebotar());
			scene.removeComponent(objeto);
			scene.getMarcadorPuntos().subirMarcador();
			this.getObjetoArebotar().destroy();
			scene.revisarFinDelJuego();
			
		}

	}