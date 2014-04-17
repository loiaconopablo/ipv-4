import com.uqbar.vainilla.GameScene;


public class EndScene extends GameScene {

	public EndScene( double x, double y) {
		super(new WinOrLoseComponent(x, y));
	}
}

