package items;

/**
 * This item connects two dungeon rooms with each other.
 * A Door object stores the exit location id i.e the room number it takes the player to and
 * it also stores the exit row and column of tile.
 *
 */
public class Door extends Item{

	private int exitLocation;
	private int exitRow,exitCol;
	
	/**
	 * Constructor
	 * @param exitLocation	exit room number
	 * @param exitRow	exit row	of tile
	 * @param exitCol	exit column	of column
	 */
	public Door(int exitLocation,int exitRow,int exitCol) {
		super("Door",DOOR);
		this.exitLocation = exitLocation;
		this.exitCol = exitCol;
		this.exitRow = exitRow;
	}

	//getters
	
	public int getExitLocation() {
		return exitLocation;
	}

	public int getExitRow() {
		return exitRow;
	}

	public int getExitCol() {
		return exitCol;
	}

}
