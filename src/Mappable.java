import java.awt.Image;

import javax.swing.ImageIcon;


/**
 * @author  imran
 */
public abstract class Mappable {
	
	/**
	 * @uml.property  name="x"
	 */
	private int x;
	/**
	 * @uml.property  name="y"
	 */
	private int y;
	/**
	 * @uml.property  name="image"
	 */
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

	/**
	 * @return  X value of Mappable
	 * @uml.property  name="x"
	 */
	public int getX() {
		return x;
	}
	/**
	 * Change current X value of Mappable
	 * @param x  new X coordinate
	 * @uml.property  name="x"
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return  Y value of Mappable
	 * @uml.property  name="y"
	 */	
	public int getY() {
		return y;
	}

	/**
	 * Change current Y value of Mappable
	 * @param y  new Y coordinate
	 * @uml.property  name="y"
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
	 * @param image  New image of mappable
	 * @uml.property  name="image"
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
	 * @return  Current image of Mappable
	 * @uml.property  name="image"
	 */
	public Image getImage() {
		return image;
	}

}
