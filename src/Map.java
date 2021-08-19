import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

public abstract class Map {
	protected Game game;
	private OnMap map[][];
	private Hashtable<String,ArrayList<Mappable>> mappables;
	private int xSize, ySize;
	
	/**
	 * Constructs an empty map of size Xsize by Ysize.
	 * @param Xsize the number of X tiles on the map.
	 * @param Ysize the number of Y tiles on the map.
	 * @param Game i unno bout this one, alex wanna add something here?
	 */
	public Map(int Xsize, int Ysize, Game game){
		this.xSize = Xsize;
		this.ySize = Ysize;
		this.map = new OnMap[Xsize][Ysize];
		for (int x=0; x<xSize; x++) {
			for (int y=0; y<ySize; y++) {
				map[x][y] = OnMap.EMPTY;
			}
		}
		this.mappables = new Hashtable<String,ArrayList<Mappable>>();
		this.game = game;
	}
	
	/**
	 * Constructs an empty map from a file.
	 * <p>
	 * See wiki page for full details on thie method
	 * @param filename name of file containing map layout
	 * @param Game i unno bout this one, alex wanna add something here?
	 */
	public Map(String fileName, Game game) {
		this.mappables = new Hashtable<String,ArrayList<Mappable>>();
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			String s = in.readLine();
			String nums[] = s.split(",");
			this.xSize = sToInt(nums[0]);
			this.ySize = sToInt(nums[1]);
			this.map = new OnMap[xSize][ySize];
			this.game = game;
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

	/**
	 * Adds a Mappable Object to the Map.
	 * <p>
	 * Adds a Mappable to an empty tile on the map and then marks that tile as MAPPABLE. 
	 * If there already is a Mappable in the position, it is still added and all current
	 * Mappables at that tile will be put into an ArrayList.
	 * @param m The Mappable that needs to be added.
	 * @return true if the add was successful, false otherwise.
	 */
	public boolean addMappable(Mappable m){
		
		if (!isWall(m.getX(),m.getY())){
			map[m.getX()][m.getY()] = OnMap.MAPPABLE;
			ArrayList<Mappable> onDmap = mappables.get((m.getX() + "," + m.getY()));
			if( onDmap == null){
				onDmap = new ArrayList<Mappable>();
				mappables.put(m.toString(), onDmap);
			}
			onDmap.add(0,m);
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a wall in the specified tile.
	 * @param x the X position of the desired tile
	 * @param y the Y position of the desired tile
	 * @return true if add was successful, false if it failed.
	 */
	public boolean addWall(int x, int y){
		
		if(isEmpty(x,y)){
			map[x][y] = OnMap.WALL;
			return true;
		}
		return false;
	}
	
	/**
	 * Checks to see if tile is empty.
	 * @param x the X position of the desired tile
	 * @param y the Y position of the desired tile
	 * @return true if the tile is empty, false otherwise or if x and y are out of bounds
	 */
	public boolean isEmpty(int x, int y){
		return ((!isOutOfBounds(x,y))&&(map[x][y] == OnMap.EMPTY));
	}
	
	/**
	 * Checks to see if tile is a wall.
	 * @param x the X position of the desired tile
	 * @param y the Y position of the desired tile
	 * @return true if the tile is a wall, false otherwise or if x and y are out of bounds
	 */
	public boolean isWall(int x, int y){
		return ((!isOutOfBounds(x,y)) && (map[x][y] == OnMap.WALL));
	}
	
	/**
	 * Checks to see if desired tile is within the map.
	 * @param x the X position of the desired tile
	 * @param y the Y position of the desired tile
	 * @return true if such a tile exits, false otherwise
	 */
	public boolean isOutOfBounds(int x, int y){
		return (x >= this.xSize || y >= this.ySize || x < 0 || y < 0);
	}
	
	/**
	 * Attempts to remove a Mappable from the Map
	 * @param x the X position of the desired tile
	 * @param y the Y position of the desired tile
	 * @param pos position of the Mappable in the tile's ArrayList
	 * @return true if a Mappable was removed, false otherwise
	 */
	public boolean removeMappable(int x, int y, int pos){

		//Checks to see if anything is in the spot
		if(isEmpty(x,y) || isWall(x,y)){
			return false;
		}
		ArrayList<Mappable> onDmap = mappables.get((x + "," + y));
		//ArrayList will not be null otherwise it would not have made this far in the code.
		onDmap.remove(pos);
		if(onDmap.size() == 0){
			/**
			 * remove comment if you want to remove the empty list from the HashTable
			 *mappables.remove((x + "," + y));
			 */
			map[x][y] = OnMap.EMPTY;
		}
		return true;
	}
	
	/**
	 * Attempts to remove a Mappable from the Map
	 * @param x the X position of the desired tile
	 * @param y the Y position of the desired tile
	 * @return true if a Mappable was removed, false otherwise
	 */
	public boolean removeMappable(int x, int y){
		return removeMappable(x,y,0);
	}
	
	/**
	 * Attempts to remove a wall
	 * @param x the X position of the desired tile
	 * @param y the Y position of the desired tile
	 * @return true if wall was removed, false otherwise
	 */
	public boolean removeWall(int x, int y){
		//Checks to see if there is a wall
		if(!isWall(x,y)){
			return false;
		}
		map[x][y] = OnMap.EMPTY;
		return true;
	}
	
	
	/**
	 * Attempts to retrieve a Mappables from a desired tile
	 * @param x the X position of the desired tile
	 * @param y the Y position of the desired tile
	 * @return The desired Mappable or null if Mappable is present at that location
	 */
	public ArrayList<Mappable> getMappable(int x, int y) {
		if (isEmpty(x,y) && isWall(x,y)) return null;
		return ((ArrayList<Mappable>)mappables.get(x + "," + y));
	}
	
	/**
	 * Retrieves Map's current X size
	 * @return Map's Xsize variable
	 */
	
	public int getX() {
		return xSize;
	}
	
	/**
	 * Retrieves Map's current Y size
	 * @return Map's Ysize variable
	 */
	
	public int getY() {
		return ySize;
	}

}
