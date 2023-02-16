// Worked on this class with Keeton ; Guillermo
// And John

/**
 * The Application class includes a main method accepting
 * command line arguments to create and simulate an appropriate CA.
 *  
 * @author Jared Rubio
 * @version 2.0
 * @since March 1, 2022
 */
public class Application {

	private static final int NUM_EXPECTED_ARGS = 6;

	private static final int IDX_CA = 0;
	private static final int IDX_RULE_NUM = 1;
	private static final int IDX_FALSE_SYMBOL = 2;
	private static final int IDX_TRUE_SYMBOL = 3;
	private static final int IDX_INITIAL_GENERATION = 4;
	private static final int IDX_NUM_EVOLVE = 5;

	private static final String ARG_NAMES = "ca rule-num false-symbol true-symbol initial-generation num-evolutions";
	
	// Source and class file names must match, so Application can be hard-coded
	private static final String USAGE_FMT_STRING_CLASS = "Usage: java Application " + ARG_NAMES;

	// The jar file may be renamed, so this will be varied
	private static final String USAGE_FMT_STRING_JAR = "Usage: java -jar %s " + ARG_NAMES;

	private String[] appArgs;
	
	/**
	 * This method constructs the Application object
	 * @param args array holding String arguments
	 */
	public Application(String[] args)  {
		validateNumArgs(args);
		appArgs = args;
	}

	/**
	 * This method calls the throwRuntimeExceptionWithUsageMessage method if args isn't equal to NUM_EXPECTED_ARGS
	 * @param args array holding String arguments
	 */
	private void validateNumArgs(String[] args) {
		
		if (args.length != NUM_EXPECTED_ARGS) {
			throwRuntimeExceptionWithUsageMessage();
		}
	}

	/**
	 * Throws a RuntimeException with an appropriate usage string
	 */
	private void throwRuntimeExceptionWithUsageMessage() {
		// Implementation provided
		if (runningAsJar()) {
			// Get the path to the executing file
			String path = Application.class
					.getProtectionDomain()
					.getCodeSource()
					.getLocation()
					.getPath();
			// Only take path after last slash (to get file name).
			// A hard-coded slash is fine since Java URLs use /
			String file = path.substring(path.lastIndexOf("/") + 1);
			throw new RuntimeException(String.format(USAGE_FMT_STRING_JAR, file));
		} else {
			throw new RuntimeException(String.format(USAGE_FMT_STRING_CLASS));
		}
	}

	/**
	 * Returns true if the Application is running as a JAR and false otherwise
	 * @return true of false depending on if running JAR
	 */
	private boolean runningAsJar() {
		// Implementation provided
		return Application.class
				.getResource("Application.class")
				.toString()
				.startsWith("jar");
	}

	/**
	 * Parses each of the six arguments, constructs the appropriate Automaton,
	 * and prints out the full evolution to System.out
	 * @param args array holding String arguments
	 */
	private void parseArgs(String[] args) {
		
		
		try {
			CellularAutomaton ca = CellularAutomaton.parse(args[IDX_CA]);
			int ruleNum = Integer.parseInt(args[IDX_RULE_NUM]);
			char falseSymbol = args[IDX_FALSE_SYMBOL].charAt(0);
			char trueSymbol = args[IDX_TRUE_SYMBOL].charAt(0);
			String generation = args[IDX_INITIAL_GENERATION];
			int evolve = Integer.parseInt(args[IDX_NUM_EVOLVE]);
			
			Generation gen = new Generation(generation, trueSymbol);
			Automaton automaton = Automaton.createAutomaton(ca, ruleNum, gen);
			
			automaton.evolve(evolve);
			String out = automaton.toString();
			out = out.replace('1', trueSymbol);
			out = out.replace('0', falseSymbol);

			System.out.println(out);
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Calls the parseArgs(String[] args) method using the previously given arguments
	 */
	public void run() {
		parseArgs(appArgs);
	}

	/**
	 * Constructs and runs an Application using the supplied main method arguments
	 * @param args array holding String arguments
	 */
	public static void main(String[] args) {
	
		try {
			Application app = new Application(args);
			app.run();
		}
		catch(RuntimeException e) {
			System.err.println(e.getMessage());
		}
		
	}
}
