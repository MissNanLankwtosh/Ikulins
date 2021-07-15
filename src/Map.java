import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

public abstract class Map {
	protected Game game;
	protected OnMap map[][];
	protected Hashtable<String,Mappable> mappables;
	protected int xSize, ySize;
	
	public Map(int Xsize, int Ysize, Game game){
		this.xSize = Xsize;
		this.ySize = Ysize;
		this.map = new OnMap[Xsize][Ysize];
		for (int x=0; x<xSize; x++) {
			for (int y=0; y<ySize; y++) {
				map[x][y] = OnMap.EMPTY;
			}
		}
		this.mappables = new Hashtable<String,Mappable>();
	}
	
	public Map(String fileName, Game game) {
		this.mappables = new Hashtable<String,Mappable>();
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			String s = in.readLine();
			String nums[] = s.split(",");
			this.xSize = sToInt(nums[0]);
			this.ySize = sToInt(nums[1]);
			this.map = new OnMap[xSize][ySize];
			
			int y = 0;
			while( (s = in.readLine()) != null){
				nums = s.split(",");
				for(int x = 0; x < this.xSize; x++){
					if(nums[x] == null){
						this.map[x][y] = OnMap.EMPTY;
					}else{
						this.map[x][y] = (sToInt(nums[x]) > 0) ? (OnMap.WALL):(OnMap.EMPTY);
					}
				}
				y++;
				if( y >= this.ySize){
					break;
				}
			}
			if( y < this.ySize){
				for(; y < this.ySize; y++){
					for( int x = 0; x < this.xSize; x++){
						this.map[x][y] = OnMap.EMPTY;
					}
				}
			}
			
		}catch(Exception e){
			System.out.println("Error while reading file: " + e.getMessage());
			System.exit(-1);
		}
	}
	
	private int sToInt(String string) {
		int i = -1; //some arbitrary number
		try{
			
			i = Integer.parseInt(string);
		}catch(NumberFormatException e){
			
			System.out.println("Invalid number: " + e.getMessage());
			System.exit(-1);
			
		}
		return i;
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
		return ((!isOutOfBounds(x,y))&&(map[x][y] == OnMap.EMPTY));
	}
	
	public boolean isWall(int x, int y){
		return ((!isOutOfBounds(x,y)) && (map[x][y] == OnMap.WALL));
	}
	
	public boolean isOutOfBounds(int x, int y){
		return (x >= this.xSize || y >= this.ySize || x < 0 || y < 0);
	}
	
	public boolean removeMappable(int x, int y){

		//Checks to see if anything is in the spot
		if(isEmpty(x,y) || isWall(x,y)){
			return false;
		}
		mappables.remove((x + "," + y));
		map[x][y] = OnMap.EMPTY;
		return true;
	}
	
	public boolean removeWall(int x, int y){
		//Checks to see if there is a wall
		if(!isWall(x,y)){
			return false;
		}
		map[x][y] = OnMap.EMPTY;
		return true;
	}
	
	public Mappable getMappable(int x, int y) {
		if (isEmpty(x,y) && isWall(x,y)) return null;
		return mappables.get(x + "," + y);
	}
	
	public int getX() {
		return xSize;
	}
	
	public int getY() {
		return ySize;
	}
	
}
