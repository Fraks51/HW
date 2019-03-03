package expression;

public class Low extends AbstractUnaryOperations {
    public Low(TripleExpression operand) {
        super(operand);
    }

    protected void check(int operand) {
    }

    protected int compute(int operand) {
        return Integer.lowestOneBit(operand);
    }
}
