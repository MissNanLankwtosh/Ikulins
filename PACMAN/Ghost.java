import java.util.ArrayList;


public class Ghost extends Player implements GameListener {
	
	private boolean ghostState = false; //this determines whether ghosts run away or not (false = chase, true = run)
	private int ghostlives = 0;
	/**
	 * Constructs Ghost based on the X,Y location on the map
	 * @param x
	 * @param y
	 */
	public Ghost(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructs Ghost based not only on X,Y locations, but also by the direction the Ghost is facing and how many lives the ghost has.
	 * An image depicting the Ghost is also set.
	 * @param x
	 * @param y
	 * @param direction
	 * @param numOfLives
	 */
	public Ghost(int x, int y, Direction direction, int numOfLives) {
		super(x,y,direction,numOfLives);
		ghostlives = numOfLives;
		setImage("PACMAN/ghostimg" + ghostlives + ".png");
	}
	/**
	 * Spawns a ghost on the map
	 * @author Alexander Clelland
	 */
	@Override
	public void spawn(Map map) {
		setX(7+getNumOflives());
		setY(5);
		map.addMappable(this);
	}
	/**
	 * Kills a ghost and erases its existence on the map. When a ghost dies, it is immediately respawned.
	 * @author Alexander Clelland
	 */
	@Override
	public void die(Map map) {
		ArrayList<Mappable> mappableArray = map.getMappable(getX(),getY());
		for (int i=mappableArray.size()-1; i >= 0; i--) {
			if(mappableArray.get(i) == this) {
				map.removeMappable(getX(),getY(),i);
			}
		}
		spawn(map);
	}

	/**
	 * Listens for events from game
	 * 
	 * if the ghost receives a "pacmanBeastmode" event, it gets scared, starts to run away, and changes magically to a harmless bunny
	 * 
	 * if the ghost receives a "pacmanNormal" event, it gets ANGRY, and resumes chasing pacman, and becomes a scary ghost again
	 * 
	 * @author Colin MacDougall
	 * @param e
	 */
	public void onEvent(GameEvent e) {
		if(e.getSource().equals("pacmanBeastmode") && e.getGameValue() instanceof PacmanGame) {
			ghostState = true;
			setImage("PACMAN/bunny.png");
		}
		if(e.getSource().equals("pacmanNormal") && e.getGameValue() instanceof PacmanGame) {
			ghostState = false;
			setImage("PACMAN/ghostimg" + ghostlives + ".png");
		}
	}
	/**
	 * This function dictates the movement of the ghosts.
	 * When pacman is normal mode, the ghosts viciously chase you down, deciding every move which square
	 * will lead to the shortest distance to you, and take that path.
	 * If pacman is BEAST MODE, the ghosts will run away from you, taking the furthest path away from
	 * you by deciding this each movement, for every possible square
	 * @author Colin MacDougall
	 * @param X
	 * @param Y
	 * @param mode
	 * @param map
	 */
	public void moveGhosts(int X, int Y, Map map){
		//for the purpose of this method, 0 is up, 1 is right, 2 is down, 3 is left
		double uDist = 0;
		double rDist = 0;
		double dDist = 0;
		double lDist = 0;
		
		int difficulty = 1; //This is a sudo difficulty meter, set higher to make ghosts more stupid
		
		int DiscouragingLargeNumber = 1000;
		int SmallerDiscouragingLargeNumber = 990;
		int DiscouragingSmallNumber = -10;
		int LargerDiscouragingLargeNumber = 1;

		//find the distance the ghost is from pacman for each possible move
		for (int i = 0; i < 4; i++){
			switch(i){
			case 0://UP
				if ((map.isWall(this.getX(), this.getY()-1)) == false){
					//not a wall, you can move up
					uDist = Math.sqrt(Math.pow(Math.abs(Y-(this.getY()-1)),2) + Math.pow(Math.abs(X-this.getX()),2));
					}else{//there is a wall
						if (ghostState){
							uDist = DiscouragingSmallNumber; //set to small number so this will not be chosen as a possible path
						}else{ //NORMAL
							uDist = DiscouragingLargeNumber; //set to large number so when normal, path won't be chosen
						}
					}
				break;
			case 1://RIGHT
				if ((map.isWall(this.getX()+1, this.getY())) == false){
					//not a wall, you can move right
					rDist = Math.sqrt(Math.pow(Math.abs(Y-this.getY()),2) + Math.pow(Math.abs(X-(this.getX()+1)),2));
				}else{//there is a wall
					if (ghostState){
						rDist = DiscouragingSmallNumber; //set to small number so this will not be chosen as a possible path
					}else{ //NORMAL
						rDist = DiscouragingLargeNumber; //set to large number so when normal, path won't be chosen
					}
				}
				break;
			case 2://DOWN
				if ((map.isWall(this.getX(), this.getY()+1)) == false){
					//not a wall, you can move down
					dDist = Math.sqrt(Math.pow(Math.abs(Y-(this.getY()+1)),2) + Math.pow(Math.abs(X-this.getX()),2));
				}else{//there is a wall
					if (ghostState){
						dDist = DiscouragingSmallNumber; //set to small number so this will not be chosen as a possible path
					}else{ //NORMAL
						dDist = DiscouragingLargeNumber; //set to large number so when normal, path won't be chosen
					}
				}
				break;
			case 3://LEFT
				if ((map.isWall(this.getX()-1, this.getY())) == false){
					//not a wall, you can move left
					lDist = Math.sqrt(Math.pow(Math.abs(Y-this.getY()),2) + Math.pow(Math.abs(X-(this.getX()-1)),2));
				}else{//there is a wall
					if (ghostState){
						lDist = DiscouragingSmallNumber; //set to small number so this will not be chosen as a possible path
					}else{ //NORMAL
						lDist = DiscouragingLargeNumber; //set to large number so when normal, path won't be chosen
					}
				}
				break;
			}
		}
		// The following switch statement determines the direction you were going, and makes the opposite
		// direction less desirable, since ghosts should not spontaneously change direction
		// The desirability of a direction is based on the state of pacman, since we use large or small
		// numbers to make a direction more or less desirable based on whether pacman is chasing them or
		// running away from them
		switch(this.getDirection()){
		case UP:					
			if (ghostState){
				dDist = LargerDiscouragingLargeNumber; 
			}else{ //NORMAL
				dDist = SmallerDiscouragingLargeNumber; }			 
			break;			 
		case RIGHT:
			if (ghostState){
				lDist = LargerDiscouragingLargeNumber;
			}else{ //NORMAL
				lDist = SmallerDiscouragingLargeNumber;}
			break;
		case DOWN:
			if (ghostState){
				uDist = LargerDiscouragingLargeNumber;
			}else{ //NORMAL
				uDist = SmallerDiscouragingLargeNumber;}
			break;
		case LEFT:
			if (ghostState){
				rDist = LargerDiscouragingLargeNumber;
			}else{ //NORMAL
				rDist = SmallerDiscouragingLargeNumber;}
			break;
		}
		// Here, we're adding a small amount of randomness to the distances (a number between 0 and 1)
		// The small amount of randomness prevents situations where distances are exactly the same (hopefully)
		// but is a small enough change to not cause changes in what would be considered the right path to
		// take
		uDist += Math.random()*difficulty;
		rDist += Math.random()*difficulty;
		dDist += Math.random()*difficulty;
		lDist += Math.random()*difficulty;
		
		// By now, the opposite direction the ghost was travelling is less desirable than other paths, but
		// paths that would lead to walls are the least desirable
		if(ghostState){//running away
			//go for the farthest away path
			if(uDist > rDist && uDist > dDist && uDist > lDist ){
				this.setDirection(Direction.UP);
			}else if(rDist > uDist && rDist > dDist && rDist > lDist){
				this.setDirection(Direction.RIGHT);
			}else if(dDist > uDist && dDist > rDist && dDist > lDist){
				this.setDirection(Direction.DOWN);
			}else if(lDist > uDist && lDist > rDist && lDist > dDist){
				this.setDirection(Direction.LEFT);
			}
		}else{//chasing
			//decide which distance is the shortest to chase
			if(uDist < rDist && uDist < dDist && uDist < lDist ){
				this.setDirection(Direction.UP);
			}else if(rDist < uDist && rDist < dDist && rDist < lDist){
				this.setDirection(Direction.RIGHT);
			}else if(dDist < uDist && dDist < rDist && dDist < lDist){
				this.setDirection(Direction.DOWN);
			}else if(lDist < uDist && lDist < rDist && lDist < dDist){
				this.setDirection(Direction.LEFT);
			}
		}
		this.updateLocation(map); //actually move the ghosts
	return;
	}
	
}