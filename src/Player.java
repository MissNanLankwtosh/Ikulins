
public abstract class Player extends Mappable {
	private Direction direction;
	private int numOflives;
	/*
	 * General purpose constructor for Player. Sets direction to Direction.LEFT and numOfLives to 1.
	 * @param x Desired X position of Player
	 * @param y Desired Y position of Player
	 */
	public Player(int x, int y){
		super(x, y);
		this.direction = Direction.LEFT;
		this.numOflives = 1;
	}
	/*
	 * A more specific constructor for Player. Allows direction and numOfLives to be set to desired values.
	 * @param x Desired X position of Player
	 * @param y Desired Y position of Player
	 * @param direction Desired direction of player ( UP, DOWN, LEFT, RIGHT )
	 * @param numOflives desired number of lives
	 */
	public Player(int x, int y, Direction direction, int numOflives) {
		super(x, y);
		this.direction = direction;
		this.numOflives = numOflives;
		
	}
	
	/*
	 * @return Current direction of Player
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/*
	 * Change the current direction of Player
	 * @param direction Either UP, DOWN, LEFT or RIGHT
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/*
	 * @return Number of lives of the Player
	 */
	public int getNumOflives() {
		return numOflives;
	}
	
	/*
	 * Change the current number of lives of the Player
	 * @param numOflives number of lives desired.
	 */
	public void setNumOflives(int numOflives) {
		this.numOflives = numOflives;
	}
	/*
	 * Moves the Player in its current direction
	 * @param Map current map the Player is on
	 * @return true if the move was successful, false otherwise.
	 */
	public boolean move(Map map){
		switch(this.direction){
			case UP:
				if(map.isEmpty(this.x, this.y - 1)){
					map.removeMappable(this.x, this.y);
					this.y -= 1;
					return map.addMappable(this);
				}
				return false;
			case LEFT:
				if(map.isEmpty(this.x - 1, this.y)){
					map.removeMappable(this.x, this.y);
					this.x -= 1;
					return map.addMappable(this);
				}
				return false;
			case DOWN:
				if(map.isEmpty(this.x, this.y + 1)){
					map.removeMappable(this.x, this.y);
					this.y += 1;
					return map.addMappable(this);
				}
				return false;
			case RIGHT:
				if(map.isEmpty(this.x + 1, this.y)){
					map.removeMappable(this.x, this.y);
					this.x += 1;
					return map.addMappable(this);
				}
				return false;
		}
		//Random return to pacify Eclipse
		return false;
	}

	public abstract void spawn();
	
	/*
	 * Decrements current lives by one.
	 */
	public void die(){
		this.setNumOflives(this.numOflives -1);
	}
}
