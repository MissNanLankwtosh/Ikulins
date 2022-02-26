/**
 * 
 * Exit is the item that the player must try to reach. Upon reaching it, the game is won, and is exited.
 * Exit has a constructor which simply places it on the map and associates an image with the Exit.
 */
public class Exit extends Item {
	public Exit(int x, int y) {
		super(x, y);
		setImage("MOUSELAND/ladder.png");
	}

	@Override
	public void action(Object obj) {
		// TODO Auto-generated method stub

	}

}
