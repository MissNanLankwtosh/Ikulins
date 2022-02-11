import java.util.ArrayList;




public class Hero extends Player implements GameListener{
	private int numOfTraps;
	private final int MAXNUMTRAP = 4;
	private final int INITIALX = 1;
	private final int INITIALY = 7;
	
	/**
	 * Creates a Hero with desired X and Y co-ordinate, Direction will be LEFT by default and number of lives will
	 * be set to one
	 * @param x X coordinate of Hero
	 * @param y Y coordinate of Hero
	 */
	public Hero(int x, int y){
		super(x,y);
		this.setImage("MOUSELAND/person.png");
		this.numOfTraps = MAXNUMTRAP;
	}
	
	/**
	 * Creates a Hero with desired direction, lives and position
	 * @param x X coordinate of Hero
	 * @param y Y coordinate of Hero
	 * @param direction Direction of Hero can be UP DOWN LEFT or RIGHT
	 * @param numOflives Number of lives the Hero has
	 */
	public Hero(int x, int y, Direction direction, int numOflives) {
		super(x, y, direction, numOflives);
		this.setImage("MOUSELAND/person.png");
		this.numOfTraps = MAXNUMTRAP;
	}
	
	/**
	 * @return Number of traps left
	 */
	public int getNumOfTraps(){
		return numOfTraps;
	}
	@Override
	public void spawn(Map map) {
		this.setX(INITIALX);
		this.setY(INITIALY);
		map.addMappable(this);
	}

	@Override
	public void die(Map map) {
		System.exit(-1);
	}
	/**
	 * Lays down trap on current position.
	 * @param map Map on which the trap is to be laid
	 */
	public void layTrap(Map map){
		if(numOfTraps != 0){	
			map.addMappable(new TrapItem(this.getX(), this.getY()));
			numOfTraps--;
		}
		
	}

	@Override
	public void onEvent(GameEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		/**
		 * If hero hits a mouse, .die()
		 * If hero hits the ladder, print you win, die.
		 * That is all.
		 */
		//MOVEMENT
		if(e.getSource().equals("heromovement") && e.getGameValue() instanceof MouselandGame) { //if mouse movement has occurred
			MouselandGame tempGame = (MouselandGame)e.getGameValue(); //create a temp variable of the game
			ArrayList<Mappable> mappableArray = tempGame.getMap().getMappable(getX(),getY());
			for (int i=mappableArray.size()-1; i >= 0; i--) {
				if(mappableArray.get(i).getClass().getName() == "Mouse") {
					this.die(tempGame.getMap());
				}
				if(mappableArray.get(i).getClass().getName() == "Exit") {
					System.out.println("You won!");
					this.die(tempGame.getMap());
				}
				
			}
		}//end of Movement
	}
	
	

}
