package clustering.data;

import java.util.ArrayList;

import clustering.kMean.KMeanCluster;

/**
 * 
 * @author Theresa Brandt von Fackh
 *
 */
public interface Item extends Comparable<Item> {

	public int compareTo(Item obj);
	
	/**
	 * Calculates the distance between two data items
	 * @param obj Other item
	 * @return Distance
	 */
	public float distance(Item obj);
	
}
