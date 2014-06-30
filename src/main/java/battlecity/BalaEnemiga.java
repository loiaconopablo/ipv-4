package battlecity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import colisiones.ColisionBalaBloqueRule;
import colisiones.ColisionBalaHalconVidaRule;
import colisiones.ColisionBalaTanqueJugadorRule;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Circle;

import utils.Vector2D;

public class BalaEnemiga extends Bala {

	private Vector2D velocity;
	private List<ColisionBalaBloqueRule> rules = new ArrayList<ColisionBalaBloqueRule>();
	private Tanque propietario;
	
	public BalaEnemiga(TanqueEnemigo dueño, double x, double y, Vector2D velocity) {
		super(dueño, x, y, velocity);
		this.setZ(-1);
	}

	@Override
	public void update(DeltaState deltaState) {
		Vector2D newDelta = velocity.producto(deltaState.getDelta());
		this.setX(this.getX() + newDelta.getX());
		this.setY(this.getY() + newDelta.getY());
		if(!this.getScene().isFinDeJuego() && this.seFueDelJuego() ){
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

	public boolean seFueDelJuego() {
		return(	this.getGame().getDisplayHeight() <= this.getY()+this.getAppearance().getHeight() ||
		this.getY() <= 0 ||
		this.getGame().getDisplayWidth()-150 <= this.getX()+this.getAppearance().getWidth() ||
		this.getX() <= 0); 
		}


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
		
		this.rules.add(new ColisionBalaTanqueJugadorRule(this.getScene().getTanque()));
		this.rules.add(new ColisionBalaHalconVidaRule(this.getScene().getVida()));
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

	public void removeRule(ColisionBalaBloqueRule colisionBalaBloqueRule) {
		this.getRules().remove(colisionBalaBloqueRule);		
	}

}
