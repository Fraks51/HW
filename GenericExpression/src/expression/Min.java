package expression;

import expression.generic.Type;

public class Min<T> extends AbstractCommonOperations<T> {
    public Min(TripleExpression<T> firstOperand, TripleExpression<T> secondOperand, Type<T> type) {
        super(firstOperand, secondOperand, type);
    }

    protected T compute(T firstOperand, T secondOperand) {
        return type.min(firstOperand, secondOperand);
    }
}
