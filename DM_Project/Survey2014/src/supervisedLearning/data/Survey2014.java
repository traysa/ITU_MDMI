package supervisedLearning.data;

import java.util.ArrayList;

import data.FavColor;
import supervisedLearning.id3.ID3Object;

public class Survey2014 implements ID3Object{
	
	/**
	 * The attribute to built a classifier for.
	 */
	private Object s2014_Class;
//	public Integer s2014_age;
//	public Integer s2014_prog_skill;
//	public Double s2014_uni_yrs;
//	public OS s2014_os;
//	public ProgLangs s2014_progLangs;
//	public Integer s2014_engSkill;
//	public FavAnimal s2014_favAnimal;
//	public Boolean s2014_MoreMtns;
	public Boolean s2014_winter;
	public FavColor s2014_favColor;
//	public Boolean s2014_neuralNetwork;
//	public Boolean s2014_vectorMachine;
//	public Boolean s2014_sql;
//	public FavSQLServ s2014_favSQLServ;
//	public Boolean s2014_apriori;

	@Override
	public ArrayList<Object> getAttributeList() {
		ArrayList<Object> attributes = new ArrayList<Object>();
//		attributes.add("s2014_age");
//		attributes.add("s2014_prog_skill");
//		attributes.add("s2014_uni_yrs");
//		attributes.add("s2014_os");
//		attributes.add("s2014_progLangs");
//		attributes.add("s2014_engSkill");
//		attributes.add("s2014_favAnimal");
//		attributes.add("s2014_MoreMtns");
		attributes.add("s2014_winter");
		attributes.add("s2014_favColor");
//		attributes.add("s2014_neuralNetwork");
//		attributes.add("s2014_vectorMachine");
//		attributes.add("s2014_sql");
//		attributes.add("s2014_favSQLServ");
//		attributes.add("s2014_apriori");
		return attributes;
	}

	@Override
	public Object getAttributeValue(Object Attribute) throws Exception {
		String attr = Attribute.toString().replaceAll("_1", "");
		if(attr.equals("s2014_Class"))
		{
			return this.s2014_Class;
		}
//		if(attr.equals("s2014_age"))
//		{
//			return this.s2014_age;
//		}		
//		if(attr.equals("s2014_prog_skill"))
//		{
//			return this.s2014_prog_skill;
//		}
//		if(attr.equals("s2014_uni_yrs"))
//		{
//			return this.s2014_uni_yrs;
//		}
//		if(attr.equals("s2014_os"))
//		{
//			return this.s2014_os;
//		}
//		if(attr.equals("s2014_progLangs"))
//		{
//			return this.s2014_progLangs;
//		}
//		if(attr.equals("s2014_engSkill"))
//		{
//			return this.s2014_engSkill;
//		}
//		if(attr.equals("s2014_favAnimal"))
//		{
//			return this.s2014_favAnimal;
//		}
//		if(attr.equals("s2014_MoreMtns"))
//		{
//			return this.s2014_MoreMtns;
//		}
		if(attr.equals("s2014_winter"))
		{
			return this.s2014_winter;
		}
		if(attr.equals("s2014_favColor"))
		{
			return this.s2014_favColor;
		}
//		if(attr.equals("s2014_neuralNetwork"))
//		{
//			return this.s2014_neuralNetwork;
//		}
//		if(attr.equals("s2014_vectorMachine"))
//		{
//			return this.s2014_vectorMachine;
//		}
//		if(attr.equals("s2014_sql"))
//		{
//			return this.s2014_sql;
//		}
//		if(attr.equals("s2014_favSQLServ"))
//		{
//			return this.s2014_favSQLServ;
//		}
//		if(attr.equals("s2014_apriori"))
//		{
//			return this.s2014_apriori;
//		}
		
		throw new Exception(Attribute+" is not recognized");
	}

	@Override
	public Object getClasslabel() {
		return this.s2014_Class;
	}

	@Override
	public void setClasslabel(Object classLabel) {
		this.s2014_Class = classLabel;
	}

}