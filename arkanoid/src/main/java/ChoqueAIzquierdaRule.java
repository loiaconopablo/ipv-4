



public class ChoqueAIzquierdaRule extends ChoqueLateralRule {

	@Override
	public boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			PongScene scene) {
		return nuevaPosicion.getX() <= 0;
	}

	@Override
	protected double newX(Pelota pelota, Vector2D nuevaPosicion, PongScene scene) {
		
		return 0;
	}

}