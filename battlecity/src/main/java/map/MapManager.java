package map;

import scenes.BattleCityScene;

public class MapManager {
	
	private Map mapa = new Map();

	public void build(BattleCityScene battleCityScene) {
		for(int y = 0; y < this.mapa.getMapa().length; y++){
			for(int x = 0; x < this.mapa.getMapa()[0].length; x++){
				this.build(battleCityScene, x, y);
			}
		}
		System.out.println(this.mapa.getMapa()[1][0]);
		System.out.println(this.mapa.getMapa().length);
		
	}

	private void build(BattleCityScene battleCityScene, int x, int y) {
		if(this.mapa.getMapa()[y][x] == 1){
			battleCityScene.buildCementos(x, y);
		}
		if(this.mapa.getMapa()[y][x] == 2){
			battleCityScene.buildLadrillos(x, y);
		}
		if(this.mapa.getMapa()[y][x] == 3){
			battleCityScene.buildPasto(x, y);
		}
		
	}
	

}
