import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;


public class EndScene extends GameScene {

	public EndScene( double x, double y, Marcador marcadorPuntos, ArkanoidScene arkanoidScene) {
		super(new WinOrLoseComponent(x, y, marcadorPuntos, arkanoidScene));
	}
}

