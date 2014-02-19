import java.util.ArrayList;
import data.Class_Label;

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
		int sizeOfTestSet = 208;
		ArrayList<Mushroom> testSet = new ArrayList<Mushroom>();
		for (int i = 0; i < sizeOfTestSet; i++){
			testSet.add(mushrooms.get(i));
		}
		System.out.println("TestSet: "+testSet.size() + " mushrooms");
		
		ID3Algorithm id3 = new ID3Algorithm();
		Node decisionTree = id3.generateDecisionTree(testSet,Class_Label.class);
		
		//String value = mushrooms.get(0).getAttributeValue(decisionTree.getClassification()).toString();
		double result = id3.testData(decisionTree, mushrooms);
		System.out.println("Accurancy: " + result);
		
		
		/*
		// Check Level 1
		for (Mushroom mushroom: mushrooms){
			if (mushroom.m_odor.equals(Odor.foul))
				if (!mushroom.m_Class.equals(Class_Label.poisonous))
					System.out.println("Error");
		}
		// Check Level 2
		for (Mushroom mushroom: mushrooms){
			if (mushroom.m_odor.equals(Odor.none) && mushroom.m_spore_color.equals(Spore_Print_Color.black))
					if (!mushroom.m_Class.equals(Class_Label.edible))
						System.out.println("Error");
		}
		// Check Level 3
		for (Mushroom mushroom: mushrooms){
			if (mushroom.m_odor.equals(Odor.none) && mushroom.m_spore_color.equals(Spore_Print_Color.white) && mushroom.m_population.equals(Population.clustered))
					if (!mushroom.m_Class.equals(Class_Label.poisonous))
						System.out.println("Error");
		}
		*/
		
		// k-Nearest Neighbour Algorithm
		//int k = 2;
		//kNNAlgorithm.nearestNeighbour(testSet,mushrooms,k);
	}

}
