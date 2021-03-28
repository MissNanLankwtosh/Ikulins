import java.util.EventObject;


public class GameEvent extends EventObject {
	private Mappable m;
	public GameEvent(Object source, Mappable m) {
		super(source);
		this.m = m;
		
		// TODO Auto-generated constructor stub
	}
	public Mappable getMappable(){
		return m;
	}

}
