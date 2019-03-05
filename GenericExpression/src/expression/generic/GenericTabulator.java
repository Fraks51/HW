package expression.generic;

import expression.TripleExpression;
import expression.exceptions.ArgsException;
import expression.parser.ExpressionParser;
import expression.parser.Parser;

public class GenericTabulator implements Tabulator {

    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Type<?> type;
        switch (mode) {
            case "i": {
                type = new IntType();
                break;
            }
            case "u": {
                type = new UncheckedIntType();
                break;
            }
            case "f": {
                type = new FloatType();
                break;
            }
            case "b": {
                type = new ByteType();
                break;
            }
            case "d": {
                type = new DoubleType();
                break;
            }
            case "bi": {
                type = new BigIntType();
                break;
            }
            default: {
                throw new ArgsException(mode);
            }
        }
        return buildTabulate(type, expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] buildTabulate(Type<T> type, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Object[][][] answer = new Object[x2 + 1 - x1][y2 + 1 - y1][z2 + 1 - z1];
        Parser<T> parser = new ExpressionParser<>(type);
        TripleExpression<T> expressionEvaluate;
        try {
            expressionEvaluate = parser.parse(expression);
        } catch (Exception x) {
            x.printStackTrace();
            return answer;
        }
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        answer[i - x1][j - y1][k - z1] = expressionEvaluate.evaluate(type.parseNumber(Integer.toString(i)),
                                type.parseNumber(Integer.toString(j)), type.parseNumber(Integer.toString(k)));
                    } catch (Exception e) {
                        answer[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }
        return answer;
    }

}
