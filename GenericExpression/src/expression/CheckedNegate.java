package expression;

import expression.exceptions.OverflowOperationException;
import expression.exceptions.SqrtOfNegativeNumberException;
import expression.generic.Type;

public class CheckedNegate<T> extends AbstractUnaryOperations<T> {

    public CheckedNegate(TripleExpression<T> operand, Type<T> type) {
        super(operand, type);
    }

    protected T compute(T operand) throws OverflowOperationException {
        return type.negate(operand);
    }
}
