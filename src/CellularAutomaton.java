
/**
 * CellularAutomaton is an enum representing a type of cellular automaton.
 *  
 * @author Jared Rubio
 * @version 2.0
 * @since March 1, 2022
 */

public enum CellularAutomaton {

	/**
	 * This represent Elementary Cellular Automaton
	 */
	ECA,

	/**
	 * This represents Totalistic Cellular Automaton
	 */
	TCA;
	
	/**
	 * This compares automatons
	 * @param s string used to check ECA and TCA
	 * @throws CellularAutomatonNotFoundException throws an exception if cellular automaton is not found
	 * @return the Cellular Automaton if found
	 */
	public static CellularAutomaton parse(String s) throws CellularAutomatonNotFoundException {
		if (s.equalsIgnoreCase("ECA")) { 
			return ECA;
		}
		else if (s.equalsIgnoreCase("TCA")) {
			return TCA;
		}
		else {
			throw new CellularAutomatonNotFoundException(s);
		}
	}
}
