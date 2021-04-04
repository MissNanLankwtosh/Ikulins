import java.util.ArrayList;


public abstract class Game {
	ArrayList<GameListener> GameList;
	public Game(){
		GameList = new ArrayList<GameListener>();
	}
	public void addListener(GameListener g){
		GameList.add(g);
	}
	public void notify(GameEvent e){
		for(GameListener listener:GameList){
			listener.onEvent(e);
		}
	}
}
