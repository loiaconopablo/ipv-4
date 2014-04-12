

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;

public class RaquetaPlayerStrategy implements RaquetaStrategy {

	private Key leftKey = Key.LEFT;
	private Key rigthKey = Key.RIGHT;
	
	@Override
	public void update(Raqueta raqueta, PongScene scene, DeltaState deltaState) {
		if(deltaState.isKeyBeingHold(rigthKey)) {
			raqueta.derecha(deltaState.getDelta());
		}
		else if(deltaState.isKeyBeingHold(leftKey)) {
			raqueta.izquierda(deltaState.getDelta());
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