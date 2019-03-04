package expression.exceptions;

public class NoValueForOperationException extends ParsingException {
    public NoValueForOperationException(int symbolIndex) {
        super("no value for compute on " + symbolIndex + " position");
    }
}
