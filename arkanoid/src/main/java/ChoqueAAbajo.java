

public class ChoqueAAbajo extends ChoqueExtremoRule{

	@Override
	public boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			PongScene scene) {
		return nuevaPosicion.getY() <= 0;
	}

	@Override
	protected double newY(Pelota pelota, Vector2D nuevaPosicion, PongScene scene) {
		
		return 0;
	}

}