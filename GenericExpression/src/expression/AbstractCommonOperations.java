package expression;

import expression.exceptions.IllegalOperationException;

public abstract class AbstractCommonOperations implements TripleExpression {
    private final TripleExpression firstOperand, secondOperand;

    public AbstractCommonOperations(TripleExpression firstOperand, TripleExpression secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    protected abstract void check(int firstOperand, int secondOperand) throws IllegalOperationException;

    protected abstract int compute(int firstOperand, int secondOperand) throws IllegalOperationException;

    public int evaluate(int x, int y, int z) throws IllegalOperationException {
        return compute(firstOperand.evaluate(x, y, z), secondOperand.evaluate(x, y, z));
    }
}
