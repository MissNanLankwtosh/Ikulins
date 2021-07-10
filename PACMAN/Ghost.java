
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
	public void moveDown(Map map) {
		map.removeMappable(x,y);
		setY(getY()-1);		
		map.addMappable(this);
	}

	@Override
	public void moveLeft(Map map) {
		map.removeMappable(x,y);
		setX(getX()-1);
		map.addMappable(this);
	}

	@Override
	public void moveRight(Map map) {
		map.removeMappable(x,y);
		setX(getX()+1);
		map.addMappable(this);
	}

	@Override
	public void moveUp(Map map) {
		map.removeMappable(x,y);
		setY(getY()+1);
		map.addMappable(this);
	}
	
	@Override
	public void onEvent(GameEvent e) {
		// TODO Auto-generated method stub
	}

}
