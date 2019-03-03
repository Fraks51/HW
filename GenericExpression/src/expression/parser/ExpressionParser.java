package expression.parser;

import expression.*;
import expression.exceptions.*;

public class ExpressionParser implements Parser, expression.exceptions.Parser {
    private operation currentOperation;

    private ExpressionTokenizer operationsExpressionTokenizer;

    public TripleExpression parse(String expression) throws ParsingException {
        operationsExpressionTokenizer = new ExpressionTokenizer(expression);
        currentOperation = operation.NULL;
        return maxMinOperations();
    }

    private TripleExpression maxMinOperations() throws ParsingException {
        TripleExpression answer = plusMinusOperations();
        while (true) {
            switch (currentOperation) {
                case MAX: {
                    answer = new Max(answer, plusMinusOperations());
                    break;
                }
                case MIN: {
                    answer = new Min(answer, plusMinusOperations());
                    break;
                }
                default:
                    return answer;
            }
        }
    }

    private TripleExpression plusMinusOperations() throws ParsingException {
        TripleExpression answer = divMulOperations();
        while (true) {
            switch (currentOperation) {
                case ADD: {
                    answer = new CheckedAdd(answer, divMulOperations());
                    break;
                }
                case SUBTRACT: {
                    answer = new CheckedSubtract(answer, divMulOperations());
                    break;
                }
                default:
                    return answer;
            }
        }
    }

    private TripleExpression divMulOperations() throws ParsingException {
        TripleExpression answer = unaryOperations();
        while (true) {
            switch (currentOperation) {
                case MULTIPLY: {
                    answer = new CheckedMultiply(answer, unaryOperations());
                    break;
                }
                case DIVIDE: {
                    answer = new CheckedDivide(answer, unaryOperations());
                    break;
                }
                default:
                    return answer;
            }
        }

    }

    private TripleExpression unaryOperations() throws ParsingException {
        currentOperation = operationsExpressionTokenizer.getCurrentOperation();
        TripleExpression answer;
        switch (currentOperation) {
            case VARIABLE: {
                answer = new Variable(operationsExpressionTokenizer.getCurrentVariable());
                currentOperation = operationsExpressionTokenizer.getCurrentOperation();
                break;
            }
            case CONST: {
                answer = new Const(operationsExpressionTokenizer.getCurrentConst());
                currentOperation = operationsExpressionTokenizer.getCurrentOperation();
                break;
            }
            case UNARY_MINUS: {
                answer = new CheckedNegate(unaryOperations());
                break;
            }
            case OPEN_BRACKET: {
                answer = maxMinOperations();
                currentOperation = operationsExpressionTokenizer.getCurrentOperation();
                break;
            }
            case ABS: {
                answer = new CheckedAbs(unaryOperations());
                break;
            }
            case SQRT: {
                answer = new CheckedSqrt(unaryOperations());
                break;
            }
            case HIGH: {
                answer = new High(unaryOperations());
                break;
            }
            case LOW: {
                answer = new Low(unaryOperations());
                break;
            }
            default:
                return new Const(0);
        }
        return answer;
    }

}
