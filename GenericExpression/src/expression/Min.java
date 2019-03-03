package expression;

public class Min extends AbstractCommonOperations {
    public Min(TripleExpression firstOperand, TripleExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    protected void check(int firstOperand, int secondOperand) {
        return;
    }

    protected int compute(int firstOperand, int secondOperand) {
        if (firstOperand > secondOperand) {
            return secondOperand;
        } else {
            return firstOperand;
        }
    }
}
