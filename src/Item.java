/**
 * An item is something that may be packed into a backpack. Items have a name
 * and a weight.
 */
public class Item {

	private double weight;
	private String name;

	public Item(String n, double w) {
		name = n;
		weight = w;
	}

	public String getName() {
		return name;
	}

	public double getWeight() {
		return weight;
	}

	public String toString() {
		return name + " (" + weight + " lbs)";
	}

}
