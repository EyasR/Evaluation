/* 
 * CS211 Assignment 04 (Stack Operations 1)
 * Eyas Rashid - rashid_e@hotmail.com
 * Student ID: 985 762 980
 * 2018.05.17 - Spring Quarter
 *
 * This Java program is designed to detect errors in an expression
 * regarding grouping symbols(parenthesis and curly braces).
 */
import java.util.HashMap;

public class evaluation1 {

	//this HashMap contains all of the types of error messages that can be generated.
	static HashMap<Integer, String> errorMessage = new HashMap<Integer, String>(); 
	
	//this HashMap contains pairs of brackets eg. key '(' value ')'.
	static HashMap<Character, Character> pair = new HashMap<Character, Character>();

	//This array contains mathematical statements that the program must test for errors.
	static String[] Statement = { "3 + 4 } - ) * ) ", "( 3 + 4 ) { 6 / 3 )", "(( 4 - 5 )}", "{ 5 * 3 }  ( 6 + 5 }", "(( 3 - 6" };

	public static void main(String[] args) {
		loadErrorMessage();// pre-load error messages for use.
		loadPair();		   // pre-load bracket pairs for use.
		compilerInstance();// run compiler instance
	}

	//this method is used to load error messages into HashMap errorMessages
	// to be used when testing mathematical expression regarding grouping symbols.
	public static void loadErrorMessage() {
		errorMessage.put(1, "Syntax Error: Incomplete Expression: Unpaired )");
		errorMessage.put(2, "Syntax Error: Incomplete Expression: Unpaired }");

		errorMessage.put(4, "Syntax Error: insert \")\" to complete Expression");
		errorMessage.put(5, "Syntax Error: insert \"}\" to complete Expression");

		errorMessage.put(6, "Unresolved compilation problem");
	}

	//this method is used to load pairs of grouping symbols into HashMap pair
	public static void loadPair() {
		pair.put(')', '(');
		pair.put('}', '{');
	}

	// this method will print a mark of the location at which an error occurs
	// within a mathematical statement and print the type of error that occurred.
	public static void printError(int location, int errorType) {
		String errorLocation = "";

		for (int i = 0; i < location; i++) {
			errorLocation += " ";
		}
		
		System.out.println(errorLocation + "^");
		System.out.println(errorMessage.get(errorType));
		System.out.println();
	}

	// this method detects errors in an expression regarding grouping symbols (specifically () & {} )
	public static void compilerInstance() {
		for (int i = 0; i < Statement.length; i++) { // # of statements to be evaluated
			Stack211 st = new Stack211();

			String cStatement = Statement[i];//temporarily saves statements from array
			int location = 0; //keeps track of were the error is located
			boolean errorDetected = false;// flags for when an error has been detected.

			System.out.println(cStatement);// prints mathematical expression.
			
			// for loop to check all characters of statement.
			for (int j = 0; j < cStatement.length(); j++) {
				location = j;
				//temporarily saves character being tested in mathematical expression.
				char c = cStatement.charAt(j);

				if (c == '(' || c == '{') { // if (, or {
					st.push(c);//saves bracket to stack
				} else if (c == ')' || c == '}') { // if ), or }
					if (st.isEmpty()) { // if stack is empty
						printError(location, 6);// prints error
					    j = cStatement.length();// ends for loop
					} else {
						char test = st.pop(); //pops and saves last char saved in stack

						if (test != pair.get(c)) { // if test char is not c chars matching bracket
							if (c == ')') {
								printError(location, 1);
							
							} else if (c == '}') {
								printError(location, 2); 
							
							}
							j = cStatement.length();
							errorDetected = true; 

						}
					}
				}
			}

			if (!errorDetected) { // if no error detected
				if (!st.isEmpty()) { // if stack is not empty

					char test = st.pop(); //pops and saves last char saved in stack

					if (test == '(') {
						printError(location + 1, 4); //prints error
					} else  {
						printError(location + 1, 5); //prints error
					} 

				}
			}
		}
	}
}
