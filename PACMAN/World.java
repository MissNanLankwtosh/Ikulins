
public class World extends Map implements GameListener {

	public World(int Xsize, int Ysize) {
		super(Xsize, Ysize);
	}

	public World(String fileName) {
		super(fileName);
	}
	
	public void display() {
		String s;
		for (int x=0; x<xSize; x++) {
			s = "";
			for (int y=0; y<ySize; y++) {
				if(isEmpty(x, y)) s = s + " ";
				else if(isWall(x, y)) s = s + "X";
				else if(getMappable(x,y) == null) return;
				else if(getMappable(x,y) instanceof Pacman) s = s + "P";
				else if(getMappable(x,y) instanceof Ghost) s = s + "G";
				else if(getMappable(x,y) instanceof BigPill) s = s + "o";
				else if(getMappable(x,y) instanceof LittlePill) s = s + ".";
			}
			System.out.println(s);
		}
	}

	@Override
	public void onEvent(GameEvent e) {
		if(e.getMappable() instanceof Pacman) { //PACMAN MOVEMENT
			if(e.getSource().equals("pUP")) {
				
			}
			if(e.getSource().equals("pLEFT")) {
				
			}
			if(e.getSource().equals("pDOWN")) {
				
			}
			if(e.getSource().equals("pRIGHT")) {
				
			}
		}
	}
	
	
}
