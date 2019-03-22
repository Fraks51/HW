package expression.exceptions;

public class DivisionByZeroException extends IllegalOperationException {
    public DivisionByZeroException() {
        super("Division by zero");
    }
}
