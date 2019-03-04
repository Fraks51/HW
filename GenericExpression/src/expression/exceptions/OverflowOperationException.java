package expression.exceptions;

public class OverflowOperationException extends IllegalOperationException {
    public <E> OverflowOperationException(String operation, E firstOperand, E secondOperand) {
        super("overflow in operation" + operation + " with numbers" + firstOperand + " and " + secondOperand);
    }

    public <E> OverflowOperationException(String operation, E operand) {
        super("overflow in operation" + operation + " with number" + operand);
    }
}
