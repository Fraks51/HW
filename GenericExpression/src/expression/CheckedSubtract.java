package expression;

import expression.exceptions.OverflowOperationException;
import expression.generic.Type;

public class CheckedSubtract<T> extends AbstractCommonOperations<T> {
    public CheckedSubtract(TripleExpression<T> firstOperand, TripleExpression<T> secondOperand, Type<T> type) {
        super(firstOperand, secondOperand, type);
    }

    protected T compute(T firstOperand, T secondOperand) throws OverflowOperationException {
        return type.sub(firstOperand, secondOperand);
    }
}
