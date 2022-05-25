import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is the class representing the frogger player.
 * 
 * @author Colin MacDougall unless otherwise specified
 */
public class Frogger extends Player implements GameListener, ActionListener{
	
	private boolean hasFlower = false;
	
	public Frogger(int x, int y) {
		super(x, y);
	}
	/**
	 * Initializes Frogger and associates the frog image to the player
	 * @param x
	 * @param y
	 * @param direction
	 * @param numOfLives
	 * @param game 
	 */
	public Frogger(int x, int y, Direction direction, int numOfLives) {
		super(x,y,direction,numOfLives);
		setImage("FROGGER/frog1.png");
	}

	/**
	 * This method spawns Frogger on the Map. Frogger will always spawn at location (9,9) on the Map.
	 */
	public void spawn(Map map) {
		setX(9);
		setY(9);
		map.addMappable(this);
	}
	/**
	 * Kills Frogger. Removes  existance on the map.
	 * @author Alexander Clelland
	 */
	public void die(Map map) {
		setNumOflives(getNumOflives()-1); //decrement frogger lives
		//the following code finds the cell on the map and accesses the list at that cell to see if there
		//are multiple objects in the same spot on the map.  This searches through those multiple objects
		//for the frogger object to remove it.
        ArrayList<Mappable> mappableArray = map.getMappable(getX(),getY());
        if (mappableArray == null) return;
        for (int i=mappableArray.size()-1; i >= 0; i--) {
                if(mappableArray.get(i) == this) {
                        map.removeMappable(getX(),getY(),i);
                        break;
                }
        }
		
	}

	@Override
	/**
	 * This deals with movement affecting frogger in some way.
	 * If frogger moves, he can either collide with a wall, or a car
	 * @case car, must kill Frogger
	 * 
	 * Credits to Alex for searching through the mapables
	 */
	public void onEvent(GameEvent e) {
		
		//MOVEMENT
		if(e.getSource().equals("movement") && e.getGameValue() instanceof FroggerGame) { //if movement has occured
			FroggerGame tempGame = (FroggerGame)e.getGameValue(); //create a temp variable of the game
			
			ArrayList<Mappable> mappables = tempGame.getMap().getMappable(getX(),getY());
			
			for(int i=mappables.size()-1; i>=0; i--) { //go through array to check if Frogger is hit by a Car
				Mappable tempMappable = mappables.get(i);
				if(tempMappable instanceof Car) {
					die(tempGame.getMap());
					System.out.println("You have "+this.getNumOflives()+" lives remaining.");
					if(hasFlower){
						System.out.println("You lost your flower!");}
					hasFlower = false;
					spawn(tempGame.getMap());
					break; //this is here as a temporary measure to prevent multiple deaths to the same car
						   //in theory this shouln't happen cause there should only be one car per block, but
						   //it has been happening, so this is a work around.  if we solve the underyling problem
						   //of multiple cars on the same block, this will likely be unneeded
				}
				else if(tempMappable instanceof Flower) {
					if(!hasFlower){
						System.out.println("You got a flower from the flower patch!");
						hasFlower = true;
					}else{
						System.out.println("You already have a flower!");
					}
				}else if(tempMappable instanceof Home){
					if(hasFlower){
						tempGame.winner();
					}
				}
			}
			switch(this.getDirection()){ //change frogger to be looking the right direction
			case UP:
				setImage("FROGGER/frog1.png"); break;
			case RIGHT:
				setImage("FROGGER/frog2.png"); break;
			case DOWN:
				setImage("FROGGER/frog3.png"); break;
			case LEFT:
				setImage("FROGGER/frog4.png"); break;
			}
		}//end of Movement
	}//end of  onEvent()
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
	
}
