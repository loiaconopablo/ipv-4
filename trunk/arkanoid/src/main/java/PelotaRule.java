

public interface PelotaRule {

	boolean mustApply(Pelota pelota, Vector2D nuevaPosicion, PongScene scene);
	void apply(Pelota pelota, Vector2D nuevaPosicion, PongScene scene);
}