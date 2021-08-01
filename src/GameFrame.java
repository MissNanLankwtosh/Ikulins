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

	public GameFrame(String title, GamePanel gpanel){
		menuBar.add(new JMenu("Game"));
		menuBar.getMenu(0).add(new JMenuItem("New Game"));
		menuBar.getMenu(0).getItem(0).addActionListener(this);
		this.gpanel = gpanel;
		add(this.gpanel);
		setJMenuBar(menuBar);
		pack();
		setVisible(true);

	}
	public GameFrame(GamePanel gpanel){
		this("Default Title", gpanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if(s == "New Game"){
			gpanel.newGame();
		}

	}

}
