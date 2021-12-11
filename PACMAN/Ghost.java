
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
		
	}
	
	@Override
	public void die(Map map) {
		
	}

	@Override
	public void onEvent(GameEvent e) {
		// TODO Auto-generated method stub

	}
	/**
	 * This function dictates the movement of the ghosts.
	 * When pacman is normal mode, the ghosts viciously chase you down, deciding every move which square
	 * will lead to the shortest distance to you, and take that path.
	 * If pacman is BEAST MODE, the ghosts will run away from you, taking the furthest path away from
	 * you by deciding this each movement, for every possible square
	 * @param X
	 * @param Y
	 * @param mode
	 * @param map
	 */
	public void moveGhosts(int X, int Y, PacmanState mode, Map map){
		//for the purpose of this method, 0 is up, 1 is right, 2 is down, 3 is left
		double uDist = 0;
		double rDist = 0;
		double dDist = 0;
		double lDist = 0;

		//find the distance the ghost is from pacman for each possible move
		for (int i = 0; i < 4; i++){
			switch(i){
			case 0://UP
				if ((map.isWall(this.getX(), this.getY()-1)) == false){
					//not a wall, you can move up
					uDist = Math.sqrt(Math.pow(Math.abs(Y-(this.getY()-1)),2) + Math.pow(Math.abs(X-this.getX()),2));
					}else{//there is a wall
						if (mode == PacmanState.BEASTMODE){
							uDist = 0; //set to small number so this will not be chosen as a possible path
						}else{ //NORMAL
							uDist = 1000; //set to large number so when normal, path won't be chosen
						}
					}
				break;
			case 1://RIGHT
				if ((map.isWall(this.getX()+1, this.getY())) == false){
					//not a wall, you can move right
					rDist = Math.sqrt(Math.pow(Math.abs(Y-this.getY()),2) + Math.pow(Math.abs(X-(this.getX()+1)),2));
				}else{//there is a wall
					if (mode == PacmanState.BEASTMODE){
						rDist = 0; //set to small number so this will not be chosen as a possible path
					}else{ //NORMAL
						rDist = 1000; //set to large number so when normal, path won't be chosen
					}
				}
				break;
			case 2://DOWN
				if ((map.isWall(this.getX(), this.getY()+1)) == false){
					//not a wall, you can move down
					dDist = Math.sqrt(Math.pow(Math.abs(Y-(this.getY()+1)),2) + Math.pow(Math.abs(X-this.getX()),2));
				}else{//there is a wall
					if (mode == PacmanState.BEASTMODE){
						dDist = 0; //set to small number so this will not be chosen as a possible path
					}else{ //NORMAL
						dDist = 1000; //set to large number so when normal, path won't be chosen
					}
				}
				break;
			case 3://LEFT
				if ((map.isWall(this.getX()-1, this.getY())) == false){
					//not a wall, you can move left
					lDist = Math.sqrt(Math.pow(Math.abs(Y-this.getY()),2) + Math.pow(Math.abs(X-(this.getX()-1)),2));
				}else{//there is a wall
					if (mode == PacmanState.BEASTMODE){
						lDist = 0; //set to small number so this will not be chosen as a possible path
					}else{ //NORMAL
						lDist = 1000; //set to large number so when normal, path won't be chosen
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
			if (mode == PacmanState.BEASTMODE){
				dDist = 1; 
			}else{ //NORMAL
				dDist = 990; }			 
			break;			 
		case RIGHT:
			if (mode == PacmanState.BEASTMODE){
				lDist = 1;
			}else{ //NORMAL
				lDist = 990;}
			break;
		case DOWN:
			if (mode == PacmanState.BEASTMODE){
				uDist = 1;
			}else{ //NORMAL
				uDist = 990;}
			break;
		case LEFT:
			if (mode == PacmanState.BEASTMODE){
				rDist = 1;
			}else{ //NORMAL
				rDist = 990;}
			break;
		}
		// By now, the opposite direction the ghost was travelling is less desirable than other paths, but
		// paths that would lead to walls are the least desirable
		if(mode == PacmanState.BEASTMODE){//running away
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