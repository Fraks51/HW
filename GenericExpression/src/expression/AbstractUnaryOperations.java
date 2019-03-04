package expression;

import expression.exceptions.IllegalOperationException;
import expression.generic.Type;

public abstract class AbstractUnaryOperations<T> implements TripleExpression<T> {
    protected final TripleExpression<T> operand;
    protected final Type<T> type;

    protected AbstractUnaryOperations(TripleExpression operand, Type<T> type) {
        this.operand = operand;
        this.type = type;
    }

    protected abstract T compute(T operand) throws IllegalOperationException;

    public T evaluate(final T x, final T y, final T z) throws IllegalOperationException {
        return compute(operand.evaluate(x, y, z));
    }
}
