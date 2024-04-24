	package map;

import items.Item;

/**
 * This class creates an array of 5x5 tiles for a room in dungeon.
 * 
 *
 */
public class Map {

	public static final int ROWS = 5;
	public static final int COLS = 5;

	private Tile[][] tiles;

	/**
	 * Constructor
	 */
	public Map() {
		initMap();
	}

	/**
	 * Puts item at tile(row,col)
	 * 
	 * @param item	item
	 * @param row	row number
	 * @param col	column number
	 */
	public void putItem(Item item, int row, int col) {
		tiles[row][col].setItem(item);
	}

	/**
	 * gets item at tile(row,col)
	 * 
	 * @param row	row number
	 * @param col	column number
	 * @return the item
	 */
	public Item getItem(int row, int col) {
		return tiles[row][col].getItem();
	}

	/**
	 * Checks if the tile(row,col) is empty
	 * 
	 * @param row	row number
	 * @param col	column number
	 * @return	true if the tile is empty
	 */
	public boolean isEmpty(int row, int col) {
		if (!isValid(row, col))
			return false;
		return tiles[row][col].isEmpty();
	}

	
	/**
	 * Checks if the row and column numbers are valid
	 * 
	 * @param row	row number
	 * @param col	column number
	 * @return	true if the row,col are valid
	 */
	private boolean isValid(int row, int col) {
		return row >= 0 && col >= 0 && row < ROWS && col < COLS;
	}

	/**
	 * Returns a sting represenation of the map.
	 */
	@Override
	public String toString() {
		String str = "";

		for (int i = 0; i < ROWS; i++) {
			str += "| ";
			for (int j = 0; j < COLS; j++)
				str += tiles[i][j] + " |";
			str += "\n";
		}

		return str;
	}

	/**
	 * initializes the map tiles
	 */
	private void initMap() {
		tiles = new Tile[ROWS][COLS];

		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLS; j++)
				tiles[i][j] = new Tile();
	}
}
