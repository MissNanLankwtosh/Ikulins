import java.util.Hashtable;

public class Map {
	private OnMap map[][];
	private Hashtable<String,Mappable> mappables;
	

	public Map(int Xsize, int Ysize){
		map = new OnMap[Xsize][Ysize];
	}
	
	public boolean addMappable(Mappable m){
		if (isEmpty(m.getX(),m.getY())){
			map[m.getX()][m.getY()] = OnMap.MAPPABLE;
			mappables.put(m.toString(), m);
			return true;
		}
		
		return false;
	}
	
	public boolean addWall(int x, int y){
		if(isEmpty(x,y)){
			map[x][y] = OnMap.WALL;
			return true;
		}
		return false;
	}
	
	public boolean isEmpty(int x, int y){
		return map[x][y] == OnMap.EMPTY;
	}
	
	public boolean isWall(int x, int y){
		return map[x][y] == OnMap.WALL;
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
