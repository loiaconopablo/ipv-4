package asteroids;

import scenes.AsteroidsScene;
import utils.Vector2D;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class ColisionRule implements NaveRule{


	private static double anguloMayor = Math.PI/3;
	private static double anguloMenor = -Math.PI/3;
	private GameComponent<AsteroidsScene> objetoArebotar;

	public ColisionRule(GameComponent<AsteroidsScene> objetoArebotar) {
		super();
		this.objetoArebotar = objetoArebotar;
	}

	public boolean mustApply(Nave nave,
			AsteroidsScene scene) {
		return this.colisiona(objetoArebotar, nave) ;
	}
	
	private boolean colisiona(GameComponent<AsteroidsScene> objetoArebotar, Nave nave) {
		return CollisionDetector.INSTANCE.collidesRectAgainstRect(
				objetoArebotar.getX(),objetoArebotar.getY(),(int)objetoArebotar.getAppearance().getWidth(),
				(int)objetoArebotar.getAppearance().getHeight(),
				nave.getX(),nave.getY(),(int)nave.getAppearance().getWidth(),
				(int)nave.getAppearance().getHeight());
//				nuevaPosicion.getX(), nuevaPosicion.getY(), nave
//						.getAppearance().getWidth() / 2, objetoArebotar.getX(),
//				objetoArebotar.getY(), objetoArebotar.getAppearance().getWidth(), objetoArebotar
//						.getAppearance().getHeight());
	}

	public void apply(Nave nave, AsteroidsScene scene) {
//			double puntoDeColision = getPuntoColision(objetoArebotar, nave,
//					nuevaPosicion);
//
//			double signoY = Math.signum(nave.getDireccion().getY());
//
//			double anguloNuevo = ((anguloMayor - anguloMenor) / objetoArebotar
//					.getAppearance().getWidth())
//					* puntoDeColision
//					+ anguloMenor;
//			// aprovecho e invierto el signo que traia Y con el truquito de
//			// multiplicarlo por -1
//			nave.setDireccion(new Vector2D(Math.sin(anguloNuevo), (-1) * signoY * Math.cos(anguloNuevo)));
//
//			// pelota.setX(nuevaPosicion.getX());
//			nave.setY(signoY > 0 ? objetoArebotar.getY()
//					- nave.getAppearance().getHeight() - 1 : objetoArebotar.getY()
//					+ objetoArebotar.getAppearance().getHeight() + 1);
		

	}
	
	private double getPuntoColision(GameComponent<AsteroidsScene> objetoArebotar, Nave nave,
			Vector2D nuevaPosicion) {
		if (nave.getX() > objetoArebotar.getX()
				&& nave.getX() + nave.getAppearance().getWidth() < objetoArebotar
						.getX() + objetoArebotar.getAppearance().getWidth()) {
			double xCentroPelota = nuevaPosicion.getX()
					+ nave.getAppearance().getWidth() / 2;

			return xCentroPelota - objetoArebotar.getX();
		} else if (nave.getX() < objetoArebotar.getX()) {
			return 0;
		} else {
			return objetoArebotar.getAppearance().getWidth();
		}
	}



}