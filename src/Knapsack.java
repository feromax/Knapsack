import java.util.ArrayList;

/**
 * A knapsack has a weight capacity for the items it contains.
 * 
 */
public class Knapsack {

	private ArrayList<Item> contents;
	private double capacity;

	/**
	 * Knapsacks start out with no contents and must have a specified capacity.
	 * 
	 * @param c
	 *            capacity of the knapsack
	 */
	public Knapsack(double c) {
		contents = new ArrayList<Item>();
		capacity = c;
	}

	/**
	 * Return max capacity of this knapsack.
	 * 
	 * @return capacity of this knapsack
	 */
	public double getCapacity() {
		return capacity;
	}

	/**
	 * Returns the packed contents of this knapsack.
	 * 
	 * @return list of items contained in this knapsack
	 */
	public ArrayList<Item> getContents() {
		return contents;
	}

	/**
	 * Pack this knapsack with subset of items that maximizes the available
	 * capacity. This method relies on a helper method to do the heavy lifting.
	 * 
	 * @param items
	 *            list of items to pack
	 * @return used knapsack capacity
	 */
	public double pack(ArrayList<Item> items) {
		ArrayList<Double> itemWeights = packHelper(items, capacity);
		double weightUsed = 0;

		// Using the list of weights of items in the backpack,
		// reconstruct the optimal set of contents and store in field.
		// This process will be destructive to items, to operate on
		// a copy instead.
		ArrayList<Item> copyOfItems = new ArrayList<Item>(items);
		for (double weight : itemWeights) {
			weightUsed += weight;
			for (int i = 0; i < copyOfItems.size(); i++) {
				Item curItem = copyOfItems.get(i);
				if (curItem.getWeight() == weight) {
					contents.add(curItem);
					copyOfItems.remove(i);
					break;
				}
			}
		}

		return weightUsed;

	}

	/**
	 * Recursive method to find optimal combination of items for this backpack.
	 * 
	 * @param items
	 *            list of items to pack
	 * @param capacityRemaining
	 *            weight capacity left
	 * @return weight used
	 */
	private ArrayList<Double> packHelper(ArrayList<Item> items,
			double capacityRemaining) {
		// for each item, attempt to pack and find optimal packing
		// of remaining items. choose packing that leads to best use
		// of weight
		double maxWeightUsed = -1;
		ArrayList<Double> bestWeights = new ArrayList<Double>();

		for (int i = 0; i < items.size(); i++) {
			ArrayList<Item> copyOfItems = new ArrayList<Item>(items);
			Item curItem = copyOfItems.remove(i); // copyOfItems now just
													// remaining items
			double curWeight = curItem.getWeight();
			if (curWeight <= capacityRemaining) { // possible to pack this item
				ArrayList<Double> weightsAfterCur = packHelper(copyOfItems,
						capacityRemaining - curWeight);
				double weightUsed = curWeight + sumOfWeights(weightsAfterCur);
				if (weightUsed > maxWeightUsed) {
					maxWeightUsed = weightUsed;
					weightsAfterCur.add(0, curWeight);
					bestWeights = weightsAfterCur;
				}

			}
		}

		return bestWeights;

	}

	private static double sumOfWeights(ArrayList<Double> weights) {
		double sum = 0;
		for (double i : weights) {
			sum += i;
		}
		return sum;
	}

}
