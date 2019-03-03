package expression.exceptions;

public class SqrtOfNegativeNumberException extends IllegalOperationException {
    public SqrtOfNegativeNumberException() {
        super("sqrt of negative number");
    }
}
