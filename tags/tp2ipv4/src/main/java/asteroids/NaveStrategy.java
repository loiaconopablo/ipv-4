package asteroids;
/// HAY QUE BORRAR ESTA CLASE; LA DE DEJO POR LAS DUDAS POR AHORA.!!!!!!!!!!!!!!!!!!!!!!!!!
import scenes.AsteroidsScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;

public class NaveStrategy {
	
	private Key leftKey = Key.LEFT;
	private Key rigthKey = Key.RIGHT;
	
	public void update(Nave nave, AsteroidsScene scene, DeltaState deltaState) {
		if(deltaState.isKeyBeingHold(rigthKey)) {
			//nave.derecha(deltaState.getDelta());
		}
		else if(deltaState.isKeyBeingHold(leftKey)) {
			//nave.izquierda(deltaState.getDelta());
		}
	}

	public Key getLeftKey() {
		return leftKey;
	}

	public void setLeftKey(Key leftKey) {
		this.leftKey = leftKey;
	}

	public Key getRigthKey() {
		return rigthKey;
	}

	public void setRigthKey(Key rigthKey) {
		this.rigthKey = rigthKey;
	}
	
	

}
