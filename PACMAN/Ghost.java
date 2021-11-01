
public class Ghost extends Player implements GameListener {

	public Ghost(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public Ghost(int x, int y, Direction direction, int numOfLives) {
		super(x,y,direction,numOfLives);
		setImage("PACMAN/ghostimg" + numOfLives + ".png");
	}

	@Override
	public void spawn(Map map) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void die() {
		//don't decrease numOfLives when a ghost dies.
	}

	@Override
	public void onEvent(GameEvent e) {
		// TODO Auto-generated method stub

	}
	/**This function receives the x and y coordinates and the current state of Pacman and 
	 * determines where the ghosts should move. Basic movement logic is shown below:
	 * 
	 * -----MOVE GHOSTS-----
	 * Compare X position of Ghost in Map to Pacman's X to find the delta X distance
	 * Compare Y position of Ghost in Map to Pacman's Y to find the delta Y distance
	 * If X is less distance than Y, if pacman is in beast mode
	 * 		run ghosts X away
	 * 		if there is a wall there, run Y away
	 * 		if there is a wall there, run Y towards
	 * 		if there is a wall there, run X towards
	 * 		if there is a wall there
	 * Similar process for if Y is less distance than X. 
	 * If Pacman is not in beast mode, replace away with towards for each case.
	 *
	 */
	public void moveGhosts(int X, int Y, PacmanState mode, Map map){
		if(mode == PacmanState.BEASTMODE){
			if(this.getX() > X){
				if(mode == PacmanState.BEASTMODE){
					this.setDirection(Direction.RIGHT);
				}else{
					this.setDirection(Direction.LEFT);
				}
				if(this.updateLocation(map) == true){return;}
				else{
					if(this.getY() > Y){
						if(mode == PacmanState.BEASTMODE){
							this.setDirection(Direction.DOWN);
						}else{
							this.setDirection(Direction.UP);
						}
					}
					if(this.updateLocation(map) == true){return;}
					else{
						if(mode == PacmanState.BEASTMODE){
							this.setDirection(Direction.LEFT);
						}else{
							this.setDirection(Direction.RIGHT);
						}
						if(this.updateLocation(map) == true){return;}
						else{
							if(mode == PacmanState.BEASTMODE){
								this.setDirection(Direction.UP);
							}else{
								this.setDirection(Direction.DOWN);
							}
							if(this.updateLocation(map) == true){return;}
							else{return;}
						}
					}
				}
			}
		return;
		}
	}
}
