package supervisedLearning;

import supervisedLearning.id3.GraphPrinter;
import supervisedLearning.id3.ID3Algorithm;
import supervisedLearning.id3.Node;
import supervisedLearning.id3.ID3Object;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import supervisedLearning.data.DataManager_Survey2014;

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
		ArrayList<ID3Object> survey2014 = DataManager_Survey2014.LoadDataFromFile("");
		System.out.println("DataManager loaded "+survey2014.size() + " items\n");
		
		System.out.println("================================================");
		System.out.println("ID3 Algorithm");
		System.out.println("================================================");
		boolean loop = true;
		if (loop)
			loopID3(survey2014);
		else
			ID3(survey2014,10);
	}
	
	/**
	 * 
	 * @param mushrooms
	 * @param sizeOfTestSet
	 * @throws Exception 
	 */
	private static void ID3(ArrayList<ID3Object> mushrooms, int sizeOfTestSet) throws Exception{
		//Size of Testset; Testset is picked from the mushroom objects
		ArrayList<ID3Object> testSet = new ArrayList<ID3Object>();
		for (int i = 0; i < sizeOfTestSet; i++){
			testSet.add(mushrooms.get(i));
		}
		
		ID3Algorithm id3 = new ID3Algorithm();
		Node decisionTree = id3.generateDecisionTree(testSet,"s2014_class","");
	    
	    try {
		    GraphPrinter graphPrinter = new GraphPrinter("C:\\Program Files (x86)\\Graphviz2.36\\bin","png",".\\results");
		    graphPrinter.createAutomatGraphViz("result_"+sizeOfTestSet+"_"+new SimpleDateFormat("yyyyMMdd").format(new Date()), decisionTree);
	    }
	    catch (Exception e){
	    	
	    }
		
		double result = id3.testData(decisionTree, mushrooms);
		
		System.out.println("\nTestSet:\t"+testSet.size() + " mushrooms");
		System.out.println("Accurancy:\t" + result);
	}
	
	/**
	 * 
	 * @param mushrooms
	 * @throws Exception 
	 */
	private static void loopID3(ArrayList<ID3Object> mushrooms) throws Exception{
		System.out.println("\nTestSet (Mushrooms)\t| Accuracy");
		System.out.println("---------------------------------------------");
		double currentAccruracy = 0;
		int sizeOfTestSet = 1;
		boolean maxAccuracy = false;
		while (sizeOfTestSet<=mushrooms.size() && !maxAccuracy){
			ArrayList<ID3Object> testSet = new ArrayList<ID3Object>();
			for (int i = 0; i < sizeOfTestSet; i++){
				testSet.add(mushrooms.get(i));
			}
			
			ID3Algorithm id3 = new ID3Algorithm();
			Node decisionTree = id3.generateDecisionTree(testSet,"s2014_class","");
		    
			double result = id3.testData(decisionTree, mushrooms);
			if (currentAccruracy < result){
				System.out.println(testSet.size()+"\t\t\t| "+result);
				currentAccruracy = result;
			    try {
				    GraphPrinter graphPrinter = new GraphPrinter("C:\\Program Files (x86)\\Graphviz2.36\\bin","png",".\\results");
				    graphPrinter.createAutomatGraphViz("result_"+sizeOfTestSet+"_"+new SimpleDateFormat("yyyyMMdd").format(new Date()), decisionTree);
			    }
			    catch (Exception e){
			    	
			    }
			}
			if (result >= 1.0)
				maxAccuracy = true;
			sizeOfTestSet++;
		}
	}

}
