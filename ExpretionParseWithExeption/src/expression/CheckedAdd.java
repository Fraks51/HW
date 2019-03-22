package expression;

import expression.exceptions.OverflowOperationException;

public class CheckedAdd extends AbstractCommonOperations {
    public CheckedAdd(TripleExpression firstOperand, TripleExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    protected void check(int firstOperand, int secondOperand) throws OverflowOperationException {
        if (firstOperand > 0 && Integer.MAX_VALUE - firstOperand < secondOperand) {
            throw new OverflowOperationException("add", firstOperand, secondOperand);
        }
        if (firstOperand < 0 && Integer.MIN_VALUE - firstOperand > secondOperand) {
            throw new OverflowOperationException("add", firstOperand, secondOperand);
        }
    }

    protected int compute(int firstOperand, int secondOperand) throws OverflowOperationException {
        check(firstOperand, secondOperand);
        return firstOperand + secondOperand;
    }
}
