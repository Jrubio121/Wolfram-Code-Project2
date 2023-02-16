/**
 * ElementaryAutomaton represents any 1D, 
 * two-state CAs that evolve according to the rules represented by ElementaryRule 
 *  
 * @author Jared Rubio
 * @version 2.0
 * @since March 1, 2022
 */
import java.io.FileNotFoundException;

public class ElementaryAutomaton extends Automaton{

	/**
	 * This method constructs an ElementaryAutomaton using rulenum and generation
	 * @param ruleNum int of the rule number
	 * @param initial the initial generation given
	 * @throws RuleNumException throws exception if rule number is out of bounds
	 */
	protected ElementaryAutomaton(int ruleNum, Generation initial) throws RuleNumException {
		super(ruleNum, initial);
	}
	/**
	 * This method constructs an ElementaryAutomaton using a file
	 * @param filename the String file name
	 * @throws FileNotFoundException throws exception if the file isn't found
	 * @throws RuleNumException throws exception if rule number is out of bounds
	 */
	protected ElementaryAutomaton(String filename) throws FileNotFoundException, RuleNumException {
		super(filename);
	}

	/**
	 * This method creates a rule using rule number
	 * @param ruleNum the rule number int
	 * @throws RuleNumException throws an exception if rule number is out of bounds
	 */
	@Override
	protected Rule createRule(int ruleNum) throws RuleNumException {
		Rule rule = new ElementaryRule(ruleNum);
		return rule;
	}

	
}
