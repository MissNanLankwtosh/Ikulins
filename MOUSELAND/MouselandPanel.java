
public class MouselandPanel extends GamePanel {

	/**
	 * 
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
