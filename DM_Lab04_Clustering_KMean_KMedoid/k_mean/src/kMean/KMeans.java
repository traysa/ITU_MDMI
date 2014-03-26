package kMean;
import java.util.ArrayList;

import data.*;

import java.util.Random;

public class KMeans {
	
	private static final boolean DEBUG = false;
	
	/**
	 * K-Mean Clustering
	 * 
	 * @param k Number of clusters
	 * @param data Data to cluster
	 * @return
	 */
	public static ArrayList<KMeanCluster> KMeansPartition(int k, ArrayList<Item> data)
	{
		// Get random initial cluster centres
		Item[] previousClusterCenters = getInitialClusterCenters(k, data);
		Item[] clusterCenters;
		ArrayList<KMeanCluster> clusters = new ArrayList<KMeanCluster>();
		boolean clusterCenterChanged = true;
		int counter = 0;
		while (clusterCenterChanged){
			System.out.println("COUNTER: "+counter++);
			
			clusters = clustering(k, previousClusterCenters, data);
			if (DEBUG){
				System.out.println("CLUSTERS: " + clusters);
			}
			
			clusterCenters = getClusterCenters(k, clusters);	
			if (DEBUG){
				System.out.println("CLUSTER CENTERS: ");
				System.out.println(printClusterCenters(clusterCenters,k));
			}
			
			clusterCenterChanged = !areClusterCentersEqual(previousClusterCenters,clusterCenters,k);
			
			previousClusterCenters = clusterCenters;
		}
		
		System.out.println(clusters.get(0).ClusterMembers.get(0).analyzeClusters(clusters,k));
		
		return clusters;
	}
	
	/**
	 * Get the initial cluster centers by picking k random items from the data
	 * @param k Number of clusters
	 * @param data Data to cluster
	 * @return Random cluster centers
	 */
	private static Item[] getInitialClusterCenters(int k, ArrayList<Item> data){
		Item[] clusterCenters = new Item[k];	
		Random randomGenerator = new Random();
		for (int i = 0; i<k; i++){
			clusterCenters[i] = data.get(randomGenerator.nextInt(data.size()));		
		}
		return clusterCenters;
	}
	
	/**
	 * Clusters the data according to the k cluster centers
	 * @param k Number of clusters
	 * @param clusterCenters Current cluster centers
	 * @param data Data to cluster
	 * @return Clusters
	 */
	private static ArrayList<KMeanCluster> clustering(int k, Item[] clusterCenters, ArrayList<Item> data){
		ArrayList<KMeanCluster> clusters = new ArrayList<KMeanCluster>();
		for (int i = 0; i<k; i++){
			clusters.add(new KMeanCluster());
		}
		// Iterate through data and find the nearest cluster center with the minimal distance
		for (Item item: data){
			float minDistance = Integer.MAX_VALUE;
			int cluster = -1;
			for (int i = 0; i<k; i++){
				float distance = item.distance(clusterCenters[i]);
				//System.out.println("DISTANCE: "+distance);
				if (distance <= minDistance){
					minDistance = distance;
					cluster = i;
				}
			}
			clusters.get(cluster).ClusterMembers.add(item);
		}		
		return clusters;
	}

	/**
	 * Calculates the new cluster centers by returning the mean of each cluster
	 * @param k Number of clusters
	 * @param clusters Clusters
	 * @return New cluster centers
	 */
	private static Item[] getClusterCenters(int k, ArrayList<KMeanCluster> clusters){
		Item[] clusterCenters = new Item[k];
		for (int i = 0; i<k; i++){
			ArrayList<Item> clusterMembers = clusters.get(i).ClusterMembers;
			//if (!clusterMembers.isEmpty()){
				Item clusterCenter = clusterMembers.get(0).mean(clusterMembers);
				clusterCenters[i] = clusterCenter;
			//}
		}
		return clusterCenters;
	}
	
	/**
	 * Check if the cluster centers are equal
	 * @param clusterCenter1 Cluster center 1
	 * @param clusterCenter2 Cluster center 2
	 * @param k Number of Clusters
	 * @return Are cluster centers equal?
	 */
	private static boolean areClusterCentersEqual(Item[] clusterCenter1, Item[] clusterCenter2, int k){
		boolean equal = true;
		int i = 0;
		while (i < k && equal){
			boolean temp = false;
			int j = 0;
			while (j < k && !temp){
				temp = clusterCenter1[i].compareTo(clusterCenter2[j])==1;
				j++;
			}
			equal = temp;
			i++;
		}
		return equal;
	}
	
	/**
	 * Prints the cluster centers
	 * @param clusterCenters Cluster centers
	 * @param k Number of clusters
	 */
	private static String printClusterCenters(Item[] clusterCenters,int k){
		String result = "";
		for (int i = 0; i<k; i++){
			result += "\n\t" + clusterCenters[i].toString();
		}
		return result;
	}
	
}
