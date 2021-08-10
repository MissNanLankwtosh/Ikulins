import java.awt.Image;

import javax.swing.ImageIcon;


public abstract class Mappable {
	private int x;
	private int y;
	private Image image;
	public Mappable(int x ,int y){
		this.x = x;
		this.y = y;
	}
	
	public abstract void onEvent(GameEvent e);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString(){
		return (x + "," + y);
	}

	public void setImage(Image image){
		this.image = image;
	}
	
	public void setImage(String filepath){
		this.image =  new ImageIcon(filepath).getImage();
	}
	public Image getImage() {
		return image;
	}

}
