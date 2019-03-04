package expression;

import expression.exceptions.IllegalOperationException;
import expression.generic.Type;

public abstract class AbstractCommonOperations<T> implements TripleExpression<T> {
    private final TripleExpression<T> firstOperand, secondOperand;
    protected Type<T> type;

    public AbstractCommonOperations(TripleExpression<T> firstOperand, TripleExpression<T> secondOperand, Type<T> type) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.type = type;
    }

    protected abstract T compute(T firstOperand, T secondOperand) throws IllegalOperationException;

    public T evaluate(T x, T y, T z) throws IllegalOperationException {
        return compute(firstOperand.evaluate(x, y, z), secondOperand.evaluate(x, y, z));
    }
}
