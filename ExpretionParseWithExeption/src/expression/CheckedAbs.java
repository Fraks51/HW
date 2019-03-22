package expression;

import expression.exceptions.*;

public class CheckedAbs extends AbstractUnaryOperations {
    public CheckedAbs(TripleExpression operand) {
        super(operand);
    }


    protected void check(int operand) throws OverflowOperationException {
        if (operand == Integer.MIN_VALUE) {
            throw new OverflowOperationException("abs", operand);
        }
    }

    protected int compute(int operand) throws OverflowOperationException {
        check(operand);
        if (operand >= 0) {
            return operand;
        } else {
            return -operand;
        }
    }

}
