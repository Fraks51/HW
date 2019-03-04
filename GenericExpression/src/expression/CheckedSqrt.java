package expression;

import expression.exceptions.SqrtOfNegativeNumberException;
import expression.generic.Type;

public class CheckedSqrt<T> extends AbstractUnaryOperations<T> {
    public CheckedSqrt(TripleExpression<T> operand, Type<T> type) {
        super(operand, type);
    }

    protected T compute(T operand) throws SqrtOfNegativeNumberException {
        return type.sqrt(operand);
    }
}
