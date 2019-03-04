package expression;

import expression.exceptions.*;
import expression.generic.Type;

public class CheckedAbs<T> extends AbstractUnaryOperations<T> {
    public CheckedAbs(TripleExpression<T> operand, Type<T> type) {
        super(operand, type);
    }

    protected T compute(T operand) throws OverflowOperationException {
        return type.abs(operand);
    }

}
