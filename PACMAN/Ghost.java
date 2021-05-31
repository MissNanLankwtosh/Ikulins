
public class Ghost extends Player {
	private int mode;

	public Ghost(int x, int y) {
		super(x, y);
		//need to set mode here when i know what it is
	}
	
	public void die() {
		//???
	}
	
	public void spawn() {
		//???
	}
	

	@Override
	public void moveDown() {
		setY(getY()-1);		
	}

	@Override
	public void moveLeft() {
		setX(getX()-1);
	}

	@Override
	public void moveRight() {
		setX(getX()+1);
	}

	@Override
	public void moveUp() {
		setY(getY()+1);
	}
	
	@Override
	public void onEvent(GameEvent e) {
		// TODO Auto-generated method stub
	}

}
