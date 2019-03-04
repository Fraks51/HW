package expression;

import expression.exceptions.*;
import expression.generic.Type;

public class CheckedDivide<T> extends AbstractCommonOperations<T> {
    public CheckedDivide(TripleExpression<T> firstOperand, TripleExpression<T> secondOperand, Type<T> type) {
        super(firstOperand, secondOperand, type);
    }

    protected T compute(T firstOperand, T secondOperand) throws IllegalOperationException {
        return type.div(firstOperand, secondOperand);
    }
}