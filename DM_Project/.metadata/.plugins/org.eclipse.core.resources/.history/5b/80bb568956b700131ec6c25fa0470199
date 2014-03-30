import java.math.BigDecimal;
import java.math.RoundingMode;


public class AssociationRule {
	
	ItemSet setAB;
	double countAB;
	ItemSet setA;
	double countA;
	ItemSet setB;
	double countB;
	double confidence = 0;
	
	public AssociationRule(ItemSet setAB, double countAB, ItemSet setA, double countA, ItemSet setB, double countB){
		this.setAB = setAB;
		this.setA = setA;
		this.setB = setB;
		this.countAB = countAB;
		this.countA = countA;
		this.countB = countB;
		confidence = this.countAB / this.countA;
	}

	public String toString(){
		return this.setAB+": Confidence("+this.setA+" => "+this.setB+") = " + this.countAB + "/" + this.countA + " = " + new BigDecimal(confidence).setScale(3,RoundingMode.HALF_UP).doubleValue();
		
	}
}
