package expression;

import expression.exceptions.IllegalOperationException;
import expression.exceptions.OverflowOperationException;
import expression.generic.Type;

public class CheckedMultiply<T> extends AbstractCommonOperations<T> {
    public CheckedMultiply(TripleExpression<T> firstOperand, TripleExpression<T> secondOperand, Type<T> type) {
        super(firstOperand, secondOperand, type);
    }

    protected T compute(T firstOperand, T secondOperand) throws IllegalOperationException {
        return type.mul(firstOperand, secondOperand);
    }
}
