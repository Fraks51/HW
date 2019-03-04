package expression.parser;

import expression.*;
import expression.exceptions.*;
import expression.generic.Type;

public class ExpressionParser<T> implements Parser<T> {
    private operation currentOperation;
    private Type<T> type;

    private ExpressionTokenizer<T> operationsExpressionTokenizer;

    public ExpressionParser(Type<T> type) {
        this.type = type;
    }

    public TripleExpression<T> parse(String expression) throws ParsingException {
        operationsExpressionTokenizer = new ExpressionTokenizer<>(expression, type);
        currentOperation = operation.NULL;
        return maxMinOperations();
    }

    private TripleExpression<T> maxMinOperations() throws ParsingException {
        TripleExpression<T> answer = plusMinusOperations();
        while (true) {
            switch (currentOperation) {
                case MAX: {
                    answer = new Max<>(answer, plusMinusOperations(), type);
                    break;
                }
                case MIN: {
                    answer = new Min<>(answer, plusMinusOperations(), type);
                    break;
                }
                default:
                    return answer;
            }
        }
    }

    private TripleExpression<T> plusMinusOperations() throws ParsingException {
        TripleExpression<T> answer = divMulOperations();
        while (true) {
            switch (currentOperation) {
                case ADD: {
                    answer = new CheckedAdd<>(answer, divMulOperations(), type);
                    break;
                }
                case SUBTRACT: {
                    answer = new CheckedSubtract<>(answer, divMulOperations(), type);
                    break;
                }
                default:
                    return answer;
            }
        }
    }

    private TripleExpression<T> divMulOperations() throws ParsingException {
        TripleExpression<T> answer = unaryOperations();
        while (true) {
            switch (currentOperation) {
                case MULTIPLY: {
                    answer = new CheckedMultiply<>(answer, unaryOperations(), type);
                    break;
                }
                case DIVIDE: {
                    answer = new CheckedDivide<>(answer, unaryOperations(), type);
                    break;
                }
                default:
                    return answer;
            }
        }

    }

    private TripleExpression<T> unaryOperations() throws ParsingException {
        currentOperation = operationsExpressionTokenizer.getCurrentOperation();
        TripleExpression<T> answer;
        switch (currentOperation) {
            case VARIABLE: {
                answer = new Variable<>(operationsExpressionTokenizer.getCurrentVariable());
                currentOperation = operationsExpressionTokenizer.getCurrentOperation();
                break;
            }
            case CONST: {
                answer = new Const<>(operationsExpressionTokenizer.getCurrentConst());
                currentOperation = operationsExpressionTokenizer.getCurrentOperation();
                break;
            }
            case UNARY_MINUS: {
                answer = new CheckedNegate<>(unaryOperations(), type);
                break;
            }
            case OPEN_BRACKET: {
                answer = maxMinOperations();
                currentOperation = operationsExpressionTokenizer.getCurrentOperation();
                break;
            }
            case ABS: {
                answer = new CheckedAbs<>(unaryOperations(), type);
                break;
            }
            case SQRT: {
                answer = new CheckedSqrt<>(unaryOperations(), type);
                break;
            }
            default:
                return new Const(0);
        }
        return answer;
    }

}
