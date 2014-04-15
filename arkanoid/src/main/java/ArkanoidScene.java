
import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.GameScene;

public class ArkanoidScene extends GameScene {


	private Pelota pelota;
	private Raqueta raqueta;
	private List<Bloque> bloques = new ArrayList<Bloque>();


	public Pelota getPelota() {
		return pelota;
	}

	public void setPelota(Pelota pelota) {
		this.addComponent(pelota);
		this.pelota = pelota;
	}
	
	public void setRaqueta(Raqueta raqueta){
			this.raqueta = raqueta;
			this.addComponent(raqueta);
	}

	public Raqueta getRaqueta() {
		return raqueta;
	}
	
	public void addBloque(Bloque bloque){
		this.bloques.add(bloque);
		this.addComponent(bloque);
	}

	public List<Bloque> getBloques() {
		return bloques;
	}
}