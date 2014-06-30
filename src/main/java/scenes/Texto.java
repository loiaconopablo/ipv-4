package scenes;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Label;

public class Texto extends GameComponent<PacmanScene> {

	private String valor;

	public Texto(double x, double y, Color color, String valorInicial) {
		super(new Label(new Font("Arial", Font.BOLD, 20), color, "0"), x, y);
		this.setValor(valorInicial);
	}

	@Override
	public void update(DeltaState deltaState) {
		((Label) this.getAppearance()).setText(this.getValor());
		super.update(deltaState);
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
