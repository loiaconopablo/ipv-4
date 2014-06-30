package colisiones;

import battlecity.Bala;
import battlecity.Posicion;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

import scenes.BattleCityScene;

public class ColisionBalaLadrilloRule extends ColisionBalaBloqueRule {

	public ColisionBalaLadrilloRule(GameComponent<BattleCityScene> objetoArebotar) {
		super(objetoArebotar);
	}
	
	@Override
	public void apply(GameComponent<BattleCityScene> objeto, BattleCityScene scene) {
		((Bala) objeto).removeRule(this);
		scene.getBalas().remove(objeto);
		((Bala) objeto).volverASetearseAsuDueño();	
		scene.getBloques().remove(this.getObjetoArebotar());
		scene.removeComponent(objeto);
		Posicion ladrillo=scene.getGrilla().getPosicion(this.getObjetoArebotar().getX(), this.getObjetoArebotar().getY());
		ladrillo.setElemento(null);
		this.getObjetoArebotar().destroy();
		scene.setFinDeJuego(true);
		
	}

}
