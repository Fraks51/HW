package expression;

import expression.exceptions.IllegalOperationException;
import expression.exceptions.OverflowOperationException;

public class CheckedMultiply extends AbstractCommonOperations {
    public CheckedMultiply(TripleExpression firstOperand, TripleExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    protected void check(int firstOperand, int secondOperand) throws IllegalOperationException {
        if (firstOperand > 0 && secondOperand > 0 && Integer.MAX_VALUE / firstOperand < secondOperand) {
            throw new OverflowOperationException("multiply", firstOperand, secondOperand);
        }
        if (firstOperand < 0 && secondOperand < 0 && Integer.MAX_VALUE / firstOperand > secondOperand) {
            throw new OverflowOperationException("multiply", firstOperand, secondOperand);
        }
        if (firstOperand > 0 && secondOperand < 0 && Integer.MIN_VALUE / firstOperand > secondOperand) {
            throw new OverflowOperationException("multiply", firstOperand, secondOperand);
        }
        if (firstOperand < 0 && secondOperand > 0 && Integer.MIN_VALUE / secondOperand > firstOperand) {
            throw new OverflowOperationException("multiply", firstOperand, secondOperand);
        }
    }

    protected int compute(int firstOperand, int secondOperand) throws IllegalOperationException {
        check(firstOperand, secondOperand);
        return firstOperand * secondOperand;
    }
}
