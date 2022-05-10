
import java.util.ArrayList;


public class HeadHunter extends Player implements GameListener {
	public HeadHunter(int x, int y){
		super(x, y);
		setImage("SURVIVAL/evil.png");
	}
	/**
	 * Creates new headhunter and associates an image with it
	 * @param x
	 * @param y
	 * @param direction
	 * @param numOfLives
	 */
	public HeadHunter(int x, int y, Direction direction, int numOfLives) {
		super(x,y,direction,numOfLives);
		setImage("SURVIVAL/evil.png");
	}
	/**
	 * Spawns a new headhunter at location (x,y)
	 */
	@Override
	public void spawn(Map map) {
		map.addMappable(this);
	}
	/**
	 * Kills a headhunter. This should NEVER happen.
	 */
	@Override
	public void die(Map map) {
	}

	@Override
	public void onEvent(GameEvent e) {
		// TODO Auto-generated method stub
		/**
		 * HeadHunter always chases you. Always.
		 */
		//MOVEMENT
		if(e.getSource().equals("HeadHunterMovement") && e.getGameValue() instanceof SurvivalGame) { //if mouse movement has occurred
			SurvivalGame tempGame = (SurvivalGame)e.getGameValue(); //create a temp variable of the game
			ArrayList<Mappable> mappableArray = tempGame.getMap().getMappable(getX(),getY());
			for (int i=mappableArray.size()-1; i >= 0; i--) {
				if(mappableArray.get(i).getClass().getName() == "Survivor") {
					((Survivor)mappableArray.get(i)).die(tempGame.getMap());
				}
			}
		}//end of Movement
	}
	/**
	 * This function dictates the movement of the mice.
	 * The  mice will constantly try to chase you down, and their movement is decided on which path is the shortest distance
	 * @author Colin MacDougall
	 * @param X
	 * @param Y
	 * @param map
	 */
	public void moveHeadHunter(int X, int Y, Map map){
		//for the purpose of this method, 0 is up, 1 is right, 2 is down, 3 is left
		double uDist = 5;
		double rDist = 5;
		double dDist = 5;
		double lDist = 5;
		int numberofHeadHunter = 4;
		int randomfactor = 2; //this is a bit of a difficulty meter, set this higher to make mice more stupid
		int LargeDiscouragingNumber = 1000;
		int SlightlyLessLargeDiscouragingNumber = 990;
		//find the distance the mice is from hero for each possible move
		for (int i = 0; i < numberofHeadHunter; i++){
			switch(i){
			case 0://UP
				if ((map.isWall(this.getX(), this.getY()-1)) == false){
					//not a wall, you can move up
					uDist = Math.sqrt(Math.pow(Math.abs(Y-(this.getY()-1)),2) + Math.pow(Math.abs(X-this.getX()),2));
				}else{//there is a wall
					uDist = LargeDiscouragingNumber; //set to large number so when normal, path won't be chosen
				}
				break;
			case 1://RIGHT
				if ((map.isWall(this.getX()+1, this.getY())) == false){
					//not a wall, you can move right
					rDist = Math.sqrt(Math.pow(Math.abs(Y-this.getY()),2) + Math.pow(Math.abs(X-(this.getX()+1)),2));
				}else{//there is a wall
					rDist = LargeDiscouragingNumber; //set to large number so when normal, path won't be chosen
				}
				break;
			case 2://DOWN
				if ((map.isWall(this.getX(), this.getY()+1)) == false){
					//not a wall, you can move down
					dDist = Math.sqrt(Math.pow(Math.abs(Y-(this.getY()+1)),2) + Math.pow(Math.abs(X-this.getX()),2));
				}else{//there is a wall
					dDist = LargeDiscouragingNumber; //set to large number so when normal, path won't be chosen
				}
				break;
			case 3://LEFT
				if ((map.isWall(this.getX()-1, this.getY())) == false){
					//not a wall, you can move left
					lDist = Math.sqrt(Math.pow(Math.abs(Y-this.getY()),2) + Math.pow(Math.abs(X-(this.getX()-1)),2));
				}else{//there is a wall
					lDist = LargeDiscouragingNumber; //set to large number so when normal, path won't be chosen
				}
				break;
			}
		}
		// The following switch statement determines the direction you were going, and makes the opposite
		// direction less desirable, since mice should not spontaneously change direction
		// The desirability of a direction is based on the state of the hero, since we use large or small
		// numbers to make a direction more or less desirable based on whether hero is chasing them or
		// running away from them
		switch(this.getDirection()){
		case UP:
			dDist = SlightlyLessLargeDiscouragingNumber; 			 
			break;			 
		case RIGHT:
			lDist = SlightlyLessLargeDiscouragingNumber;
			break;
		case DOWN:
			uDist = SlightlyLessLargeDiscouragingNumber;
			break;
		case LEFT:
			rDist = SlightlyLessLargeDiscouragingNumber;
			break;
		}
		// Here, we're adding a small amount of randomness to the distances (a number between 0 and 1)
		// The small amount of randomness prevents situations where distances are exactly the same (hopefully)
		// but is a small enough change to not cause changes in what would be considered the right path to
		// take
		uDist += (Math.random()*randomfactor);
		rDist += (Math.random()*randomfactor);
		dDist += (Math.random()*randomfactor);
		lDist += (Math.random()*randomfactor);
		
		// By now, the opposite direction the ghost was travelling is less desirable than other paths, but
		// paths that would lead to walls are the least desirable
		if(uDist < rDist && uDist < dDist && uDist < lDist ){
			this.setDirection(Direction.UP);
		}else if(rDist < uDist && rDist < dDist && rDist < lDist){
			this.setDirection(Direction.RIGHT);
		}else if(dDist < uDist && dDist < rDist && dDist < lDist){
			this.setDirection(Direction.DOWN);
		}else if(lDist < uDist && lDist < rDist && lDist < dDist){
			this.setDirection(Direction.LEFT);
		}
		this.updateLocation(map); //actually move the mice
	return;
	}
}
