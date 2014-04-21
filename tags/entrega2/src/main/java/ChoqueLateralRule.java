



public abstract class ChoqueLateralRule implements PelotaRule {

	@Override
	public abstract boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			ArkanoidScene scene);

	@Override
	public void apply(Pelota pelota, Vector2D nuevaPosicion, ArkanoidScene scene) {
		this.invertirX(pelota);
		pelota.setX(this.newX(pelota, nuevaPosicion, scene));
		pelota.setY(nuevaPosicion.getY());
	}

	protected abstract double newX(Pelota pelota, Vector2D nuevaPosicion, ArkanoidScene scene);

	protected void invertirX(Pelota pelota) {
		pelota.setDireccion(new Vector2D(-pelota.getDireccion().getX(), pelota.getDireccion().getY()));
	}


}