import java.util.*;

import data.Class_Label;

public class kNNAlgorithm {
	
	public static int distance (Mushroom a, Mushroom b){
		ArrayList<Object> attributes = Mushroom.getAttributeList();
		int sumDiff = 0;
		for (Object attribute : attributes){
			if (a.getAttributeValue(attribute) == b.getAttributeValue(attribute))
				sumDiff++;
		}
		return sumDiff;
	}

	public static void nearestNeighbour(ArrayList<Mushroom> testSet, ArrayList<Mushroom> trainingSet){
		int row = testSet.size();
		for (Mushroom testMushroom : testSet){
			int maxDist = 0;
			Mushroom neighbour = null;
			int neighbourRow = 0;
			for (Mushroom trainingMushroom : trainingSet){
				row++;
				int distance = kNNAlgorithm.distance(testMushroom, trainingMushroom);
				if (distance >= maxDist){
					maxDist = distance;
					neighbour = trainingMushroom;
					neighbourRow = row;
				}
			}
			System.out.println("Nearest neighbour: "+neighbourRow);
			System.out.println("Prediction: "+neighbour.m_Class);
			System.out.println("Real value: "+testMushroom.m_Class);
		}
	}
	
	public static void nearestNeighbour(ArrayList<Mushroom> testSet, ArrayList<Mushroom> mushrooms, int k) throws Exception{
		int row = testSet.size();
		ArrayList<Mushroom> trainingSet = new ArrayList<Mushroom>();
		ArrayList<Neighbour> distances = new ArrayList<Neighbour>();
		for (Mushroom testMushroom : testSet){
			trainingSet = (ArrayList<Mushroom>) mushrooms.clone();
			trainingSet.remove(testMushroom);
			System.out.println("TrainingSet: "+trainingSet.size());
			for (Mushroom trainingMushroom : trainingSet){
				row++;
				int distance = kNNAlgorithm.distance(testMushroom, trainingMushroom);
				distances.add(new Neighbour(trainingMushroom,distance,row));
			}
			Collections.sort(distances);
			ArrayList<Mushroom> nearestNeighbours = new ArrayList<Mushroom>();
			int edibleCnt = 0;
			int poisonousCnt = 0;
			for (int i = 0; i < k; i++){
				Mushroom mushroom = distances.get(i).getMushroom();
				nearestNeighbours.add(mushroom);
				if (mushroom.m_Class.equals(Class_Label.edible))
					edibleCnt++;
				else if (mushroom.m_Class.equals(Class_Label.poisonous))
					poisonousCnt++;
				else throw new Exception("Error");
			}
			System.out.println("Nearest neighbours: "+nearestNeighbours.size());
			System.out.println(edibleCnt + " " + poisonousCnt);
			if (edibleCnt > poisonousCnt)
				System.out.println("Prediction: "+Class_Label.edible);
			else if (edibleCnt < poisonousCnt)
				System.out.println("Prediction: "+Class_Label.poisonous);
			else
				System.out.println("Prediction: "+Class_Label.poisonous);
			System.out.println("Real value: "+testMushroom.m_Class);
		}
	}
}

class Neighbour implements Comparator<Neighbour>, Comparable<Neighbour>{
	private Integer distance;
	private Mushroom mushroom;
	private int row;
	
	public Neighbour(Mushroom mushroom, int distance, int row){
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
	
	public Mushroom getMushroom(){
		return this.mushroom;
	}
	
	public int getDistance(){
		return this.distance;
	}
	
	public int getRow(){
		return this.row;
	}
}
