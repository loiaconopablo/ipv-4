

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;

public class Pelota extends GameComponent<PongScene> {

	private Vector2D direccion;
	private double velocidad;
	private double xInicial;
	private double yInicial;
	private Vector2D direccionInicial;
	private double velocidadInicial;
	private double velocidadStep = 0.1;
	private List<PelotaRule> rules = new ArrayList<PelotaRule>();
	
	public Pelota(int radio, double xInicial, double yInicial, Vector2D direccionInicial, double velocidadInicial) {
		super(new Circle(Color.RED, radio), xInicial, xInicial);
		this.xInicial = xInicial;
		this.yInicial = yInicial;
		this.direccion = direccionInicial.asVersor();
		this.direccionInicial = this.direccion;
		this.velocidad = velocidadInicial;
		this.velocidadInicial = velocidadInicial;
	}
	
	private void initRules() {
		this.rules.add(new ChoqueADerechaRule());
		this.rules.add(new ChoqueAIzquierdaRule());
		this.rules.add(new ChoqueAArriba());
		this.rules.add(new ChoqueAAbajo());
		this.rules.add(new DesplazamientoLibreRule());
	}

	@Override
	public void update(DeltaState deltaState) {
		Vector2D nuevaPosicion = this.direccion.producto(velocidad*deltaState.getDelta()).suma(new Vector2D(this.getX(), this.getY()));
				
		for(PelotaRule rule : this.getRules()) {
			if(rule.mustApply(this, nuevaPosicion, this.getScene())) {
				rule.apply(this, nuevaPosicion, this.getScene());
				break;
			}
		}
		super.update(deltaState);
	}


	private List<PelotaRule> getRules() {
		if(this.rules.isEmpty()) {
			this.initRules();
		}
		return this.rules;
	}

	public void setDireccion(Vector2D vector2d) {
		this.direccion = vector2d.asVersor();
	}

	public Vector2D getDireccion() {
		return this.direccion;
	}


	public double getVelocidadStep() {
		return velocidadStep;
	}

	public void setVelocidadStep(double velocidadStep) {
		this.velocidadStep = velocidadStep;
	}
	
}