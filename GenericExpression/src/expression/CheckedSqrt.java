package expression;

import expression.exceptions.SqrtOfNegativeNumberException;

public class CheckedSqrt extends AbstractUnaryOperations {
    public CheckedSqrt(TripleExpression operand) {
        super(operand);
    }


    protected void check(int operand) throws SqrtOfNegativeNumberException {
        if (operand < 0) {
            throw new SqrtOfNegativeNumberException();
        }
    }

    protected int compute(int operand) throws SqrtOfNegativeNumberException {
        check(operand);
        if (operand < 2) {
            return operand;
        }
        int left = 0;
        int right = 46341;
        while (left + 1 < right) {
            int middle = (left + right) / 2;
            if (middle * middle > operand) {
                right = middle;
            } else {
                left = middle;
            }
        }
        return left;
    }
}
