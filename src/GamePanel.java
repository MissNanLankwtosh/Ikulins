import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;


public abstract class GamePanel extends JPanel{

	private static final long serialVersionUID = -2458261363554188485L;
	private Game game;
	private Color mazeColor;
	private Color bgColor;
	private Dimension d;
	private final int defaultTileSize = 20; //in pixels
	
	public GamePanel(Game g){
		this.game = g;
		addKeyListener(new KeyPressHandler());
		setFocusable(true);
		
		d = new Dimension(this.game.getMap().getX() * defaultTileSize, this.game.getMap().getY() * defaultTileSize);
		mazeColor = new Color(0,255,0);
		bgColor = new Color(0,0,0);
		
		setBackground(bgColor);
		setDoubleBuffered(true);


	}
	
	public void addNotify() {
        super.addNotify();
        GameInit();
    }

	public abstract void GameInit();

	public void setBgColor( Color color){
		this.bgColor = color;
	}
	
	public void setMazeColor( Color color){
		this.mazeColor = color;
	}
	
	public void DrawImage(Graphics2D g2d, int x, int y, Image image) {
        g2d.drawImage(image, x, y, this);
    }

	public abstract void newGame();
	
	public void paint(Graphics g){
		super.paint(g);

	    Graphics2D g2d = (Graphics2D) g;

	    g2d.setColor(bgColor);
	    g2d.fillRect(0, 0, d.width, d.height);

	    DrawMap(g2d);
	    
	    Toolkit.getDefaultToolkit().sync();
	    g.dispose();

		
	}
	
	private void DrawMap(Graphics2D g2d) {
		for(int x = 0; x < this.game.getMap().getX(); x++){
			for(int y = 0; y < this.game.getMap().getY(); y++){
				if(this.game.getMap().isWall(x,y)){
					g2d.setColor(mazeColor);
					g2d.drawRect(x, y, defaultTileSize, defaultTileSize);
				}
				else{
					ArrayList<Mappable> onmap = this.game.getMap().getMappable(x, y);
					if(onmap != null){
						for(Mappable om: onmap){
							DrawImage(g2d, x, y, om.getImage());
						}
					}
					
				}
			}
		}
	}

	class KeyPressHandler extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			int key = e.getKeyCode();
			switch(key){
			//Add cases for different key presses here
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
	        repaint();  
	}

}
