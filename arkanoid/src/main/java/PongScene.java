import com.uqbar.vainilla.GameScene;

public class PongScene extends GameScene {

	private Pelota pelota;
	private int maxScore = 3;
	private Raqueta raqueta;
	private Bloque bloque;

	public Pelota getPelota() {
		return pelota;
	}

	public void setPelota(Pelota pelota) {
		this.addComponent(pelota);
		this.pelota = pelota;
	}

	public void setRaqueta(Raqueta raqueta) {
		this.raqueta = raqueta;
		this.addComponent(raqueta);
	}

	public void setBloque(Bloque bloque) {
		this.bloque = bloque;
		this.addComponent(bloque);
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public Raqueta getRaqueta() {
		return raqueta;
	}
	
	public Bloque getBloque(){
		return bloque;
	}
}