// Worked on this class with Keeton ; Guillermo
// And John

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 *The abstract Automaton class represents any 1D, 
 * two-state CA that evolves according to a rule represented by the Rule class
 * 
 * @author Jared Rubio
 * @version 2.0
 * @since March 1, 2022
 */
public abstract class Automaton {
	private Rule rule;
	private ArrayList <Generation> generations;
	
	/**
	 * character representation of false symbol
	 */
	public char falseSymbol = '0';
	
	/**
	 * character representation of true symbol
	 */
	public char trueSymbol = '1';
	
	/**
	 * This method creates a new rule and returns it
	 * @param ruleNum and int of the rule number
	 * @return the new rule that is created
	 * @throws RuleNumException throws exception if ruleNum is out of bounds
	 */
	protected abstract Rule createRule(int ruleNum) throws RuleNumException;
	
	/**
	 * This method constructs and Automaton using generation and rule number
	 * @param ruleNum int of the rule number
	 * @param initial generation given 
	 * @throws RuleNumException throws exception if the ruleNum is out of bounds
	 */
	protected Automaton (int ruleNum, Generation initial) throws RuleNumException {
		generations = new ArrayList<Generation>();
		generations.add(initial);
		rule = createRule(ruleNum);
		falseSymbol = '0';
		trueSymbol = '1';
	}
	/**
	 * This method creates and automaton using a ca rule number and generation
	 * @param ca Cellular Automaton given
	 * @param ruleNum the int rule number
	 * @param initial the given generation
	 * @return the Automaton that is created
	 * @throws RuleNumException throws exception if rule number is out of bounds
	 */
	public static Automaton createAutomaton(CellularAutomaton ca, int ruleNum, Generation initial) throws RuleNumException {
		
		if(ca == null)
		return null;
		
		if(ca == CellularAutomaton.ECA) {
			ElementaryAutomaton ecaAutomaton = new ElementaryAutomaton(ruleNum,initial);
			return ecaAutomaton;
		}
		else{
			TotalisticAutomaton tcaAutomaton = new TotalisticAutomaton(ruleNum,initial);
			return tcaAutomaton;
		}
	}
	/**
	 * This method constructs an automaton using a file
	 * @param filename the file name
	 * @throws FileNotFoundException throws an exception if the file isn't found
	 * @throws RuleNumException throws and exception if rule number is out of bounds
	 */
	protected Automaton (String filename) throws FileNotFoundException, RuleNumException {
		Scanner file = new Scanner(new File(filename));
		String temp;
		rule = createRule(file.nextInt());
		file.nextLine();
		temp = file.next();
		falseSymbol = temp.charAt(0);
		temp = file.next();
		trueSymbol = temp.charAt(0);
		file.nextLine();
		Generation gen = new Generation(file.next(),trueSymbol);
		generations = new ArrayList<Generation>();
		generations.add(gen);
		file.close();
		
	}
	/**
	 * This method evolves a certain steps and returns the number of steps
	 * @param numSteps number of steps
	 * @return the number of steps taken
	 */
	public int evolve(int numSteps) {
		if(numSteps <= 0) {
			return 0;
		}
		else {
		for(int i =0; i <numSteps; ++i) {
			generations.add(rule.evolve(generations.get(generations.size()-1)));
			
		}
		return numSteps;
		}
	}
	/**
	 * This method returns the current generation 
	 * @return the current generation
	 */
	public Generation getCurrentGeneration() {
		return generations.get(generations.size() - 1); 
	}
	/**
	 * This method returns the generation with the given steps
	 * @param stepNum number of steps
	 * @return the generation at the step
	 */
	public Generation getGeneration(int stepNum) {
		if(stepNum > generations.size()) {
			evolve(stepNum - generations.size() + 1);
		}
		return generations.get(stepNum);
	}
	/**
	 * This method returns the current rule number
	 * @return the current rule number
	 */
	public int getRuleNum() {
		return rule.getRuleNum();
	}
	/**
	 * This method return the number of total steps
	 * @return the total steps
	 */
	public int getTotalSteps() {
		return generations.size() - 1;
	}
	/**
	 * This method saves the current evolution into a file
	 * @param filename the name of the file
	 * @throws FileNotFoundException throws an exception if the file isn't found
	 */
	public void saveEvolution(String filename) throws FileNotFoundException {
	PrintWriter writer = new PrintWriter(filename);
	writer.print(toString());
	writer.close();
	
	}
	/**
	 * This method puts the generation into a string
	 * @return this method returns a String of the generation
	 */
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		for(int i = 0; i < generations.size(); ++i) {
			sj.add(generations.get(i).getStates(falseSymbol, trueSymbol));
		}
		return sj.toString();
	}
	/**
	 * This method returns the String representation of the rule table
	 * @return the String representation of the rule table
	 */
	public String ruleTableString() {
		return rule.ruleTableString(falseSymbol,trueSymbol);
	}
}
