package data;

import java.util.ArrayList;

import kMean.KMeanCluster;


public class Iris implements Item {

	private float Sepal_Length;
	private float Sepal_Width;
	private float Petal_Length;
	private float Petal_Width;
	private IrisClass Class;
	
	public Iris(float sepal_length, float sepal_width, float petal_length, float petal_width, String iris_class)
	{
		this(sepal_length,sepal_width,petal_length,petal_width,ResolveIrisClass(iris_class));
	}
	
	public Iris(float sepal_length, float sepal_width, float petal_length, float petal_width, IrisClass iris_class)
	{
		this.Sepal_Length = sepal_length;
		this.Sepal_Width = sepal_width;
		this.Petal_Length = petal_length;
		this.Petal_Width = petal_width;
		this.Class = iris_class;
	}
	
	private static IrisClass ResolveIrisClass(String iris_class)
	{
		if(iris_class.equals("Iris-setosa"))
		{
			return IrisClass.Iris_setosa;
		}
		else if(iris_class.equals("Iris-versicolor"))
		{
			return IrisClass.Iris_versicolor;
		}
		else if(iris_class.equals("Iris-virginica"))
		{
			return IrisClass.Iris_virginica;
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		String result = "Iris Object --> | Sepal_Length = "+this.Sepal_Length+" | Sepal_Width = "+this.Sepal_Width+" | Petal_Length = "+this.Petal_Length + " | Petal_Width = "+this.Petal_Width + " | Class = "+this.Class;
		
		return result;
	}

	@Override
	public int compareTo(Item obj) {
		Iris iris = (Iris)obj;
		if (this.Petal_Length==iris.Petal_Length && this.Petal_Width==iris.Petal_Width && this.Sepal_Length==iris.Sepal_Length && this.Sepal_Width==iris.Sepal_Width)
			return 1;
		else
			return 0;
	}

	@Override
	public float distance(Item obj) {
		Iris iris = (Iris)obj;
		float distance = 0;
		distance += Math.pow(this.Petal_Length-iris.Petal_Length,2);
		distance += Math.pow(this.Petal_Width-iris.Petal_Width,2);
		distance += Math.pow(this.Sepal_Length-iris.Sepal_Length,2);
		distance += Math.pow(this.Sepal_Width-iris.Sepal_Width,2);
		return distance;
	}

	@Override
	public Item mean(ArrayList<Item> clusterMembers) {
		int size = clusterMembers.size();
		float sepal_Length = 0;
		float sepal_Width = 0;
		float petal_Length = 0;
		float petal_Width = 0;
		for (Item item: clusterMembers){
			Iris iris = (Iris)item;
			sepal_Length += iris.Sepal_Length;
			sepal_Width += iris.Sepal_Width;
			petal_Length += iris.Petal_Length;
			petal_Width += iris.Petal_Width;
		}
		return new Iris(sepal_Length/size,sepal_Width/size,petal_Length/size,petal_Width/size,"");
	}
	
	@Override
	public String analyzeClusters(ArrayList<KMeanCluster> clusters, int k){
		String result = "";
		for (int i = 0; i<k; i++){
			ArrayList<Item> clusterMembers = clusters.get(i).ClusterMembers;
			int size = clusterMembers.size();
			float Iris_setosaCnt = 0;
			float Iris_versicolorCnt = 0;
			float Iris_virginicaCnt = 0;
			for (Item item: clusterMembers){
				Iris iris = (Iris)item;
				if(iris.Class.equals(IrisClass.Iris_setosa))
					Iris_setosaCnt++;
				else if(iris.Class.equals(IrisClass.Iris_versicolor))
					Iris_versicolorCnt++;
				else if(iris.Class.equals(IrisClass.Iris_virginica))
					Iris_virginicaCnt++;
			}
			result += "\n=========================================\nCluster "+(i+1)+"\nIris-setosa: "+Iris_setosaCnt/size*100+"\nIris-versicolor: "+Iris_versicolorCnt/size*100+"\nIris-virginica: "+Iris_virginicaCnt/size*100;
		}
		return result;
	}

}
