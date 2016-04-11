/**
 * 
 */
package works.tonny.apps.workflow;

import works.tonny.apps.Entity;

/**
 * @author 祥栋
 */
public class ActivityDesc extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected int x = -1;
	protected int y = -1;
	protected int width = -1;
	protected int height = -1;

	/**
	 * @param name
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public ActivityDesc(String id, String name, int x, int y, int width, int height) {
		super();
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

}
