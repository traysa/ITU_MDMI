package id3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * 
 * @author Theresa Brandt von Fackh
 *
 */
public class ID3Algorithm {
	
	protected static final boolean DEBUG = false;
	
	/**
	 * Tests dataset against a decision tree and makes a predictions.
	 * Calculates the accuracy by comparing the matches of predicted and real classification.
	 * 
	 * @param decisionTree
	 * @param objects
	 * @return
	 */
	public double testData(Node decisionTree, ArrayList<ID3Object> objects){
		int errorCnt = 0;
		int successCnt = 0;
		for (ID3Object object: objects){
			if (testObject(decisionTree,object))
				successCnt++;
			else errorCnt++;
		}		
		if (DEBUG) System.out.println("error: "+errorCnt);
		if (DEBUG) System.out.println("success: "+successCnt);		
		return (double)successCnt/(successCnt+errorCnt);
	}
	
	/**
	 * Predicts the classification of a data object by a decision tree.
	 * Returns true, when the prediction matches the real classification.
	 * Otherwise false.
	 * 
	 * @param decisionTree Decision tree, on which the prediction is based
	 * @param object Object, where the classification should be predict
	 * @return Is the prediction equal to the real classification?
	 */
	public boolean testObject(Node decisionTree, ID3Object object){
		if (decisionTree.isLeaf())
			return decisionTree.getClassification().toString().equals(object.getClasslabel().toString());
		
		Object value = object.getAttributeValue(decisionTree.getClassification());
		if (decisionTree.getChildren().get(value.toString()) == null)
			return false;
		
		else
			return testObject(decisionTree.getChildren().get(value.toString()), object);

	}
	
public Node decTree(ArrayList<ID3Object> D, ArrayList<Object> attribute_List, Object classifierClass, String transition) throws Exception{
		
		// if the objects in D are all the same, then return a node labeled with the object class in D
		boolean isLeaf = true;
		ID3Object prevItem = D.get(0);
		Iterator<ID3Object> iterator = D.iterator();
		while (isLeaf && iterator.hasNext()){
			ID3Object item = iterator.next();
			if (!prevItem.equals(item))
				isLeaf = false;
		}
		if (isLeaf)
			return new Node(transition,D.get(0).getClasslabel(),true);
		
		// If the attribute_List is empty, return a node labeled with the majority class in D
		if (attribute_List.isEmpty()){
			Buckets buckets = new Buckets(D, classifierClass);
			return new Node(transition,buckets.getMajority(),true);
		}
		
		// Choose attribute with highest information gain
		ArrayList<InformationGain> informationGains = new ArrayList<InformationGain>();
		for(Object attribute: attribute_List){
			informationGains.add(new InformationGain(D, classifierClass, attribute));
		}
		Collections.sort(informationGains);
		Object splittingAttribute = informationGains.get(informationGains.size()-1).getAttribute();
		if (DEBUG) System.out.println("splittingAttribute: " + splittingAttribute);
		
		// Remove splitting attribute from the attribute List
		attribute_List.remove(splittingAttribute);	
		
		// For each outcome of the splitting attribute
		Node node = new Node(transition,splittingAttribute,false);
		// Create buckets for each characteristic of the classifier
		Buckets bucketsForClassifier = new Buckets(D, splittingAttribute);
		
		for (Object characteristic: bucketsForClassifier.getBuckets().keySet()){
			if (DEBUG) System.out.println("\tCharacteristic: " + characteristic);
			ArrayList<ID3Object> Dj = bucketsForClassifier.getBuckets().get(characteristic);
			if (Dj.isEmpty())
				return new Node(transition,bucketsForClassifier.getMajority(),true);
			else
			// Add decision tree for each characteristic to the node of the new classifier
			node.addChild(characteristic.toString(),decTree(Dj,attribute_List,classifierClass,characteristic.toString()));
		}
		return node;
	}
	
	/**
	 * Creates a decision tree
	 * 
	 * @param objects Dataset to consider
	 * @param classifierClass Classifier class
	 * @param transition Incoming transition, which indicates the previous classifier attribute (Initially "")
	 * @return
	 */
	public Node generateDecisionTree(ArrayList<ID3Object> objects, Object classifierClass, String transition){		
		// Create buckets for each classifier and sort objects into these buckets
		Buckets buckets = new Buckets(objects, classifierClass);
		
		// Iterate classifiers
		for (Object classifier : buckets.getBuckets().keySet()){
			// if a classifier contains all the objects, then the classifier is a leaf
			if (buckets.getBuckets().get(classifier).size() == buckets.getCounter())
				// if a classifier is a leaf return the classifier as node
				return new Node(transition,classifier,true);
		}
		
		// Choose attribute with highest information gain
		ArrayList<InformationGain> informationGains = new ArrayList<InformationGain>();
		for(Object attribute: objects.get(0).getAttributeList()){
			informationGains.add(new InformationGain(objects, classifierClass, attribute));
		}
		Collections.sort(informationGains);
		Object newClassifier = informationGains.get(informationGains.size()-1).getAttribute();
		
		if (DEBUG) System.out.println("New classifier: " + newClassifier);
		
		// Create node for new classifier
		Node node = new Node(transition,newClassifier,false);
		// Create buckets for each characteristic of the classifier
		Buckets bucketsForClassifier = new Buckets(objects, newClassifier);
		for (Object characteristic: bucketsForClassifier.getBuckets().keySet()){
			if (DEBUG) System.out.println("\tCharacteristic: " + characteristic);
			// Add decision tree for each characteristic to the node of the new classifier
			node.addChild(characteristic.toString(),generateDecisionTree(bucketsForClassifier.getBuckets().get(characteristic),classifierClass,characteristic.toString()));
		}
		if (DEBUG) System.out.println("\tCharacteristic: ---");
		return node;				
	}	
	
}
