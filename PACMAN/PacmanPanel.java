


public class PacmanPanel extends GamePanel{

	/**
	 * The Panel - Used for Graphical Representation
	 */
	private static final long serialVersionUID = 1L;

	public PacmanPanel(Game g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void GameInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Creates a new pacman game and displays it on the frame
	 */
	public void newGame() {
		setGame(new PacmanGame());
		((PacmanGame)getGame()).start();
		repaint();
	}

	/**
	 * Pass the KeyEvent to PacmanGame and let it deal with it in recieveInput
	 */
	@Override
	public void onKeyPress(int keycode) {
		((PacmanGame)getGame()).recieveInput(keycode);
		repaint();
	}

}
