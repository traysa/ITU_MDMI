package kNN;
import id3.ID3Object;

import java.util.*;

import data.Class_Label;

public class KNNAlgorithm {
	
	protected static final boolean DEBUG = false;
	
	//k-nearest neighbour function
	public double nearestNeighbour(ArrayList<ID3Object> testSet, ArrayList<ID3Object> trainingSet, int k) throws Exception{
		int errorCnt = 0;
		int successCnt = 0;
		boolean remove = false;
		for (ID3Object testMushroom : testSet){
			remove = trainingSet.remove(testMushroom); // removing mushroom to test from the trainingset
			int row = 0;
			ArrayList<Neighbour> distances = new ArrayList<Neighbour>();
			
			// Calculate the distance from the mushroom to test to each other mushroom in the trainingset
			for (ID3Object trainingMushroom : trainingSet){
				row++;
				int distance = distance(testMushroom, trainingMushroom);
				distances.add(new Neighbour(trainingMushroom,distance,row));
			}
			
			// Sort the mushrooms to their distance to the mushroom to test
			Collections.sort(distances);
			
			// Select the k-nearest neighbours and count how many edible/poisonous mushrooms there are
			int[] classOccurances = rateKNearestNeighbours(distances, k);
			
			if (checkPrediction(classOccurances,testMushroom) == 1)
				errorCnt++;
			else successCnt++;
			
			// Adding mushroom to test back to the training set, so it can be used for the next mushroom to test
			if (remove) trainingSet.add(testMushroom);
		}
		if (DEBUG){
			System.out.println("error: "+errorCnt);
			System.out.println("success: "+successCnt);	
		}
		return (double)successCnt/(successCnt+errorCnt);
	}
	
	// Calculates the distance between two mushrooms
	// A high value indicates a high distance (inequality)
	private static int distance (ID3Object a, ID3Object b){
		ArrayList<Object> attributes = a.getAttributeList();
		int sumDiff = 0;
		for (Object attribute : attributes){
			if (a.getAttributeValue(attribute) != b.getAttributeValue(attribute))
				sumDiff++;
		}
		return sumDiff;
	}
	
	// Counts occurances of classes within the k nearest neighbours
	// Return array of counts for each class
	private static int[] rateKNearestNeighbours(ArrayList<Neighbour> distances, int k) throws Exception{
		int[] result = {0, 0};
		for (int i = 0; i < k; i++){
			Neighbour neighbour = distances.get(i);
			if (neighbour.getMushroom().getClasslabel().equals(Class_Label.edible))
				result[0]++;
			else if (neighbour.getMushroom().getClasslabel().equals(Class_Label.poisonous))
				result[1]++;
			else throw new Exception("Error");
		}
		return result;
	}
	
	// Print prediction and real value for a mushroom
	// Returns 1 if the prediction and the real value are a mismatch
	private static int checkPrediction(int[] classOccurances, ID3Object mushroom){
		//System.out.println("E: "+classOccurances[0] + "; P: " + classOccurances[1]);
		Class_Label prediction = Class_Label.poisonous;
		if (classOccurances[0] > classOccurances[1])
			prediction = Class_Label.edible;			
					
		if (mushroom.getClasslabel() != prediction){
			if (DEBUG){
				System.out.println("Prediction: "+prediction);
				System.out.println("Real value: "+mushroom.getClasslabel());
				System.out.println("---------------------> WARNING");
			}
			return 1;
		}
		return 0;
	}
	
}
