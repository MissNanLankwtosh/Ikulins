import java.awt.Color;


public class FroggerPanel extends GamePanel{

	/**
	 * The Panel - Used for Graphical Representation
	 */
	private static final long serialVersionUID = 1L;

	
	public FroggerPanel(Game g) {
		super(g);
		this.setBgColor(new Color(200,200,200));
		this.setMazeColor(new Color(0,0,0));
	}

	@Override
	public void GameInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Creates a new frogger game and displays it on the frame
	 */
	public void newGame() {
		setGame(new FroggerGame());
		((FroggerGame)getGame()).start();
		repaint();
	}

	/**
	 * Pass the KeyEvent to PacmanGame and let it deal with it in recieveInput
	 */
	@Override
	public void onKeyPress(int keycode) {
		((FroggerGame)getGame()).recieveInput(keycode);
		repaint();
	}


}
