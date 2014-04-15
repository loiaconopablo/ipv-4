
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class ColisionRule implements PelotaRule {

	private static double anguloMayor = Math.PI/3;
	private static double anguloMenor = -Math.PI/3;
	private GameComponent<ArkanoidScene> objetoArebotar;

	public ColisionRule(GameComponent<ArkanoidScene> objetoArebotar) {
		super();
		this.objetoArebotar = objetoArebotar;
	}

	@Override
	public boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			ArkanoidScene scene) {
		return this.colisiona(objetoArebotar, pelota, nuevaPosicion) ;
	}
	
	private boolean colisiona(GameComponent<ArkanoidScene> objetoArebotar, Pelota pelota,
			Vector2D nuevaPosicion) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				nuevaPosicion.getX(), nuevaPosicion.getY(), pelota
						.getAppearance().getWidth() / 2, objetoArebotar.getX(),
				objetoArebotar.getY(), objetoArebotar.getAppearance().getWidth(), objetoArebotar
						.getAppearance().getHeight());
	}

	@Override
	public void apply(Pelota pelota, Vector2D nuevaPosicion, ArkanoidScene scene) {
			double puntoDeColision = getPuntoColision(objetoArebotar, pelota,
					nuevaPosicion);

			double signoY = Math.signum(pelota.getDireccion().getY());

			double anguloNuevo = ((anguloMayor - anguloMenor) / objetoArebotar
					.getAppearance().getWidth())
					* puntoDeColision
					+ anguloMenor;
			// aprovecho e invierto el signo que traia Y con el truquito de
			// multiplicarlo por -1
			pelota.setDireccion(new Vector2D(Math.sin(anguloNuevo), (-1) * signoY * Math.cos(anguloNuevo)));

			// pelota.setX(nuevaPosicion.getX());
			pelota.setY(signoY > 0 ? objetoArebotar.getY()
					- pelota.getAppearance().getHeight() - 1 : objetoArebotar.getY()
					+ objetoArebotar.getAppearance().getHeight() + 1);
		

	}
	
	private double getPuntoColision(GameComponent<ArkanoidScene> objetoArebotar, Pelota pelota,
			Vector2D nuevaPosicion) {
		if (pelota.getX() > objetoArebotar.getX()
				&& pelota.getX() + pelota.getAppearance().getWidth() < objetoArebotar
						.getX() + objetoArebotar.getAppearance().getWidth()) {
			double xCentroPelota = nuevaPosicion.getX()
					+ pelota.getAppearance().getWidth() / 2;

			return xCentroPelota - objetoArebotar.getX();
		} else if (pelota.getX() < objetoArebotar.getX()) {
			return 0;
		} else {
			return objetoArebotar.getAppearance().getWidth();
		}
	}



}
