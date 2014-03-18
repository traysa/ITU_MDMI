import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataCleaner {
		
	public String createPattern(String[] values){
		String pattern = "^[";
		for (String value : values){
			pattern += value + "|";
		}
		pattern = pattern.substring(0, pattern.lastIndexOf("|")-1) + "]$";
		pattern = pattern.re.replace("[\\/|\\[|\\-|\\[|\\\|\\]|\\{|\\}|\\(|\\)|\\*|\\+|?\\.|\\,|\\\\|\\^|\\$|#\s]]", "\\\\$&");
		System.out.println("pattern: " + pattern);
		
		return pattern;
	}
	public String[] getValues(String[][] data, Integer column, Integer threshold){
		Set<String> selection = new HashSet<String>();
		Map<String,Integer> tempSelection = new HashMap<String,Integer>();
		for (String[] line : data) {
			for (String value : line[column].trim().split(",|\\s|\\(|\\)"))
				if (!value.trim().isEmpty()){
					String valueUpper = value.trim().toUpperCase();
					if (tempSelection.containsKey(valueUpper)){
						if (tempSelection.get(valueUpper) > threshold)
								selection.add(valueUpper);
						else tempSelection.put(valueUpper,tempSelection.get(valueUpper)+1);
					}
					else {
						if (1 > threshold)
							selection.add(valueUpper);
						else tempSelection.put(valueUpper,1);
					}
				}
		}
		System.out.println(selection.size());
		System.out.println(selection.toString());
		return selection.toArray(new String[selection.size()]);
	}
	
	public static String[][] cleanStringArrayValues(String pattern, String[][] data, Integer column){
		// Create a Pattern object
	    Pattern r = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
		for (String[] line : data) {
			for(String value: line[column].split(",| ")){
			    // Now create matcher object.
				if (!value.trim().isEmpty()){
				    Matcher m = r.matcher(value.trim());
				    if (m.find( )) {
				    	value = m.group(0);
				    	System.out.println(line[column]+"; Found value: " + value);
				    } else {
				    	System.out.println(Arrays.toString(line));
				    	value = "Unknown";
				        //System.out.println("NO MATCH");
					}	
				}
			}
		}
		return data;
	}
	
	public static String[][] cleanStringValues(String pattern, String[][] data, Integer column){
		// Create a Pattern object
	    Pattern r = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
		for (String[] line : data) {
	
		    // Now create matcher object.
		    Matcher m = r.matcher(line[column].trim());
		    if (m.find( )) {
		    	line[column] = m.group(0);
		    	//System.out.println("Found value: " + m.group(0));
		    } else {
		    	System.out.println(Arrays.toString(line));
		    	line[column] = "Unknown";
		        //System.out.println("NO MATCH");
			}	
		}
		return data;
	}
	
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
	
	/*public static void main(String args[]) {
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

	}*/
	
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
