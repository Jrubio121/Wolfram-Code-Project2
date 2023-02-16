// Worked on this class with Keeton ; Guillermo
// And John

/**
 * ElementaryRule represents any one of the 256 rules that govern the evolution of elementary CAs.
 *
 * @author Jared Rubio
 * @version 2.0
 * @since March 1, 2022
 */
import java.util.StringJoiner;

public class ElementaryRule extends Rule {

	protected final boolean[] listRule;
	
	/**
	 * This method constructs an elementary Rule using rule number
	 * @param ruleNum the int of the rule number
	 * @throws RuleNumException throws an exception when rule num is out of bounds
	 */
	protected ElementaryRule(int ruleNum) throws RuleNumException{
		super(ruleNum);
		
		if(ruleNum < 0 || ruleNum > 255){
		throw new RuleNumException(0,255);
		}
		Generation temp = new Generation(binaryRule, '1');
		listRule = temp.getStates();
	}

	/**
	 * This method gets the neighborhood based on the index and generation
	 * @param gen the initial generation
	 * @param idx the index int
	 * @return the neighborhood boolean array
	 */
	public boolean[] getNeighborhood(int idx, Generation gen) {
		boolean[] neighborhood = new boolean[3];
		if(idx>0) {
			neighborhood[0] = gen.getState(idx - 1);
		}
		else if(idx == 0) {
			neighborhood[0]=gen.getState(gen.size()-1);
		}
		neighborhood[1] = gen.getState(idx);
		
		if(idx == gen.size() - 1) {
			neighborhood[2] = gen.getState(0);
			
		}
		else if(idx < gen.size() - 1) {
			neighborhood[2] = gen.getState(idx+1);
		}
		
		return neighborhood;
	}
	/**
	 * This method evolves the neighborhood and returns true or false based on the state
	 * @param neighborhood boolean array
	 * @return returns true or false based on if the evolution passed
	 */
	public boolean evolve (boolean[] neighborhood) {
		int [] test = new int[]{111,110,101,100,11,10,1,000};
		
		char next = ' ';
		String neighborhoodString = "";
		
		for(int i = 0; i < neighborhood.length; ++i) {
			if(neighborhood[i] == true) {
				neighborhoodString += "1";
			}
			else if(neighborhood[i] == false) {
				neighborhoodString += "0";
			}
		}
		
		int val = Integer.valueOf(neighborhoodString);
		
		for(int i = 0; i < test.length; ++i) {
			if(val == test[i]) {
				next = binaryRule.charAt(i);
			}
		}
		
		if(next == '0') {
			return false;
		}
		else {
			return true;
		}
		
	}

	/**
	 * This method returns a string representation of rule table
	 * @param falseSymbol character representation of false symbol
	 * @param trueSymbol character representation of true symbol
	 */
	public String ruleTableString(char falseSymbol, char trueSymbol) {
		String start = "111 110 101 100 011 010 001 000";
		String binary = "";
		StringJoiner sj = new StringJoiner("   ");
		for(int i = 0; i < start.length(); ++i) {
			if(start.charAt(i) == ' ') {
				binary = binary + " ";
			}
			else if(start.charAt(i) == '1') {
				binary = binary + trueSymbol;
			}
			else {
				binary = binary + falseSymbol;
			}
		}
		for(int i =0; i<8; ++i) {
			if(listRule[i] == true) {
				sj.add("" + trueSymbol);
			}
			else {
				sj.add(""+ falseSymbol);
			}
		}
		return binary + System.lineSeparator() + " " + sj.toString() + " ";
	}
	
	
}

