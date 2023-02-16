/**
 * TotalisticAutomaton represent any 1D, 
 * two-state CAs that evolve according to the rules represented by totalisticRule
 *  
 * @author Jared Rubio
 * @version 2.0
 * @since March 1, 2022
 */
import java.io.FileNotFoundException;


public class TotalisticAutomaton extends Automaton {

	/**
	 * This is a protected method used to construct the totatlisticAutomaton object
	 * 
	 * @param ruleNum is an int representing the rule number
	 * @param initial is a Generation object used to construct totalsticAutomaton
	 * @throws RuleNumException if the ruleNum is out of bounds
	 */
	protected TotalisticAutomaton(int ruleNum, Generation initial) throws RuleNumException {
		super(ruleNum, initial);
	}
	
	/**
	 * This is a protected method use to construct a totalisticAutomaton object using a file
	 * 
	 * @param filename is a String representing the name of the file given
	 * @throws FileNotFoundException if the file is not found
	 * @throws RuleNumException if the ruleNum is out of bounds
	 */
	protected TotalisticAutomaton(String filename) throws FileNotFoundException, RuleNumException {
		super(filename);

	}

	/**
	 * This is a protected method that creates a new Rule using ruleNum
	 * 
	 * @param ruleNum is an int representing the rule number
	 * @throws RuleNumException if the ruleNum is out of bounds
	 * @return a new rule created with ruleNum 
	 */
	@Override
	protected Rule createRule(int ruleNum) throws RuleNumException {
		Rule rule = new TotalisticRule(ruleNum);
		return rule;
	}

}
