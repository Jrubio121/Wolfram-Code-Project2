// Worked on this class with Keeton ; Guillermo
// And John

/**
 * RuleNumExceptions throws a custom exception based on the Range of ruleNum
 *  
 * @author Jared Rubio
 * @version 2.0
 * @since March 1, 2022
 */
public class RuleNumException extends Exception{

	private static final long serialVersionUID = 1L;
	
	/**
	 * This method us a constructor for an object used to check if the int is in range
	 * 
	 * @param min is an int representing the minimum range
	 * @param max is an int representing the maximum range 
	 */
	public RuleNumException(int min, int max) {
		
		super("ruleNum is outside the range " + "[" + min + ", " + max + "].");
	}

}
