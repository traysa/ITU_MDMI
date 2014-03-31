package frequentPattern.data;

import java.util.Arrays;



/***
 * The ItemSet class is used to store information concerning a single transaction.
 * Should not need any changes.
 * @author andershh, jang
 *
 */
public class ItemSet {
	
	/***
	 * The PRIMES array is internally in the ItemSet-class' hashCode method
	 */
	private static final int[] PRIMES = { 2, 3, 5, 7, 11, 13, 17, 23, 27, 31, 37 };
    private final Integer[] set;

    /***
     * Creates a new instance of the ItemSet class.
     * @param set Transaction content
     */
    public ItemSet( Integer[] set ) {
        this.set = set;
    }

    @Override
    /**
     * hashCode functioned used internally in Hashtable
     */
    public int hashCode() {
        int code = 0;
        for (int i = 0; i < getSet().length; i++) {
            code += getSet()[i] * PRIMES[i];
        }
        return code;
    }

    
    @Override
    /**
     * Used to determine whether two ItemSet objects are equal
     */
    public boolean equals( Object o ) {
        if (!(o instanceof ItemSet)) {
            return false;
        }
        ItemSet other = (ItemSet) o;
        if (other.getSet().length != this.getSet().length) {
            return false;
        }
        for (int i = 0; i < getSet().length; i++) {
            if (getSet()[i] != other.getSet()[i]) {
                return false;
            }
        }
        return true;
    }

	@Override
	public String toString() {
		return Arrays.toString(getSet());
	}

	public Integer[] getSet() {
		return set;
	}
    
}
