import java.util.ArrayList;


public class Survivor extends Player implements GameListener{
	private final int INITIALX = 1;
	private final int INITIALY = 7;
	
	/**
	 * Creates a Hero with desired X and Y co-ordinate, Direction will be LEFT by default and number of lives will
	 * be set to one
	 * @param x X coordinate of Hero
	 * @param y Y coordinate of Hero
	 */
	public Survivor(int x, int y){
		super(x,y);
		this.setImage("SURVIVAL/hero.png");
	}
	
	/**
	 * Creates a Hero with desired direction, lives and position
	 * @param x X coordinate of Hero
	 * @param y Y coordinate of Hero
	 * @param direction Direction of Hero can be UP DOWN LEFT or RIGHT
	 * @param numOflives Number of lives the Hero has
	 */
	public Survivor(int x, int y, Direction direction, int numOflives) {
		super(x, y, direction, numOflives);
		this.setImage("SURVIVAL/hero.png");
	}
	
	

	@Override
	/**
	 * Creates a new survivor at (1, 7)
	 */
	public void spawn(Map map) {
		// TODO Auto-generated method stub
		this.setX(INITIALX);
		this.setY(INITIALY);
		map.addMappable(this);
	}
	/**
	 * Kills the survivor. This means you lose.
	 */
	@Override
	public void die(Map map) {
		
		System.exit(-1);
		// TODO Auto-generated method stub

	}
	@Override
	public void onEvent(GameEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		/**
		 * If hero hits a headhunter, .die()
		 * That is all.
		 */
		//MOVEMENT
		if(e.getSource().equals("heroMovement") && e.getGameValue() instanceof SurvivalGame) { //if mouse movement has occurred
			SurvivalGame tempGame = (SurvivalGame)e.getGameValue(); //create a temp variable of the game
			ArrayList<Mappable> mappableArray = tempGame.getMap().getMappable(getX(),getY());
			for (int i=mappableArray.size()-1; i >= 0; i--) {
				if(mappableArray.get(i).getClass().getName() == "HeadHunter") {
					//Print Score
					this.die(tempGame.getMap());
				}	
			}
		}
	}

}
