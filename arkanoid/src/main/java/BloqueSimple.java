import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Rectangle;


public class BloqueSimple extends Bloque{

	public BloqueSimple(Color color, int ancho, int alto, int x, int y){
		super(new Rectangle(color, ancho, alto), x, y);
	}
	
	@Override
	public void update(Bloque bloque, ArkanoidScene scene, DeltaState deltaState) {
		// TODO Auto-generated method stub
		
	}

}
