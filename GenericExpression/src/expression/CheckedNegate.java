package expression;

import expression.exceptions.OverflowOperationException;

public class CheckedNegate extends AbstractUnaryOperations {

    public CheckedNegate(TripleExpression operand) {
        super(operand);
    }

    protected void check(int operand) throws OverflowOperationException {
        if (operand == Integer.MIN_VALUE) {
            throw new OverflowOperationException("unary minus", operand);
        }
    }

    protected int compute(int operand) throws OverflowOperationException {
        check(operand);
        return -operand;
    }
}
