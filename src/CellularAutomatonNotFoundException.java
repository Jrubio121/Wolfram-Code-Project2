/**
 * CellularAutomatonNotFoundException is an exception thrown when no appropriate CellularAutomaton exists. 
 *  
 * @author Jared Rubio
 * @version 2.0
 * @since March 1, 2022
 */

public class CellularAutomatonNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * This method throws a message if CellularAutomaton is not found
	 * @param s String given
	 */
	public CellularAutomatonNotFoundException(String s) {
		super("Unknown cellular automaton type " + s);
	}
}