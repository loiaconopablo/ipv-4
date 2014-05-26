package battlecity;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import scenes.BattleCityScene;
import utils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;

public class Bala extends GameComponent<BattleCityScene> {
	private Vector2D velocity;
	private List<ColisionBalaBloqueRule> rules = new ArrayList<ColisionBalaBloqueRule>();
	private Tanque propietario;
	
	public Bala(Tanque dueño, double x, double y, Vector2D velocity) {
		super(new Circle(Color.RED, 7), x, y);
		this.setVelocity(velocity);
		this.setPropietario(dueño);
	}

	@Override
	public void update(DeltaState deltaState) {
		Vector2D newDelta = velocity.producto(deltaState.getDelta());
		this.setX(this.getX() + newDelta.getX());
		this.setY(this.getY() + newDelta.getY());
		if(this.seFueDelJuego()){
			this.volverASetearseAsuDueño();
			this.getScene().removeComponent(this);
		}
		
		for(ColisionBalaBloqueRule rule : this.getRules()) {
			if(rule.mustApply(this, this.getScene())) {
				rule.apply(this, this.getScene());
				break;
			}
		}
		
	}

	private boolean seFueDelJuego() {
		return(	this.getGame().getDisplayHeight() <= this.getY()+this.getAppearance().getHeight() ||
		this.getY() <= 0 ||
		this.getGame().getDisplayWidth()-150 <= this.getX()+this.getAppearance().getWidth() ||
		this.getX() <= 0); 
		}
//		pelota.getGame().getDisplayHeight() <= nuevaPosicion.getY() + pelota.getAppearance().getHeight() ||
//		nuevaPosicion.getY() <= 0 ||
//				pelota.getGame().getDisplayWidth() <= nuevaPosicion.getX() + pelota.getAppearance().getWidth() ||
//				nuevaPosicion.getX() <= 0 ||		
//		
//		return(this.getX()+this.getAppearance().getWidth() > this.getGame().getDisplayWidth() || 
//				this.getX()+this.getAppearance().getWidth() < this.getGame().getDisplayWidth() || 
//				this.getY()+this.getAppearance().getHeight() < this.getGame().getDisplayHeight()||
//				this.getY()+this.getAppearance().getHeight() > this.getGame().getDisplayHeight());
//	}

	public void volverASetearseAsuDueño(){
		this.getPropietario().setTieneBala(true);
	}
	
	public List<ColisionBalaBloqueRule> getRules() {
		if(this.rules.isEmpty()) {
			this.initRules();
		}
		return rules;
	}

	public Vector2D getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}

	private void initRules() {
		
		for (Bloque bloque : this.getScene().getBloques() ) {
			this.rules.add(bloque.crearSuColisionConBala());
		}

	}

	public void setRules(List<ColisionBalaBloqueRule> rules) {
		this.rules = rules;
	}

	public Tanque getPropietario() {
		return propietario;
	}

	public void setPropietario(Tanque propietario) {
		this.propietario = propietario;
	}


}
