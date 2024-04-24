package main;

import java.util.Random;
import java.util.Scanner;

import items.Door;
import items.Item;
import items.Player;
import map.Map;

/**
 * This is the Main Game class. It makes a 3x3 rooms of a dungeon which is a
 * total of 9 room. In each room there are gold coins that player can pick,
 * enemies which player can easily kill and doors which take player to the next
 * dungeon room. The objective of the game is to find the sword treasure which
 * is one of the 9 room.
 */
public class AdventureGame {

	public static final int NUM_ROOMS = 9;

	private Map[] allRooms;
	private Map currentRoom;
	private Player player;

	/**
	 * Constructor
	 */
	public AdventureGame() {

		// initialize locations
		allRooms = new Map[NUM_ROOMS];
		for (int i = 0; i < allRooms.length; i++)
			allRooms[i] = new Map();

		placeItemsOnMap();
		joinLocations();

		// player starts on center location
		currentRoom = allRooms[NUM_ROOMS / 2];
		// player
		player = new Player(3, 2);
		currentRoom.putItem(player, 3, 2);

	}

	/**
	 * Runs the game
	 */
	public void run() {
		Scanner scan = new Scanner(System.in);
		boolean hasFoundTreasure = false;

		System.out.println("[-----------TREASURE HUNT ADVENTURE-------------]\n");
		System.out.println("Introduction: ");
		System.out.println("You are an adventurer who has entered a dungeon which has 9 rooms. There\n"
				+ "is a legendary sword treasure in one of these rooms that you need to save your village"
				+ " and its your job to find it\n" + "GOOD LUCK!!\n\n");
		System.out.println("In the map:");
		System.out.println("P is Player");
		System.out.println("C is Gold Coin");
		System.out.println("U is for Undead Enemy");
		System.out.println("S is for Slime Enemy");
		System.out.println("S also is for Sword Treasure");
		System.out.println("N is for NPC");
		System.out.println("D is for door to next dungeon room");
		System.out.println("\n\n");

		do {

			// print location
			System.out.println("\n Dungeon Current Location: ");
			System.out.println(currentRoom);

			// prompt for input
			String input = "";
			do {
				System.out.println("\nEnter: ");
				System.out.println("n to move NORTH");
				System.out.println("e to move EAST");
				System.out.println("s to move SOUTH");
				System.out.println("w to move WEST");
				System.out.println("i to show INVENTORY");
				input = scan.next().toLowerCase();

				// show inventory items
				if (input.equals("i")) {
					System.out.println("Inventory Items: ");
					for (Item item : player.getInventory())
						System.out.println(item);
				}

			} while (input.equals("i"));

			// player row,col and destination row,col
			int row = player.getRow();
			int col = player.getCol();
			int dr = row, dc = col;

			// set destination row,col according to input
			if (input.equals("n"))
				dr = row - 1;
			else if (input.equals("e"))
				dc = col + 1;
			else if (input.equals("s"))
				dr = row + 1;
			else if (input.equals("w"))
				dc = col - 1;
			else {
				//if input is invalid,continue with loop
				System.out.println("Invalid Input");
				System.out.println("Try again.\n");
				continue;
			}

			// if destination is empty,then move player to it
			if (currentRoom.isEmpty(dr, dc)) {
				// move player to new tile
				currentRoom.putItem(null, row, col);
				currentRoom.putItem(player, dr, dc);
				player.setLocation(dr, dc);

				System.out.println("You moved to new tile!!");
			}

			// if destination has an item in it then,
			else {

				// item
				Item item = currentRoom.getItem(dr, dc);
				int type = item.getType();

				switch (type) {

				case Item.PICK_UP:
					// pick up the item
					currentRoom.putItem(null, dr, dc);
					player.pickItem(item);

					// move player to new tile
					currentRoom.putItem(null, row, col);
					currentRoom.putItem(player, dr, dc);
					player.setLocation(dr, dc);

					System.out.println("There was a " + item.getName() + " in front of you.");
					System.out.println("You walked to it and picked it up.");
					System.out.println("Now its in your inventory.");

					break;
				case Item.INTERACTABLE_ITEM:
					System.out.println("There is an " + item.getName() + " in front of you.");
					System.out.println("You interacted with it.");
					System.out.println(item.getName() + ": Hi there, I am a " + item.getName());

					break;
				case Item.ENEMY:
					currentRoom.putItem(null, dr, dc);

					System.out.println("Oh no, There is an enemy " + item.getName() + " in front of you.");
					System.out.println("But it was very weak and you defeated it.");

					// move player to new tile
					currentRoom.putItem(null, row, col);
					currentRoom.putItem(player, dr, dc);
					player.setLocation(dr, dc);
					break;
				case Item.DOOR:
					System.out.println("Oh, There is a door in front which takes you another room in Dungeon.");
					System.out.println("You opened the door and now have entered a new location");

					Door door = (Door) item;

					// move player to new location
					currentRoom.putItem(null, row, col);
					currentRoom = allRooms[door.getExitLocation()];
					currentRoom.putItem(player, door.getExitRow(), door.getExitCol());
					player.setLocation(door.getExitRow(), door.getExitCol());

					break;

				case Item.TREASURE:
					// pick up the item
					currentRoom.putItem(null, dr, dc);
					player.pickItem(item);

					// move player to new tile
					currentRoom.putItem(null, row, col);
					currentRoom.putItem(player, dr, dc);
					player.setLocation(dr, dc);

					System.out.println("There was a " + item.getName() + " in front of you.");
					System.out.println("Congrats!!! You have finally found the TREASURE!!!!");
					System.out.println("Your quest has been completed!!!");

					hasFoundTreasure = true;
					break;

				}
			}

		} while (!hasFoundTreasure);

		System.out.println("\nGame Ended.\nYou have successfully accomplished your goal.");
		scan.close();
	}

	/**
	 * This functions joins each room of dungeon with the other by placing doors
	 * which exits at the other dungeon room. A room can have from 2 - 4 doors.
	 */
	private void joinLocations() {
		// location 0 doors
		allRooms[0].putItem(new Door(1, 2, 1), 2, 4);
		allRooms[0].putItem(new Door(3, 1, 2), 4, 2);

		// location 1 doors
		allRooms[1].putItem(new Door(2, 2, 1), 2, 4);
		allRooms[1].putItem(new Door(4, 1, 2), 4, 2);
		allRooms[1].putItem(new Door(1, 2, 3), 2, 0);

		// location 2 doors
		allRooms[2].putItem(new Door(5, 1, 2), 4, 2);
		allRooms[2].putItem(new Door(1, 2, 3), 2, 0);

		// location 3 doors
		allRooms[3].putItem(new Door(4, 2, 1), 2, 4);
		allRooms[3].putItem(new Door(6, 1, 2), 4, 2);
		allRooms[3].putItem(new Door(2, 3, 2), 0, 2);

		// location 4 doors
		allRooms[4].putItem(new Door(5, 2, 1), 2, 4);
		allRooms[4].putItem(new Door(7, 1, 2), 4, 2);
		allRooms[4].putItem(new Door(3, 2, 3), 2, 0);
		allRooms[4].putItem(new Door(1, 3, 2), 0, 2);

		// location 5 doors
		allRooms[5].putItem(new Door(8, 1, 2), 4, 2);
		allRooms[5].putItem(new Door(4, 2, 3), 2, 0);
		allRooms[5].putItem(new Door(2, 3, 2), 0, 2);

		// location 6 doors
		allRooms[6].putItem(new Door(7, 2, 1), 2, 4);
		allRooms[6].putItem(new Door(3, 3, 2), 0, 2);

		// location 7 doors
		allRooms[7].putItem(new Door(8, 2, 1), 2, 4);
		allRooms[7].putItem(new Door(6, 2, 3), 2, 0);
		allRooms[7].putItem(new Door(4, 3, 2), 0, 2);

		// location 8 doors
		allRooms[8].putItem(new Door(5, 2, 3), 2, 0);
		allRooms[8].putItem(new Door(7, 3, 2), 0, 2);

	}

	/**
	 * This function places items on the map randomly. And the treasure is placed in
	 * the room no 1
	 */
	private void placeItemsOnMap() {
		Random random = new Random();
		int row = 0, col = 0;

		// put 5 pickup items in each map location
		for (int i = 0; i < allRooms.length; i++)
			for (int j = 0; j < 5; j++) {
				do {
					row = random.nextInt(5);
					col = random.nextInt(5);
				} while (!allRooms[i].isEmpty(row, col));
				allRooms[i].putItem(new Item("Gold Coin", Item.PICK_UP), row, col);
			}

		// put 2 enemies in each map location
		for (int i = 0; i < allRooms.length; i++)
			for (int j = 0; j < 2; j++) {
				do {
					row = random.nextInt(5);
					col = random.nextInt(5);
				} while (!allRooms[i].isEmpty(row, col));

				allRooms[i].putItem(new Item(j % 2 == 0 ? "Undead" : "Slime", Item.ENEMY), row, col);
			}

		// put 1 npc in each map
		for (int i = 0; i < allRooms.length; i++) {
			do {
				row = random.nextInt(5);
				col = random.nextInt(5);
			} while (!allRooms[i].isEmpty(row, col));

			allRooms[i].putItem(new Item("NPC", Item.INTERACTABLE_ITEM), row, col);
		}

		// put treasure in location 1
		allRooms[1].putItem(new Item("Sword Treasure", Item.TREASURE), 2, 2);

	}

	
	public static void main(String args[]) {
		AdventureGame game = new AdventureGame();
		game.run();
	}
}
