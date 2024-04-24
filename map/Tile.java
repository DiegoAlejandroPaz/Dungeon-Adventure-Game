package map;

import items.Item;

/**
 * This is single in the game which can hold a single item of any type.
 *
 */
public class Tile {

	private Item item;

	@Override
	public String toString() {
		if (!isEmpty())
			return item.toString();

		return " ";
	}

	public boolean isEmpty() {
		return item == null;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
