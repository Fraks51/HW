package expression.exceptions;

public class NoOperationForValueException extends ParsingException {
    public NoOperationForValueException(int symbolIndex) {
        super("no compute for value on " + symbolIndex + " position");
    }
}
