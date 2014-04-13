
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;

public class Bloque extends GameComponent<PongScene> {

	//private double velocidad;	
	//private double velocidadInicial;
	private double xInicial;
	private double yInicial;
	private List<BloqueRule> rules = new ArrayList<BloqueRule>();


	public Bloque(double x, double y, int ancho, int alto, Color color) {
		super(new Rectangle(color, ancho, alto), x, y);
		this.xInicial = x;
		this.yInicial = y;
	}
	
	//Falta Terminar, esto seria para detectar la colision y que rompa
//	private void initRules() {
//		this.rules.add(new ChoqueExtremoDerechoRule());
//		this.rules.add(new ChoqueExtremoIzquierdaRule());
//		this.rules.add(new ChoqueCentro());
//		this.rules.add(new Destruido());
//	}
	
	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
	}

}