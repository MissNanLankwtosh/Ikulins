
public class MouselandPanel extends GamePanel {

	/**
	 * The Mouse Panel is used for graphical interface purposes
	 */
	private static final long serialVersionUID = 1L;

	public MouselandPanel(Game g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void GameInit() {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * A new game is created with a map and painted onto the display
	 */
	public void newGame() {
		// TODO Auto-generated method stub
		setGame(new MouselandGame());
		((MouselandGame)getGame()).start();
		repaint();
	}

	/**
	 * Pass the KeyEvent to MouselandGame and let it deal with it in recieveInput
	 */
	@Override
	public void onKeyPress(int keycode) {
		((MouselandGame)getGame()).recieveInput(keycode);
		repaint();
	}
}
