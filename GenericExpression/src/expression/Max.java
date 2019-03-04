package expression;

import expression.generic.Type;

public class Max<T> extends AbstractCommonOperations<T> {
    public Max(TripleExpression<T> firstOperand, TripleExpression<T> secondOperand, Type<T> type) {
        super(firstOperand, secondOperand, type);
    }

    protected T compute(T firstOperand, T secondOperand) {
        return type.max(firstOperand, secondOperand);
    }
}
