package battlecity;

import scenes.BattleCityScene;

import com.uqbar.vainilla.GameComponent;

public class ColisionBalaTanqueJugadorRule extends ColisionBalaBloqueRule {
	
		public ColisionBalaTanqueJugadorRule(GameComponent<BattleCityScene> objetoArebotar) {
			super(objetoArebotar);
		}
		
		@Override
		public void apply(GameComponent<BattleCityScene> objeto, BattleCityScene scene) {
			((Bala) objeto).removeRule(this);
			scene.getBalas().remove(objeto);
			((Bala) objeto).volverASetearseAsuDueño();	
			scene.removeComponent(objeto);
			((Tanque)this.getObjetoArebotar()).resetCentrar();
			scene.getMarcadorVidas().descontarMarcador();
			scene.revisarFinDelJuego();
		}


}
