import java.util.EventObject;


public class GameEvent extends EventObject {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected Object m;
	public GameEvent(Object source, Object m) {
		super(source);
		this.m = m;
		
		// TODO Auto-generated constructor stub
	}
	public Object getGameValue(){
		return m;
	}

}
