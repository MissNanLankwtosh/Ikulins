import java.awt.Image;

import javax.swing.ImageIcon;


public abstract class Mappable {
	
	private int x;
	private int y;
	private Image image;
	/**
	 * Creates a Mappable with given X and Y.
	 * @param x Desired X coordinate of the Mappable
	 * @param y Desired Y coordinate of the Mappable
	 */
	public Mappable(int x ,int y){
		this.x = x;
		this.y = y;
	}
	
	public abstract void onEvent(GameEvent e);

	/**
	 * @return X value of Mappable
	 */
	public int getX() {
		return x;
	}
	/**
	 * Change current X value of Mappable
	 * @param x new X coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return Y value of Mappable
	 */	
	public int getY() {
		return y;
	}

	/**
	 * Change current Y value of Mappable
	 * @param y new Y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return (x + "," + y);
	}

	/**
	 * Change the image of Mappable
	 * @param image New image of mappable
	 */
	public void setImage(Image image){
		this.image = image;
	}
	
	/**
	 * Set the image by providing a filepath
	 * @param filepath string representation of filepath to image
	 */
	public void setImage(String filepath){
		this.image =  new ImageIcon(filepath).getImage();
	}
	/**
	 * @return Current image of Mappable
	 */
	public Image getImage() {
		return image;
	}

}
