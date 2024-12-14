package nl.novi.techiteasy.exceptions;

public class TelevisionNameTooLongException extends RuntimeException {

    public TelevisionNameTooLongException() {
        super("Television name is not valid");
    }

    public TelevisionNameTooLongException(String message) {
        super(message);
    }


}
