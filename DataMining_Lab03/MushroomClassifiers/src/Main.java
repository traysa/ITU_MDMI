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
		
		int sizeOfTestSet = 20;
		ArrayList<Mushroom> testSet = new ArrayList<Mushroom>();
		ArrayList<Mushroom> trainingSet = mushrooms;
		for (int i = 0; i < sizeOfTestSet; i++){
			testSet.add(trainingSet.get(i));
		}
		System.out.println("TestSet: "+testSet.size() + " mushrooms");
		System.out.println("TestSet: "+testSet);
		
		kNNAlgorithm.nearestNeighbour(testSet,mushrooms,14);
		
		
	}

}
