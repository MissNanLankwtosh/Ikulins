import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public abstract class GameFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private GamePanel gpanel;

	/**
	 * Creates a frame that contains the game
	 * @param title Title of the frame
	 * @param gpanel Panel that contains the game
	 */
	public GameFrame(String title, GamePanel gpanel){
		menuBar = new JMenuBar();
		menuBar.add(new JMenu("Game"));
		menuBar.getMenu(0).add(new JMenuItem("New Game"));
		menuBar.getMenu(0).getItem(0).addActionListener(this);
		this.gpanel = gpanel;
		add(this.gpanel);
		setJMenuBar(menuBar);
		this.setSize(gpanel.getHeight()+50, gpanel.getWidth()+50);
		setVisible(true);

	}
	/**
	 * Creates a Frame of the Game
	 * @param gpanel Panel of the current game
	 */
	public GameFrame(GamePanel gpanel){
		this("Default Title", gpanel);
	}
	
	/**
	 * Allows the user to start a new game
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if(s == "New Game"){
			gpanel.newGame();
		}

	}

}
