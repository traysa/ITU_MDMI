import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import data.Class_Label;


public class ID3Algorithm {
	
	public double testData(Node decisionTree,ArrayList<Mushroom> mushrooms){
		int errorCnt = 0;
		int successCnt = 0;
		for (Mushroom mushroom: mushrooms){
			if (testMushroom(decisionTree,mushroom))
				successCnt++;
			else errorCnt++;
		}		
		System.out.println("error: "+errorCnt);
		System.out.println("success: "+successCnt);		
		return (double)successCnt/(successCnt+errorCnt);
	}
	
	public boolean testMushroom(Node decisionTree, Mushroom mushroom){
		if (decisionTree.isLeaf())
			return decisionTree.getClassification().toString().equals(mushroom.m_Class.toString());
		Object value = mushroom.getAttributeValue(decisionTree.getClassification());
		if (decisionTree.getChildren().get(value.toString()) == null){
			return false;
		}
		if (decisionTree.getChildren().get(value.toString()).isLeaf()){
			return decisionTree.getChildren().get(value.toString()).getClassification().toString().equals(mushroom.m_Class.toString());
		}
		else
		{
			return testMushroom(decisionTree.getChildren().get(value.toString()), mushroom);
		}
	}
	
	public Node generateDecisionTree(ArrayList<Mushroom> mushrooms, Object classifierAttribute){
		
		myClass buckets = this.bucketing(mushrooms, classifierAttribute);
		boolean leaf = false;
		Iterator<Object> classifiers = buckets.getBuckets().keySet().iterator();
		String leafName = "";
		Object classifier = null;
		while (!leaf && classifiers.hasNext()){
			classifier = classifiers.next();
			leafName = (String) classifier;
			leaf = buckets.getBuckets().get(classifier).size() == buckets.getCounter();
		}
		
		if (leaf){
			return new Node(leafName,classifier,true);
		}
		else
		{
			ArrayList<InformationGain> informationGains = new ArrayList<InformationGain>();
			for(Object attribute: Mushroom.getAttributeList()){
				informationGains.add(new InformationGain(attribute,gain(mushrooms,Class_Label.class,attribute)));
			}
			Collections.sort(informationGains);
			Object attribute = informationGains.get(informationGains.size()-1).getAttribute();
			
			System.out.println(attribute);
			Node node = new Node(attribute.toString(),attribute,false);
			myClass buckets1 = this.bucketing(mushrooms, attribute);
			for (Object attribute1: buckets1.getBuckets().keySet()){
				//System.out.println(attribute1);
				node.addChild(attribute1.toString(),generateDecisionTree(buckets1.getBuckets().get(attribute1),classifierAttribute));
			}
			return node;			
		}		
	}	
	
	private double info(ArrayList<Mushroom> mushrooms, Object classifierAttribute){
		myClass buckets = bucketing(mushrooms, classifierAttribute);
		double result = 0.0;
		for (ArrayList<Mushroom> bucket: buckets.getBuckets().values()){
			double temp = ((double)bucket.size()/(double)buckets.getCounter());
			result -= temp*log(temp,2);
		}
		return result;
	}
	
	
	private double info_A(ArrayList<Mushroom> mushrooms, Object classifierAttribute, Object attribute){	
		myClass buckets = bucketing(mushrooms, attribute);
		double result = 0.0;
		for(Object classifier: buckets.getBuckets().keySet()){
			ArrayList<Mushroom> selection = buckets.getBuckets().get(classifier);
			double ratio = (double)selection.size()/(double)buckets.getCounter();
			double info = (double)info(selection,classifierAttribute);
			result += ratio*info;
		}
		return result;
	}

	public double gain(ArrayList<Mushroom> mushrooms, Object classifierAttribute, Object attribute){
		double info = info(mushrooms,classifierAttribute);
		double info_A = info_A(mushrooms,classifierAttribute,attribute);
		return info-info_A;	
	}
	
	private double log(double a, double b){	
		return Math.log(a)/Math.log(b);
	}
	
	private myClass bucketing(ArrayList<Mushroom> mushrooms, Object classifier){
		HashMap<Object,ArrayList<Mushroom>> buckets = new HashMap<Object,ArrayList<Mushroom>>();	
		int counter = 0;
		for(Mushroom mushroom : mushrooms){
			Object attributeValue = mushroom.getAttributeValue(classifier).toString();
			if (buckets.containsKey(attributeValue)){
				ArrayList<Mushroom> list = buckets.get(attributeValue);
				list.add(mushroom);
				buckets.put(attributeValue,list);
			}
			else
			{
				ArrayList<Mushroom> list = new ArrayList<Mushroom>();
				list.add(mushroom);
				buckets.put(attributeValue,list);
			}
			counter++;
		}
		return new myClass(counter, buckets);
	}
	
}


class myClass{
	private int counter;
	private HashMap<Object,ArrayList<Mushroom>> buckets;
	
	public myClass(int counter, HashMap<Object, ArrayList<Mushroom>> buckets) {
		this.counter = counter;
		this.buckets = buckets;
	}

	public int getCounter() {
		return counter;
	}
	
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public HashMap<Object, ArrayList<Mushroom>> getBuckets() {
		return buckets;
	}
	
	public void setBuckets(HashMap<Object, ArrayList<Mushroom>> buckets) {
		this.buckets = buckets;
	}
}


class Node {
	
	private String label;
	private Object classification;
	private HashMap<String,Node> children;
	private boolean leaf;
	
	public Node(String label, Object classification, boolean leaf){
		this.label = label;
		this.classification = classification;
		this.leaf = leaf;
		this.children = new HashMap<String,Node>();
	}
	
	public void addChild(String value, Node child){
		this.children.put(value, child);
	}
	
	public HashMap<String,Node> getChildren(){
		return children;
	}
	
	public String getLabel() {
		return label;
	}
	
	public Object getClassification(){
		return classification;
	}
	
	public boolean isLeaf(){
		return leaf;
	}
}

class InformationGain implements Comparator<InformationGain>, Comparable<InformationGain>{
	private Object attribute;
	private Double gain;

	public InformationGain(Object attribute, Double gain) {
		super();
		this.gain = gain;
		this.attribute = attribute;
	}

	@Override
	public int compareTo(InformationGain arg0) {
		return (this.gain).compareTo(arg0.gain);
	}

	@Override
	public int compare(InformationGain arg0, InformationGain arg1) {
		if (arg0.gain > arg1.gain){
			return 1;
		}
		else if (arg0.gain < arg1.gain){
			return -1;
		}
		else return 0;
	}
	
	public double getGain() {
		return gain;
	}
	
	public void setGain(double gain) {
		this.gain = gain;
	}
	
	public Object getAttribute() {
		return attribute;
	}
	
	public void setAttribute(Object attribute) {
		this.attribute = attribute;
	}

	@Override
	public String toString() {
		return attribute + ": " + gain;
	}	
	
}