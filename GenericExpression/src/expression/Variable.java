package expression;

public class Variable implements TripleExpression {
    private final char variable;

    public Variable(char currentVariable) {
        variable = currentVariable;
    }

    public int evaluate(int x, int y, int z) {
        switch (variable) {
            case 'x':
                return x;
            case 'y':
                return y;
            case 'z':
                return z;
        }
        return 0;
    }
}
