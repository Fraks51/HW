package expression;

import expression.exceptions.IllegalOperationException;
import expression.generic.Type;

public class Variable<T> implements TripleExpression<T> {
    private final char variable;

    public Variable(char currentVariable) {
        variable = currentVariable;
    }

    public T evaluate(T x, T y, T z) throws IllegalOperationException {
        switch (variable) {
            case 'x':
                return x;
            case 'y':
                return y;
            case 'z':
                return z;
        }
        return x;
    }
}
