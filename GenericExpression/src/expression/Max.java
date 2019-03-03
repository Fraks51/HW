package expression;

public class Max extends AbstractCommonOperations {
    public Max(TripleExpression firstOperand, TripleExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    protected void check(int firstOperand, int secondOperand) {
    }

    protected int compute(int firstOperand, int secondOperand) {
        if (firstOperand > secondOperand) {
            return firstOperand;
        } else {
            return secondOperand;
        }
    }
}
