public class Stack211 {

	public int stackTop;// tracks what position objects are in stack.
	public char[] myStack =  new char [40];
	
	Stack211() {
		stackTop = -1;
	}
	//pushes char to stack.
	public void push(char c) {
		stackTop++;
		myStack[stackTop] = c;
	}
	//pops char from stack.
	public char pop() {
		char c = myStack[stackTop];
		stackTop--;
		return c;
		
	}
	// detects if contains objects or not.
	public boolean isEmpty() {
		if(stackTop<0)
			return true;
		else
			return false;
	}

}