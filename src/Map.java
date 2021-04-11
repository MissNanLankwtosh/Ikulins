import java.util.Dictionary;
import java.util.Hashtable;


public class Map {
	private int map[][];
	private Hashtable<String,Mappable> mappables;
	
	public Map(int Xsize, int Ysize){
		map = new int[Xsize][Ysize];
	}
	
	public boolean addMappable(Mappable m){
		if (isEmpty(m.getX(),m.getY())){
			map[m.getX()][m.getY()] = 1;
			mappables.put(m.toString(), m);
			return true;
		}
		
		return false;
	}
	
	public boolean isEmpty(int x, int y){
		if (map[x][y] == 0){
			return true;
		}
		return false;
	}
	public boolean isWall(int x, int y){
		if( map[x][y] == -1){
			return true;
		}
		return false;
	}
	public boolean removeMappable(int x, int y){
		//Checks to see if anything is in the spot
		if(isEmpty(x,y) && !isWall(x,y)){
			return false;
		}
		mappables.remove((x + "," + y));
		return true;
	}
	
}
