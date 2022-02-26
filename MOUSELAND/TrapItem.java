/**
 * 
 * Trap item is an item that is used to trap the mice.
 * An image is set up that is associated with the trap.
 */
public class TrapItem extends Item {

	public TrapItem(int x, int y) {
		super(x, y);
		setImage("MOUSELAND/mousetrap.png");
		
	}

	@Override
	public void action(Object obj) {
		

	}

}
