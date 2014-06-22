package map;

import pacman.Comida;
import pacman.Pared;
import pacman.Personaje;
import pacman.TerrenoVacio;
import scenes.PacmanScene;

public class MapManager {
	
	private Map mapa = new Map();

	public void build(PacmanScene pacmanScene) {
		for(int y = 0; y < this.mapa.getMapa().length; y++){
			for(int x = 0; x < this.mapa.getMapa()[0].length; x++){
				this.build(pacmanScene, x, y);
			}
		}
//		System.out.println(this.mapa.getMapa()[1][0]);
//		System.out.println(this.mapa.getMapa().length);
		
	}

	private void build(PacmanScene pacmanScene, int x, int y) {
		if(this.mapa.getMapa()[y][x] == 1){
			Pared pared = new Pared(x,y);
			pacmanScene.agregarALaGrilla(x,y,pared);
			pacmanScene.buildComponent(x, y,pared);

		}
		if(this.mapa.getMapa()[y][x] == 2){
			TerrenoVacio vacio = new TerrenoVacio(x,y);
			pacmanScene.agregarALaGrilla(x,y,vacio);
			pacmanScene.buildComponent(x, y,vacio);
			
		}
		if(this.mapa.getMapa()[y][x] == 3){
			pacmanScene.buildPersonaje(x, y);
		}
		
	}


	

}
