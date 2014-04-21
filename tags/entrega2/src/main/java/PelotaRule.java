


public interface PelotaRule {

	boolean mustApply(Pelota pelota, Vector2D nuevaPosicion, ArkanoidScene scene);
	void apply(Pelota pelota, Vector2D nuevaPosicion, ArkanoidScene scene);
}