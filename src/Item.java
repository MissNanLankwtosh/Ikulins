
public abstract class Item extends Mappable {

	/**
	 * Creates an Item at position x,y
	 * @param x X coordinate of Item
	 * @param y Y coordinate of Item
	 */
	public Item(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Abstract method which forces items to have some effect
	 * @param obj an object the item affects
	 */
	public abstract void action(Object obj);
}
