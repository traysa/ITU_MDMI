import java.util.ArrayList;
import java.util.EnumSet;

import data.Cap_Shape;

/**
 * Main class to run program from.
 * 
 * @author Anders Hartzen
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// First step - Load data and convert to Mushroom objects.
		ArrayList<Mushroom> mushrooms = DataManager.LoadData();
		System.out.println("DataManager loaded "+mushrooms.size() + " mushrooms");
		
		//Size of Testset; Testset is picked from the mushroom objects
		int sizeOfTestSet = 1000;
		ArrayList<Mushroom> testSet = new ArrayList<Mushroom>();
		for (int i = 0; i < sizeOfTestSet; i++){
			testSet.add(mushrooms.get(i));
		}
		System.out.println("TestSet: "+testSet.size() + " mushrooms");
		
		// k-Nearest Neighbour Algorithm
		int k = 20;
		kNNAlgorithm.nearestNeighbour(testSet,mushrooms,k);
	}

}
