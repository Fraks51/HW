package expression.generic;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowNumberException;
import expression.exceptions.OverflowOperationException;
import expression.exceptions.SqrtOfNegativeNumberException;

public interface Type<T> {
    public T add(final T firstOperand, final T secondOperand) throws OverflowOperationException;

    public T sub(final T firstOperand, final T secondOperand) throws OverflowOperationException;

    public T parseNumber(final String number) throws OverflowNumberException;

    public T min(final T firstOperand, final T secondOperand);

    public T max(final T firstOperand, final T secondOperand);

    public T sqrt(final T operand) throws SqrtOfNegativeNumberException;

    public T negate(final T operand) throws OverflowOperationException;

    public T mul(final T firstOperand, final T secondOperand) throws OverflowOperationException;

    public T div(final T firstOperand, final T secondOperand) throws DivisionByZeroException, OverflowOperationException;

    public T abs(final T operand) throws OverflowOperationException;
}
