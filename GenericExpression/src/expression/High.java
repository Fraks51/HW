package expression;

public class High extends AbstractUnaryOperations {

    public High(TripleExpression operand) {
        super(operand);
    }

    protected void check(int operand) {
    }

    protected int compute(int operand) {
        return Integer.highestOneBit(operand);
    }

}
