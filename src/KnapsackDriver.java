import java.util.Scanner;
import java.util.ArrayList;

public class KnapsackDriver {

	public static void main(String[] args) {

		ArrayList<Item> stuff = new ArrayList<Item>();
		stuff.add(new Item("Lunch", .75));
		stuff.add(new Item("Physics Book", 4.1));
		stuff.add(new Item("APCS Book", 2.3));
		stuff.add(new Item("Jacket", 1.2));
		stuff.add(new Item("Water", 1.9));
		stuff.add(new Item("AP Lit Book", 3.8));

		double choice;
		Scanner keybd = new Scanner(System.in);
		
		do {
		
			System.out.println("We need to optimally store these items in a backpack:");
			double totalItemWeight = 0;
			for(Item i : stuff) {
				System.out.println(i);
				totalItemWeight += i.getWeight();
			}
			System.out.println("\n(total weight of items = " + totalItemWeight + "lbs)\n");
			
			System.out.print("How many pounds can your backpack hold? (-1 to quit)  ");
			try {
				choice = Double.parseDouble( keybd.nextLine() );
			} catch(Exception e) {
				choice = 0;
			}
			
			if ( choice >= 0 ) {
				Knapsack bag = new Knapsack(choice);
		
				System.out.println("Weight used is " + bag.pack(stuff) + "lbs out of "
						+ bag.getCapacity() + "lbs.");
				System.out.print("Contents:  " + bag.getContents() + "\n\n");
			}
				
		} while( choice >= 0 );

	}

}
