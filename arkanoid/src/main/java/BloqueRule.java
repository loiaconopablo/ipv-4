
//Falta Terminar
public interface BloqueRule {

	public abstract boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			PongScene scene);
	void apply(Pelota pelota, Bloque bloque, PongScene scene);
}