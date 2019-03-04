package expression.exceptions;

public class ArgsException extends Exception {
    public ArgsException(String type) {
        super(String.format("Unknown type '%s'", type));
    }
}
