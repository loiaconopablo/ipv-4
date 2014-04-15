import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;


public abstract class Bloque extends GameComponent<ArkanoidScene>{

	public Bloque(Rectangle rectangle, int x, int y) {
		super(rectangle, x, y);
	}

	public abstract void update(Bloque bloque, ArkanoidScene scene, DeltaState deltaState);

}
