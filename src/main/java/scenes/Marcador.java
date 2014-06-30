package scenes;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Label;

public class Marcador extends GameComponent<BattleCityScene> {

	private int value;

	public Marcador(double x, double y, Color color, int valorInicial) {
		super(new Label(new Font("verdana", Font.BOLD, 30), color, "0"), x, y);
		this.value = valorInicial;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void subirMarcador() {
		this.setValue(this.getValue() + 1);
	}

	public void reset() {
		this.setValue(0000);
	}

	@Override
	public void update(DeltaState deltaState) {
		((Label) this.getAppearance())
				.setText(Integer.toString(this.getValue()));
		super.update(deltaState);
	}

	public boolean fin() {
		return this.getValue() == 0;
	}

	public void descontarMarcador() {
		this.setValue(this.getValue() - 1);

	}

}
