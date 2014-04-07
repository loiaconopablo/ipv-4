


public class ChoqueAArriba extends ChoqueExtremoRule{

	@Override
	public boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			PongScene scene) {
		return pelota.getGame().getDisplayHeight() <= nuevaPosicion.getY() + pelota.getAppearance().getHeight();
	}

	@Override
	protected double newY(Pelota pelota, Vector2D nuevaPosicion, PongScene scene) {
		return pelota.getGame().getDisplayHeight()-pelota.getAppearance().getHeight();
	}

}
