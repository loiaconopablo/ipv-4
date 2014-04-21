
public class PerdidaDeVidaRule implements PelotaRule {
	
	public PerdidaDeVidaRule() {
		super();
	}

	@Override
	public boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			ArkanoidScene scene) {
		return this.perdio(pelota, nuevaPosicion);
	}

	protected boolean perdio(Pelota pelota, Vector2D nuevaPosicion){
		return nuevaPosicion.getY() > pelota.getGame().getDisplayHeight();}
	
	@Override
	public void apply(Pelota pelota, Vector2D nuevaPosicion, ArkanoidScene scene) {
		pelota.centrar();
		scene.getRaqueta().centrar();
		scene.getMarcadorVidas().bajarMarcador();
		this.revisarFinJuego(scene);
	}

	protected void revisarFinJuego(ArkanoidScene scene) {
		if(scene.getMarcadorVidas().fin()) {
			scene.fin();
		}
	}


}