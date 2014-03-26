package data;

import java.util.ArrayList;

import kMean.KMeanCluster;

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
	
	/**
	 * Calculates the mean of a cluster of items
	 * @param cluster
	 * @return Mean
	 */
	public Item mean(ArrayList<Item> clusterMembers);

	/**
	 * Analyzes the clusters and returns some useful statistics
	 * @param clusters Clusters to consider
	 * @param k Number of clusters
	 * @return Analysis statistics
	 */
	public String analyzeClusters(ArrayList<KMeanCluster> clusters, int k);
}
