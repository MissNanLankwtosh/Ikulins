
public class SurvivalPanel extends GamePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SurvivalPanel(Game g) {
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
		setGame(new SurvivalGame());
		((SurvivalGame)getGame()).start();
		repaint();
	}

	@Override
	public void onKeyPress(int keycode) {
		// TODO Auto-generated method stub
		((SurvivalGame)getGame()).recieveInput(keycode);
		repaint();
	}

}
