package expression;

import expression.exceptions.OverflowOperationException;

public class CheckedSubtract extends AbstractCommonOperations {
    public CheckedSubtract(TripleExpression firstOperand, TripleExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    protected void check(int firstOperand, int secondOperand) throws OverflowOperationException {
        if (firstOperand >= 0 && secondOperand < 0 && firstOperand - Integer.MAX_VALUE > secondOperand) {
            throw new OverflowOperationException("subtract", firstOperand, secondOperand);
        }
        if (firstOperand <= 0 && secondOperand > 0 && Integer.MIN_VALUE - firstOperand > -secondOperand) {
            throw new OverflowOperationException("subtract", firstOperand, secondOperand);
        }
    }


    protected int compute(int firstOperand, int secondOperand) throws OverflowOperationException {
        check(firstOperand, secondOperand);
        return firstOperand - secondOperand;
    }
}
