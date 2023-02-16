// Worked on this class with Keeton ; Guillermo
// And John

/**
 * TotalisticRule represents any one of the 64 rules that govern the evolution of 1D
 *  
 * @author Jared Rubio
 * @version 2.0
 * @since March 1, 2022
 */
import java.util.StringJoiner;

public class TotalisticRule extends Rule{
	
	protected final boolean[] listRule;
	
	/**
	 * This is a protected method used to construct the totatlisticRule object
	 * 
	 * @param ruleNum is and int representing the rule number
	 * @throws RuleNumException if the ruleNum is out of bounds
	 */
	protected TotalisticRule(int ruleNum) throws RuleNumException{
		super(ruleNum);
		
		if(ruleNum < 0 || ruleNum > 63) {
			throw new RuleNumException(0, 63);
		}
		String mexico = binaryRule.substring(2,binaryRule.length());
		Generation temp = new Generation(mexico, '1');
		listRule = temp.getStates();
			
	}

	/**
	 * This method returns the next state of a cell in a neighborhood with the given 
	 * states by applying the totalistic rule.
	 * 
	 * @param gen is a Generation object that we evolve
	 * @param idx is an int representing the index
	 * @return the next state of a cell in a neighborhood with the given states by applying the totalistic rule.
	 */
	@Override
	public boolean[] getNeighborhood(int idx, Generation gen) {
		return getNeighborhoodByRadius(idx,2,gen);
	}

	/**
	 * This method returns true or false depending on the evolution of neighborhood
	 * @param neighborhood boolean array
	 * @return true or false depending on the evolution of neighborhood
	 */
	@Override
	public boolean evolve(boolean[] neighborhood) {
		int count = 0;
		
		for(int i = 0; i < neighborhood.length; ++i) {
			if(neighborhood[i] == true) {
				++count;
			}
		}
		char  minecraft = binaryRule.charAt(5 - count+2);
		
		if(minecraft == '0') {
			return false;
		}
		
		else
			return true;
		
	}

	/**
	 * This method returns a two-line representation of the totalistic rule table
	 * 
	 * @param falseSymbol a character representation of the false symbol
	 * @param trueSymbol a character representation of the true symbol
	 * @return a two-line representation of the totalistic rule table
	 */
	@Override
	public String ruleTableString(char falseSymbol, char trueSymbol) {
		StringJoiner sj = new StringJoiner(" ");
        String binary = "5 4 3 2 1 0";
      for(int i = 0; i < listRule.length;++i ) {
    	  if(listRule[i]) {
    		  sj.add("" + trueSymbol);
    	  }
    	  else {
    		  sj.add("" + falseSymbol);
    	  }
    	  
      }
      return binary + System.lineSeparator() + sj.toString();
       
	}
	
}
