package expression;

import expression.exceptions.IllegalOperationException;

public abstract class AbstractUnaryOperations implements TripleExpression {
    protected final TripleExpression operand;

    protected AbstractUnaryOperations(TripleExpression operand) {
        this.operand = operand;
    }

    protected abstract void check(int operand) throws IllegalOperationException;

    protected abstract int compute(int operand) throws IllegalOperationException;

    public int evaluate(final int x, final int y, final int z) throws IllegalOperationException {
        return compute(operand.evaluate(x, y, z));
    }
}
