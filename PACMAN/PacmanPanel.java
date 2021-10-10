import java.awt.event.KeyEvent;


public class PacmanPanel extends GamePanel{

	public PacmanPanel(Game g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void GameInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newGame() {
		setGame(new PacmanGame());
		((PacmanGame)getGame()).start();
	}

	/**
	 * Pass the KeyEvent to PacmanGame and let it deal with it in recieveInput
	 */
	@Override
	public void onKeyPress(int keycode) {
		((PacmanGame)getGame()).recieveInput(keycode);
	}

}
