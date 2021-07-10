//this does nothing atm...

public class LittlePill extends Item {

	public LittlePill(int x, int y) {
		super(x, y);
	}

	@Override
	public void action(Object obj) {
		PacmanGame game = (PacmanGame)obj;
		game.setScore(game.getScore()+1);
	}
	
	@Override
	public void onEvent(GameEvent e) {
		if(e.getGameValue() instanceof PacmanGame) {
			if(e.getSource().equals("littlepill")) action(e.getGameValue());
		}
	}

}
