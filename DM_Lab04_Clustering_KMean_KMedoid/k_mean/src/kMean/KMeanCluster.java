package kMean;
import java.util.ArrayList;

import data.Item;

//ToDo: Compute cluster mean based on cluster members.
public class KMeanCluster {

	public ArrayList<Item> ClusterMembers;
	
	public KMeanCluster()
	{
		this.ClusterMembers = new ArrayList<Item>();
	}
	
	@Override
	public String toString() {
		String toPrintString = "-----------------------------------CLUSTER START------------------------------------------" + System.getProperty("line.separator");
		
		for(Item i : this.ClusterMembers)
		{
			toPrintString += i.toString() + System.getProperty("line.separator");
		}
		toPrintString += "-----------------------------------CLUSTER END-------------------------------------------" + System.getProperty("line.separator");
		
		return toPrintString;
	}

}
