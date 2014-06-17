package map;

import battlecity.Cemento;
import battlecity.Ladrillo;
import battlecity.Pasto;
import scenes.BattleCityScene;

public class MapManager {
	
	private Map mapa = new Map();

	public void build(BattleCityScene battleCityScene) {
		for(int y = 0; y < this.mapa.getMapa().length; y++){
			for(int x = 0; x < this.mapa.getMapa()[0].length; x++){
				this.build(battleCityScene, x, y);
			}
		}
//		System.out.println(this.mapa.getMapa()[1][0]);
//		System.out.println(this.mapa.getMapa().length);
		
	}

	private void build(BattleCityScene battleCityScene, int x, int y) {
		if(this.mapa.getMapa()[y][x] == 1){
			Cemento cemento = new Cemento(x,y);
			battleCityScene.agregarALaGrilla(x,y,cemento);
			battleCityScene.buildComponent(x, y,cemento);

		}
		if(this.mapa.getMapa()[y][x] == 2){
			Ladrillo ladrillo = new Ladrillo(x,y);
			battleCityScene.agregarALaGrilla(x,y,ladrillo);
			battleCityScene.buildComponent(x, y,ladrillo);
		}
		if(this.mapa.getMapa()[y][x] == 3){
			Pasto pasto = new Pasto(x,y);
			battleCityScene.agregarALaGrilla(x,y,pasto);
			battleCityScene.buildComponent(x, y,pasto);
		}
		
	}
	

}
