package items;

import java.util.ArrayList;

/**
 * This class defines a player in the game.
 */
public class Player extends Item {

	private int row, col;
	private ArrayList<Item> inventory;
	
	/**
	 * Constructor
	 * @param i	row of player
	 * @param j	column of player
	 */
	public Player(int i, int j) {
		super("Player");
		row = i;
		col = j;
		
		inventory = new ArrayList<Item>();
	}
	
	/**
	 * picks the item and add to inventory
	 * @param item	item
	 */
	public void pickItem(Item item) {
		inventory.add(item);
	}
	
	//getters and setters
	
	public void setLocation(int row,int col) {
		this.row = row;
		this.col = col;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public ArrayList<Item> getInventory(){
		return inventory;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
}
