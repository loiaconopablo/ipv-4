


public class ChoqueADerechaRule extends ChoqueLateralRule {

	@Override
	public boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			PongScene scene) {
		return pelota.getGame().getDisplayWidth() <= nuevaPosicion.getX() + pelota.getAppearance().getWidth();
	}

	@Override
	protected double newX(Pelota pelota, Vector2D nuevaPosicion, PongScene scene) {
		return pelota.getGame().getDisplayWidth()-pelota.getAppearance().getWidth();
	}

}