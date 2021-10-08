
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

	@Override
	public void onKeyPress(int keycode) {
		// TODO Auto-generated method stub
		
	}

}
