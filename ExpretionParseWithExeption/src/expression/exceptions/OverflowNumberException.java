package expression.exceptions;

public class OverflowNumberException extends ParsingException {
    public OverflowNumberException(String number) {
        super("overflow number " + number);
    }
}
