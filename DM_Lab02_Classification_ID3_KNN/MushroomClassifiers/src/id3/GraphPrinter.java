package id3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * GraphViz Printer for decision trees
 * 
 * @author Theresa Brandt von Fackh
 *
 */
public class GraphPrinter {
	
	protected static final boolean DEBUG = false;
	
	private String graphvizLocation;
	private String imageType;
	private String outputFolder;
	
	/**
	 * Constructor
	 * 
	 * @param graphvizLocation Location of the graphViz program
	 * @param imageType Image file type
	 * @param outputFolder Location of the output files
	 * @throws Exception
	 */
	public GraphPrinter(String graphvizLocation, String imageType, String outputFolder) throws Exception{
		if (new File(graphvizLocation,"dot.exe").exists()){
			this.graphvizLocation = graphvizLocation;
			this.imageType = imageType;
			this.outputFolder = outputFolder;
		} else throw new Exception("No GraphViz available.");
	}
	
	/**
	 * Create DOT-File and generate image file.
	 * 
	 * @param filename Filename of the output files
	 * @param decisionTree Decision tree to visualize
	 * @throws IOException
	 */
	public void createAutomatGraphViz(String filename, Node decisionTree) throws IOException{
		// Create DOT-File
		Writer output = new BufferedWriter(new FileWriter(outputFolder+"\\"+filename+".dot"));  
        output.write(createStringGraphViz(decisionTree));  
        output.flush();
        output.close();  
		// Generate image file
        String command = graphvizLocation + "\\dot.exe" + " -T " + imageType + " \"" + outputFolder +"\\" + filename + ".dot\" -o \""+ outputFolder + "\\" + filename+"." + imageType + "\"";
        Runtime.getRuntime().exec(command);
        if (DEBUG) System.out.println("Image of the decision tree in " + new File(outputFolder).getCanonicalPath());
	}

	/**
     * Convert data into GraphViz-String-Format
     *  
     * @return	Data in GraphViz-String-Format
     */
	private String createStringGraphViz(Node decisionTree){
		String result = "digraph G {\n";
		// invisible node "node0", which is pointing to the start node
		result += "node0 [style=invis]\nnode0 -> \""+decisionTree.getClassification()+"\" [label=\"\"]\n";
		// Convert data into GraphViz-String-Format
		result += decisionTree.transistionsToStringGraphViz();
		result = result+"}";
		return result;	
	}

}
