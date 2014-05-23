package battlecity;

import scenes.BattleCityScene;

public class ColisionBloqueRule extends ColisionRule {
	
	private Bloque objetoArebotar;
	
	public ColisionBloqueRule(Bloque objetoArebotar) {
		super(objetoArebotar);
		this.objetoArebotar = objetoArebotar;
	}
	
	@Override
	public void apply(Bala bala, BattleCityScene scene) {
		super.apply(bala, scene);
		this.objetoArebotar.choqueConBala(scene);	
		
	}
	
	

}
