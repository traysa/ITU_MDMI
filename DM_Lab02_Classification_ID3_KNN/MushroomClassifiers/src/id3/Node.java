package id3;

import java.util.HashMap;

/**
 * Represents a node in a decision tree
 * 
 * @author Theresa Brandt von Fackh
 *
 */
public class Node {
	
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
	
	/**
     * Convert node into GraphViz-String-Format
     *  
     * @return GraphViz-String-Format
	 */
	public String nodeToStringGraphViz(){
		String result = "";
		if (!this.getClassification().toString().isEmpty()){
			String nodeName = this.getClassification().toString();
			if (this.getClassification().toString().contains("."))
				nodeName = this.getClassification().toString().substring(this.getClassification().toString().indexOf(".")+1);
			result = "\""+this.getClassification() + "\"[label=\""+nodeName+"\"";
			if (!this.isLeaf())
				result += ",peripheries=2";
			result += "]\n";
		}
		return result;		
	}
	
	/**
     * Convert branches into GraphViz-String-Format
     *  
     * @return GraphViz-String-Format
	 */
	public String transistionsToStringGraphViz(){
		String node = nodeToStringGraphViz();
		for (String key : children.keySet()){
			// <Start node> ->
			node += "\""+this.getClassification() + "\" -> ";
			// <Destination node> [label="<description of the branch>"]
			node += "\""+children.get(key).getClassification() + "\" [label=\""+children.get(key).getLabel()+"\"]\n";
			node += children.get(key).transistionsToStringGraphViz();
		}
		return node;		
	}
}
