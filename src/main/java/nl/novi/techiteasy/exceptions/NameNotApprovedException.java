package nl.novi.techiteasy.exceptions;

public class NameNotApprovedException extends RuntimeException {

    public NameNotApprovedException() {
        super();
    }

    public NameNotApprovedException(String message) {
        super(message);
    }
}
