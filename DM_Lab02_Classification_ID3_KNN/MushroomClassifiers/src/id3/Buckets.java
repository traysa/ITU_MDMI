package id3;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Theresa Brandt von Fackh
 *
 */
public class Buckets {
	private int counter = 0;
	private HashMap<Object,ArrayList<ID3Object>> buckets = new HashMap<Object,ArrayList<ID3Object>>();	
	
	public Buckets(ArrayList<ID3Object> objects, Object classifier) {
		createBuckets(objects, classifier);
	}

	/**
	 * Creates buckets for each classifier.
	 * Sorts the objects into the buckets, depending on their classifier.
	 * 
	 * @param objects Objects to sort
	 * @param classifier Classifiers
	 * @return
	 */
	private void createBuckets(ArrayList<ID3Object> objects, Object classifier){

		for(ID3Object object : objects){
			Object attributeValue = object.getAttributeValue(classifier).toString();
			if (this.buckets.containsKey(attributeValue)){
				// If bucket for classifier already exists, add the object to the bucket of that classifier
				ArrayList<ID3Object> list = this.buckets.get(attributeValue);
				list.add(object);
				this.buckets.put(attributeValue,list);
			}
			else
			{
				// If bucket for classifier does not exist, create a new bucket for the classifier and add the object to it
				ArrayList<ID3Object> list = new ArrayList<ID3Object>();
				list.add(object);
				this.buckets.put(attributeValue,list);
			}
			this.counter++;
		}
	}
	
	public int getCounter() {
		return counter;
	}
	
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public HashMap<Object, ArrayList<ID3Object>> getBuckets() {
		return buckets;
	}
	
	public void setBuckets(HashMap<Object, ArrayList<ID3Object>> buckets) {
		this.buckets = buckets;
	}
	
	public Object getMajority(){
		Object result = null;
		int max =0;
		for (Object key : buckets.keySet()){
			if(buckets.get(key).size() > max){
				max = buckets.get(key).size();
				result = key;
			}
		}
		return result;
	}
}
