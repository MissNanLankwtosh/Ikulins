
public abstract class Mappable implements GameListener {
	private int x;
	private int y;
	
	public Mappable(int x ,int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString(){
		return (x + "," + y);
	}
	
	@Override
	public void onEvent(GameEvent e) {
		//code here not needed, Mappable has this blank by default, if you care you must write it in your code
	}
}
