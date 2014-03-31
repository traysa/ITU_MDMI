package data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class DataResolver {	
	
	public static Float resolveFloat(String floatVal){
		try {
			return Float.parseFloat(floatVal.replace(",","."));
		} catch(Exception ex){
			return null;
		}
	}
	
	public static Double resolveDouble(String doubleVal){
		try {
			return Double.parseDouble(doubleVal.replace(",","."));
		} catch(Exception ex){
			return null;
		}
	}
	
	public static Integer resolveInteger(String intVal){
		try {
			return Integer.parseInt(intVal);
		} catch(Exception ex){
			return null;
		}
	}
	
	public static Boolean resolveBoolean(String boolVal){
		if(boolVal.toUpperCase().equals("YES"))
			return true;
		else if(boolVal.toUpperCase().equals("NO"))
			return false;
		return null;
	}
	
	public static FavSQLServ resolveSQLServ(String favSqlServ)
	{
		if(favSqlServ.toUpperCase().equals("MSSQL"))
			return FavSQLServ.MSSQL;
		else if(favSqlServ.toUpperCase().equals("MYSQL"))
			return FavSQLServ.MySQL;
		else if(favSqlServ.toUpperCase().equals("ORACLE"))
			return FavSQLServ.Oracle;
		else if(favSqlServ.toUpperCase().equals("POSTGRESQL"))
			return FavSQLServ.PostgreSQL;
		return null;
	}
	
	public static FavColor resolveColor(String favColor)
	{
		if(favColor.toUpperCase().equals("BLUE"))
			return FavColor.Blue;
		else if(favColor.toUpperCase().equals("GREEN"))
			return FavColor.Green;
		else if(favColor.toUpperCase().equals("RED"))
			return FavColor.Red;
		else if(favColor.toUpperCase().equals("YELLOW"))
			return FavColor.Yellow;
		else if(favColor.toUpperCase().equals("BLACK"))
			return FavColor.Black;
		else if(favColor.toUpperCase().equals("ORANGE"))
			return FavColor.Orange;
		return null;
	}
	
	public static FavAnimal resolveAnimal(String favAnimal)
	{
		if(favAnimal.toUpperCase().equals("ZEBRA"))
			return FavAnimal.Zebra;
		else if(favAnimal.toUpperCase().equals("ELEPHANT"))
			return FavAnimal.Elephant;
		else if(favAnimal.toUpperCase().equals("ASPARAGUS"))
			return FavAnimal.Asparagus;
		return null;
	}
	
	public static OS resolveOS(String os)
	{
		if(os.toUpperCase().equals("OSX"))
			return OS.OSX;
		else if(os.toUpperCase().equals("WINDOWS"))
			return OS.Windows;
		else if(os.toUpperCase().equals("LINUX"))
			return OS.Linux;
		return null;
	}

	public static HashSet<ProgLangs> resolveProgLangs(String progLangs)
	{
		String[] temp = progLangs.split(",| ");
		HashSet<String> progLangsStr = new HashSet<String>();
		for (String i : temp)
			progLangsStr.add(i);
		HashSet<ProgLangs> progLangsSet = new HashSet<ProgLangs>();
		if(progLangsStr.contains("C#"))
			progLangsSet.add(ProgLangs.CSharp);
		else if(progLangsStr.contains("C++"))
			progLangsSet.add(ProgLangs.CPlusPlus);
		else if(progLangsStr.contains("C"))
			progLangsSet.add(ProgLangs.C);
		else if(progLangsStr.contains("Java"))
			progLangsSet.add(ProgLangs.Java);
		else if(progLangsStr.contains("Javascript"))
			progLangsSet.add(ProgLangs.Javascript);
		else if(progLangsStr.contains("F#"))
			progLangsSet.add(ProgLangs.FSharp);
		else if(progLangsStr.contains("OBJECTIVE-C"))
			progLangsSet.add(ProgLangs.OBJC);
		else if(progLangsStr.contains("PHP"))
			progLangsSet.add(ProgLangs.PHP);
		else if(progLangsStr.contains("Python"))
			progLangsSet.add(ProgLangs.Python);
		else if(progLangsStr.contains("RUBY"))
			progLangsSet.add(ProgLangs.RUBY);
		else if(progLangsStr.contains("Scala"))
			progLangsSet.add(ProgLangs.Scala);
		return progLangsSet;
	}


}
