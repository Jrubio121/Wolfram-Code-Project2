// Worked on this class with Keeton ; Guillermo
// And John

/** 
 * Rule class represents any rule that governs the evolution of a 1D, two-state CA.
 *  
 * @author Jared Rubio
 * @version 2.0
 * @since March 1, 2022
 */

public abstract class Rule {
	private int ruleNum;
	protected String binaryRule;
	
	/**
	 * This method is a protected constructor used to create a new rule
	 * @param ruleNum is an int representation of rule number
	 */
	protected Rule(int ruleNum) {
		this.ruleNum = ruleNum;
		this.binaryRule = String.format("%8s",Integer.toBinaryString(this.ruleNum)).replace(' ', '0');
	}
	
	/**
	 * This method returns the int rule number 
	 * @return the rule number
	 */
	public int getRuleNum() {
		return ruleNum; //stub
	}
	
	/**
	 * This is an abstract method returns the neighborhood array depending on elementary and totalistic subclasses
	 * @param idx this is the index int
	 * @param gen this is the given generation 
	 * @return the boolean array of the neighborhood
	 */
	public abstract boolean[] getNeighborhood(int idx, Generation gen);
	
	/**
	 * This is an abstract method that evolves the neighborhood
	 * @param neighborhood is a boolean array that is going to be evolved 
	 * @return true or false based on if it was evolved or not
	 */
	public abstract boolean evolve (boolean[] neighborhood);
	
	/**
	 * This method returns a String representation of the rule table
	 * @param falseSymbol is the character representation of the false symbol
	 * @param trueSymbol is the character representation of the true symbol 
	 * @return a String representation of rule table
	 */
	public abstract String ruleTableString(char falseSymbol, char trueSymbol);

	/**
	 * This method takes the generations given and evolves it then returns it
	 * @param gen this represents the Generation variable given
	 * @return returns the boolean array after it is evolved
	 */
	public Generation evolve(Generation gen) {
		boolean[] temp = new boolean[gen.size()];
		
		for(int i = 0; i < gen.size(); ++i) {
			temp[i] = evolve(getNeighborhood(i,gen));
		}
		Generation tempGen = new Generation(temp);
		
		return tempGen;
	}
	
	/**
	 * This method gets the neighborhood of a generation using an index and radius 
	 * @param idx this is an int representing the index
	 * @param radius is an int representation of the radius we need
	 * @param gen is the generation that we want to evolve
	 * @return a boolean array of the neighborhood
	 */
	public static boolean[] getNeighborhoodByRadius(int idx, int radius, Generation gen) {
		
		boolean[] result = new boolean[(radius*2)+ 1];
		int index = 0;
		
		for(int i = 0; i < result.length; ++i) {
			index = Math.floorMod(idx - radius + i , gen.size());
			result[i] = gen.getState(index);
		}
		return result;
		
	}
				
}
