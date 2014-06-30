package map;

public class Map {
	
	private int mapa [][] = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
							 {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
							 {1,0,0,0,0,2,3,3,3,2,0,0,0,0,0,1},
							 {1,0,0,0,0,2,0,0,0,2,0,0,0,0,0,1},
							 {1,0,0,0,0,2,2,2,2,2,2,0,0,0,0,1},
							 {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
							 {1,0,2,2,2,0,3,3,3,0,0,2,2,2,2,1},
							 {1,0,0,2,0,0,3,3,3,0,0,0,0,0,0,1},
							 {1,0,0,2,0,0,0,0,0,0,0,0,0,0,0,1},
							 {1,0,0,0,0,0,2,2,2,0,0,0,0,0,0,1},
							 {1,0,0,0,4,0,2,5,2,0,0,0,1,0,0,1},
							 {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
//	REFERENCIAS:
//		#1 : Cemento
//		#2 : Ladrillo
//		#3 : Pasto
//		#4 : Tanque
//		#5 : VidaPrincipal Halcon
		
	public int[][] getMapa() {
		return mapa;
	}

	public void setMapa(int mapa[][]) {
		this.mapa = mapa;
	}	

}
