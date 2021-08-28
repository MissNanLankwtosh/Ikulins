import java.util.EventObject;


public class GameEvent extends EventObject {
	
	protected static final long serialVersionUID = 1L;
	protected Object m;
	/**
	 * Creates a GameEvent
	 * @param source object that is creating the Event
	 * @param m object that the event affects
	 */
	public GameEvent(Object source, Object m) {
		super(source);
		this.m = m;
		
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return returns affected Object
	 */
	public Object getGameValue(){
		return m;
	}

}
