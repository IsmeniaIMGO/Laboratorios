package exceptions;

public class IdYaExisteException extends Exception{
	 public IdYaExisteException (String s) {
	        super (s);
	    }

	    public IdYaExisteException () {
	        super ();
	    }
}