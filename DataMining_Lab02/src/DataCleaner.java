import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataCleaner {
	
	public static String cleanAnimal(String animal){
		String pattern = "(Asparagus|Elephant|Zebra){1}";
		
		// Create a Pattern object
	    Pattern r = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);

	    // Now create matcher object.
	    Matcher m = r.matcher(animal);
	    if (m.find( )) {
	    	System.out.println("Found value: " + m.group(0));
	    	return m.group(0);
	    } else {
	        System.out.println("NO MATCH");
	        return "Unknown";
		}	
	}
	
	public static String cleanProgLangugaes(String progLanguages){
		String[] languages = progLanguages.split(",");
		String pattern = "^(C|C#|F#|Java|Python|Prolog|Ruby|C\\+\\+|Cobol|Obj-C|SQL){1}$";
		
		// Create a Pattern object
	    Pattern r = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);

	    // Now create matcher object.
	    Set<String> result = new HashSet<String>();
	    for (String language:languages){
		    Matcher m = r.matcher(language);
		    if (m.find( )) {
		    	System.out.println("Found value: " + m.group(0));
		    	result.add(m.group(0));
		    } else {
		        System.out.println("NO MATCH");
		        result.add("Unknown");
			}	
	    }
	    return result.toString();
	}
	
	public static void main(String args[]) {
		try {
			String[][] data = CSVFileReader.read("Data_Mining_Student_DataSet_Spring_2013.csv", false);
			
			String[] headers = data[0];
			int headersCnt = headers.length;
			System.out.println("headersCnt: " + headersCnt);
			int c = 0;
			for (String[] line : data) {
				System.out.println(c++);
				//Delete invalid rows
				if (line.length != headersCnt){
					System.out.println("INVALID ROW: " + line); 
				} else {
					
					// Clean Animal
					//System.out.println("input: " + line[7].toString());
					//System.out.println("output: " + cleanAnimal(line[7].toString()));
					//line[7] = cleanAnimal(line[7].toString());
					
					// Clean Prog_Languages
					//System.out.println("input: " + line[5].toString());
					//System.out.println("output: " + cleanProgLangugaes(line[5].toString()));
					//line[5] = cleanProgLangugaes(line[5].toString());
	
					System.out.println(Arrays.toString(line));
				}
			}
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}

	}
	
	private String formatDate(String date) {
		
		/*
		// String to be scanned to find the pattern.
	    String pattern = "(.*)(\\d+)(.*)";

	    // Create a Pattern object
	    Pattern r = Pattern.compile(pattern);

	    // Now create matcher object.
	    Matcher m = r.matcher(line);
	    if (m.find( )) {
	    	System.out.println("Found value: " + m.group(0) );
	        System.out.println("Found value: " + m.group(1) );
	        System.out.println("Found value: " + m.group(2) );
	    } else {
	        System.out.println("NO MATCH");
	    }
		*/
		
		
		SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yy"); 
		//String input = args.length == 0 ? "1818-11-11" : args[0]; 
		System.out.print(date + " Parses as ");
		Date t;
		try {
			t = ft.parse(date); 
			System.out.println(t); 
			return t.toString();
		} catch (ParseException e) { 
	          System.out.println("Unparseable using " + ft); 
	    }
		return null;
	}
	
}
