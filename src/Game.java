import java.util.ArrayList;


public abstract class Game {
	private ArrayList<GameListener> GameList;
	private Map map;
	/**
	 * Creates a new game
	 */
	public Game(){
		GameList = new ArrayList<GameListener>();
	}
	/**
	 * @return Current Map of game
	 */
	public Map getMap() {
		return map;
	}
	/**
	 * Allows you to change current map of game
	 * @param map new map that you want game to use
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	/**
	 * Lets you add listeners for events
	 * @param g Listener to be added
	 */
	public void addListener(GameListener g){
		GameList.add(g);
	}
	/**
	 * Notifies all listeners of Event
	 * @param e Event that needs to be sent to listeners
	 */
	public void notify(GameEvent e){
		for(GameListener listener:GameList){
			listener.onEvent(e);
		}
	}
}
