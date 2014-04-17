import com.uqbar.vainilla.GameComponent;


public class ColisionBloqueRule extends ColisionRule{

	private GameComponent<ArkanoidScene> objetoArebotar;
	
	public ColisionBloqueRule(GameComponent<ArkanoidScene> objetoArebotar) {
		super(objetoArebotar);
		this.objetoArebotar = objetoArebotar;
	}
	
	@Override
	public void apply(Pelota pelota, Vector2D nuevaPosicion, ArkanoidScene scene) {
		super.apply(pelota, nuevaPosicion, scene);
		pelota.removeRule(this);
		this.objetoArebotar.destroy();
		scene.getBloques().remove(objetoArebotar);
		scene.getMarcador().gol();		
		
	}
	
	

}
