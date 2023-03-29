package exceptions;

public class CedulaYaExisteException extends Exception{
	 public CedulaYaExisteException (String s) {
	        super (s);
	    }

	    public CedulaYaExisteException () {
	        super ();
	    }
}