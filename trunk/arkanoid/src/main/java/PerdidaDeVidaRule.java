

public class PerdidaDeVidaRule implements PelotaRule {

	//private Marcador marcador;
	
	public PerdidaDeVidaRule() {
		super();
		//this.marcador = marcador;
	}

	@Override
	public boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			PongScene scene) {
		return this.perdio(pelota, nuevaPosicion);
	}

	protected boolean perdio(Pelota pelota, Vector2D nuevaPosicion){
		return nuevaPosicion.getY() > pelota.getGame().getDisplayHeight();}
	
	@Override
	public void apply(Pelota pelota, Vector2D nuevaPosicion, PongScene scene) {
		pelota.centrar();
	
		scene.getRaqueta().centrar();
	//	this.changeMarcador();
	//	this.revisarFinJuego(scene);
	}

//	protected void revisarFinJuego(PongScene scene) {
//		if(marcador.finJuego(scene)) {
//			scene.fin();
//		}
//	}
//
//	protected void changeMarcador() {
//		this.marcador.gol();
//	}


}