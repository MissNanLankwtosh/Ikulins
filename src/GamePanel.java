import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;


/**
 * @author  imran
 */
public abstract class GamePanel extends JPanel{

	private static final long serialVersionUID = -2458261363554188485L;
	/**
	 * @uml.property  name="game"
	 * @uml.associationEnd  
	 */
	private Game game;
	private Color mazeColor;
	private Color bgColor;
	private Dimension d;
	private final int defaultTileSize = 19; //in pixels
	
	/**
	 * Creates a game panel that can be added to a JFrame for a game
	 * @param g instance of game.
	 */
	public GamePanel(Game g){
		this.game = g;
		addKeyListener(new KeyPressHandler());
		setFocusable(true);
		d = new Dimension(this.game.getMap().getX() * defaultTileSize, this.game.getMap().getY() * defaultTileSize);
		setSize(d);
		mazeColor = new Color(0,255,0);
		bgColor = new Color(0,0,0);
		
		setBackground(bgColor);
		setDoubleBuffered(true);


	}
	/**
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#addNotify()
	 */
	public void addNotify() {
        super.addNotify();
        GameInit();
    }

	/**
	 * Performs Game initializations.
	 */
	public abstract void GameInit();
	
	/**
	 * @param game
	 * @uml.property  name="game"
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Changes current background colour
	 * @param color  New color for background
	 * @uml.property  name="bgColor"
	 */
	public void setBgColor( Color color){
		this.bgColor = color;
	}
	
	/**
	 * Changes current wall colour
	 * @param color  New color for walls in the map
	 * @uml.property  name="mazeColor"
	 */
	public void setMazeColor( Color color){
		this.mazeColor = color;
	}
	
	/**
	 * @return  returns instance of current game
	 * @uml.property  name="game"
	 */
	public Game getGame() {
		return game;
	}
	
	/**
	 * Provides a simple draw method
	 * @param image Image to be drawn
	 * @param y Y co-ordinate on canvas of whare Image should be drawn
	 * @param x X co-ordinate on canvas of where Image should be drawn
	 * @param g2d Instance of current Graphics on which things are to be drawn
	 */
	public void DrawImage(Graphics2D g2d, int x, int y, Image image) {
        g2d.drawImage(image, x, y, this);
    }

	/**
	 * Starts a new game
	 */
	public abstract void newGame();
	/**
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		super.paint(g);

	    Graphics2D g2d = (Graphics2D) g;

	    g2d.setColor(bgColor);
	    g2d.fillRect(0, 0, d.width, d.height);

	    DrawMap(g2d);
	    
	    Toolkit.getDefaultToolkit().sync();
	    g.dispose();

		
	}
	/**
	 * Draws everything loaded onto current map that is contained in the current Game
	 * @param g2d Instance of Graphics on which images are to be drawn
	 */
	private void DrawMap(Graphics2D g2d) {
		for(int x = 0; x < this.game.getMap().getX(); x++){
			for(int y = 0; y < this.game.getMap().getY(); y++){
				if(this.game.getMap().isWall(x,y)){
					g2d.setColor(mazeColor);
					g2d.drawRect(x*defaultTileSize, y*defaultTileSize, defaultTileSize, defaultTileSize);
				}
				else{
					ArrayList<Mappable> onmap = this.game.getMap().getMappable(x, y);
					if(onmap != null){
						for(Mappable om: onmap){
							DrawImage(g2d, x*defaultTileSize, y*defaultTileSize, om.getImage());
						}
					}
					
				}
			}
		}
	}
	/**
	 *	Handy adapter to listen for key presses 
	 */
	class KeyPressHandler extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			onKeyPress(e.getKeyCode());
		}
	}
	/**
	 * Abstract method to allow for custom key bindings for different games
	 * @param keycode integer representation of current key being pressed
	 */
	public abstract void onKeyPress(int keycode);


}
