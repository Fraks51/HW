package expression.exceptions;

public class OverflowOperationException extends IllegalOperationException {
    public OverflowOperationException(String operation, int firstOperand, int secondOperand) {
        super("overflow in operation" + operation + " with numbers" + firstOperand + " and " + secondOperand);
    }

    public OverflowOperationException(String operation, int operand) {
        super("overflow in operation" + operation + " with number" + operand);
    }
}
