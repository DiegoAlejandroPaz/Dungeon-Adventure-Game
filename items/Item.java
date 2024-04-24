package items;

/**
 * This defines all types of item in the game.
 *
 */
public class Item {

	public static final int PICK_UP = 1;
	public static final int INTERACTABLE_ITEM = 2;
	public static final int ENEMY = 3;
	public static final int DOOR = 4;
	public static final int TREASURE = 5;

	private String name;
	private int type;

	/**
	 * Constructor
	 * @param name	name of item
	 */
	public Item(String name) {
		this(name, 0);
	}

	/**
	 * Constructor
	 * @param name	name of item
	 * @param type	type of item
	 */
	public Item(String name, int type) {
		this.name = name;
		this.type = type;
	}
	
	//getters and setters

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name.substring(0, 1);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
