import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is the class representing the frogger player.
 * 
 * @author Colin MacDougall unless otherwise specified
 */
public class Frogger extends Player implements GameListener, ActionListener{
	
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
		setImage("PACMAN/pacmanimg3.png");
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
			
			for(int i=mappables.size()-1; i>=0; i--) { //go through array to check if pacman is on a LittlePillItem
				Mappable tempMappable = mappables.get(i);
				if(tempMappable instanceof Car) {
					die(tempGame.getMap());
					spawn(tempGame.getMap());
				}
				/*  This is going to become the check for the Flower
				 * 
				else if(tempMappable instanceof BigPillItem) {
					tempGame.getMap().removeMappable(getX(),getY(),i);
				}*/
			}

		}//end of Movement
	}//end of  onEvent()
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
	
}
