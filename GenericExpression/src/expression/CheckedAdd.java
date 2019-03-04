package expression;

import expression.exceptions.OverflowOperationException;
import expression.generic.Type;

public class CheckedAdd<T> extends AbstractCommonOperations<T> {
    public CheckedAdd(TripleExpression<T> firstOperand, TripleExpression<T> secondOperand, Type<T> type) {
        super(firstOperand, secondOperand, type);
    }

    protected T compute(T firstOperand, T secondOperand) throws OverflowOperationException {
        return type.add(firstOperand, secondOperand);
    }
}
