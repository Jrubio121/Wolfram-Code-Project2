// Worked on this class with Keeton ; Guillermo
// And John

/**
 * Generation represents the cells of a 1D, two-state CA at a single moment in time
 *   
 * @author Jared Rubio
 * @version 2.0
 * @since March 1, 2022
 */
import java.util.Arrays;



public class Generation {
	
	private boolean[] cellStates;
	
	/**
	 * This method constructs a generation using the states boolean array
	 * @param states is a boolean array that contains the states of the generation
	 */
	public Generation (boolean... states) {
		
		if(states != null && states.length !=0) {
			boolean[] copyArray = Arrays.copyOf(states, states.length);
			cellStates = copyArray;
		}
		else {
			cellStates = new boolean[] {false};
			}
		}
	
		/**
		 * This method is a constructor for generation that uses a state and true symbol
		 * @param states is a String that represents the current state
		 * @param trueSymbol is a character representation of true symbol
		 */
	public Generation (String states, char trueSymbol) {
		if(states == null || states.length() == 0){
			cellStates = new boolean[] {false};
		}
		
		else {
			cellStates = new boolean[states.length()];
			for(int i =0; i < states.length(); ++i) {
				if(states.charAt(i)==trueSymbol) {
					cellStates[i] = true;
				}
				else if(states.charAt(i)!=trueSymbol) {
					cellStates[i] = false;
				}
			}
		}
	}
	
	/**
	 * This method returns the boolean value of the state
	 * @param idx int representing the index
	 * @return true or false based on the state
	 */
	public boolean getState(int idx) {
		return cellStates[idx];
	}
	
	/**
	 * This method returns the boolean array of states
	 * @return the boolean array of states
	 */
	public boolean[] getStates() {
		boolean[] copyArray = Arrays.copyOf(cellStates, cellStates.length);
		return copyArray;
	}
	
	/**
	 * This method returns the String representation of the current state
	 * @param falseSymbol a character representation of false symbol
	 * @param trueSymbol a character representation of true symbol
	 * @return the String of the current state
	 */
	public String getStates(char falseSymbol, char trueSymbol) {
		String states = "";
		for(int i =0; i < cellStates.length; ++i) {
			if(cellStates[i] == false) {
				states = states + falseSymbol;
			}
			else if(cellStates[i] == true) {
				states = states + trueSymbol;
			}
		}
		return states;
	}
	/**
	 * This method returns the size of cell states
	 * @return the size of cell states
	 */
	public int size() {
		return cellStates.length; 
	}
	
}
