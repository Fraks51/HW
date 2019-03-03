package expression;

import expression.exceptions.*;

public class CheckedDivide extends AbstractCommonOperations {
    public CheckedDivide(TripleExpression firstOperand, TripleExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    protected void check(int firstOperand, int secondOperand) throws IllegalOperationException {
        if (secondOperand == 0) {
            throw new DivisionByZeroException();
        }
        if (firstOperand == Integer.MIN_VALUE && secondOperand == -1) {
            throw new OverflowOperationException("divide", firstOperand, secondOperand);
        }
    }

    protected int compute(int firstOperand, int secondOperand) throws IllegalOperationException {
        check(firstOperand, secondOperand);
        return firstOperand / secondOperand;
    }
}