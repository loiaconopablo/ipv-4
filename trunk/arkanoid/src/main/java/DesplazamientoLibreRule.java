



public class DesplazamientoLibreRule implements PelotaRule {

	@Override
	public boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			PongScene scene) {
		return true;
	}

	@Override
	public void apply(Pelota pelota, Vector2D nuevaPosicion, PongScene scene) {
		pelota.setX(nuevaPosicion.getX());
		pelota.setY(nuevaPosicion.getY());
	}

}