package kNN;

import id3.ID3Object;

import java.util.Comparator;

/**
 * 
 * @author Theresa
 *
 */
public class Neighbour implements Comparator<Neighbour>, Comparable<Neighbour>{
	private Integer distance;
	private ID3Object mushroom;
	private int row;
	
	public Neighbour(ID3Object mushroom, int distance, int row){
		this.mushroom = mushroom;
		this.distance = distance;
		this.row = row;
	}
	
	@Override
	public int compare(Neighbour arg0, Neighbour arg1) {
		if (arg0.distance > arg1.distance){
			return 1;
		}
		else if (arg0.distance < arg1.distance){
			return -1;
		}
		else return 0;
	}

	@Override
	public int compareTo(Neighbour arg0) {
		return (this.distance).compareTo(arg0.distance);
	}
	
	public ID3Object getMushroom(){
		return this.mushroom;
	}
	
	public int getDistance(){
		return this.distance;
	}
	
	public int getRow(){
		return this.row;
	}
	
	@Override
	public String toString(){
		return distance.toString();
	}

}
