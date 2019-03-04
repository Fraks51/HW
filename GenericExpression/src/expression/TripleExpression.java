package expression;

import expression.exceptions.IllegalOperationException;
import expression.generic.Type;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression<T> {
    T evaluate(T x, T y, T z) throws IllegalOperationException;
}
