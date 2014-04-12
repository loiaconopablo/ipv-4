

public abstract class ChoqueExtremoRule implements PelotaRule{
	
	@Override
	public abstract boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			PongScene scene);

	@Override
	public void apply(Pelota pelota, Vector2D nuevaPosicion, PongScene scene) {
		this.invertirY(pelota);
		pelota.setY(this.newY(pelota, nuevaPosicion, scene));
		pelota.setX(nuevaPosicion.getX());
	}

	protected abstract double newY(Pelota pelota, Vector2D nuevaPosicion, PongScene scene);

	protected void invertirY(Pelota pelota) {
		pelota.setDireccion(new Vector2D(pelota.getDireccion().getX(), -pelota.getDireccion().getY()));
	}

}