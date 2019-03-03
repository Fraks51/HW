package expression.parser;

import expression.exceptions.*;

import java.util.ArrayDeque;

public class ExpressionTokenizer {
    private String expression;

    private int currentExpressionIndex;

    private ArrayDeque<operation> operationsQueue;

    private ArrayDeque<Character> variableQueue;

    private ArrayDeque<Integer> constQueue;

    private int openBracketsCounter;

    private operation currentOperation;

    private boolean wasLastOperationIsEvaluate;

    public ExpressionTokenizer(String expression) throws ParsingException {
        this.expression = expression;
        currentExpressionIndex = 0;
        currentOperation = operation.NULL;
        openBracketsCounter = 0;
        variableQueue = new ArrayDeque<>();
        operationsQueue = new ArrayDeque<>();
        constQueue = new ArrayDeque<>();
        wasLastOperationIsEvaluate = false;
        makeOperationList();
    }

    public char getCurrentVariable() {
        return variableQueue.poll();
    }

    public int getCurrentConst() {
        return constQueue.poll();
    }

    public operation getCurrentOperation() {
        if (operationsQueue.isEmpty()) {
            return operation.NULL;
        }
        return operationsQueue.poll();
    }

    public void makeOperationList() throws ParsingException {
        do {
            currentOperation = NextOperation();
            operationsQueue.add(currentOperation);
        } while (currentOperation != operation.NULL);
        if (!wasLastOperationIsEvaluate && expression.charAt(expression.length() - 1) != ')') {
            throw new NoValueForOperationException(currentExpressionIndex - 1);
        }
    }

    private void checkForBinary() throws NoValueForOperationException {
        if (!wasLastOperationIsEvaluate) {
            throw new NoValueForOperationException(currentExpressionIndex);
        }
        wasLastOperationIsEvaluate = false;
    }

    private void checkForUnary() throws NoValueForOperationException {
        if (wasLastOperationIsEvaluate) {
            throw new NoValueForOperationException(currentExpressionIndex);
        }
    }

    private void checkForEvaluate() throws NoOperationForValueException {
        if (wasLastOperationIsEvaluate) {
            throw new NoOperationForValueException(currentExpressionIndex - 1);
        }
        wasLastOperationIsEvaluate = true;
    }

    private void checkAfterUnaryOperation() throws UnknownSymbolException {
        char currentChar = expression.charAt(currentExpressionIndex);
        if (currentChar == 'x' || currentChar == 'y' || currentChar == 'z') {
            throw new UnknownSymbolException(currentExpressionIndex);
        }
    }

    private void checkAfterBinaryOperation() throws UnknownSymbolException {
        if (currentExpressionIndex + 2 < expression.length()) {
            char currentChar = expression.charAt(currentExpressionIndex + 2);
            if (!Character.isWhitespace(currentChar) && currentChar != '-') {
                throw new UnknownSymbolException(currentExpressionIndex + 2);
            }
        }
    }

    private void checkOnOverflowForNumber(String currentNumber) throws OverflowNumberException {
        try {
            Integer.parseUnsignedInt(currentNumber);
        } catch (Exception ex) {
            throw new OverflowNumberException(currentNumber);
        }
        if (-Integer.parseUnsignedInt(currentNumber) == Integer.MIN_VALUE) {
            if (operationsQueue.getLast() == operation.UNARY_MINUS) {
                operationsQueue.pollLast();
                constQueue.add(Integer.MIN_VALUE);
                return;
            } else {
                throw new OverflowNumberException(currentNumber);
            }
        } else {
            try {
                Integer.parseInt(currentNumber);
            } catch (Exception ex) {
                throw new OverflowNumberException(currentNumber);
            }
        }
    }

    private void CheckUnaryAndMovePointer(int lengthShiftPointer) throws NoValueForOperationException, UnknownSymbolException {
        currentExpressionIndex += lengthShiftPointer;
        checkForUnary();
        checkAfterUnaryOperation();
    }

    private operation NextOperation() throws ParsingException {
        while (currentExpressionIndex < expression.length() && Character.isWhitespace(expression.charAt(currentExpressionIndex))) {
            currentExpressionIndex++;
        }
        if (currentExpressionIndex >= expression.length()) {
            if (openBracketsCounter != 0) {
                throw new MissingCloseBracketException(currentExpressionIndex);
            }
            return operation.NULL;
        }
        char currentChar = expression.charAt(currentExpressionIndex);
        currentExpressionIndex++;
        switch (currentChar) {
            case '+': {
                checkForBinary();
                return operation.ADD;
            }
            case '-': {
                if (currentOperation == operation.CONST || currentOperation == operation.CLOSE_BRACKET
                        || currentOperation == operation.VARIABLE) {
                    checkForBinary();
                    return operation.SUBTRACT;
                } else {
                    checkForUnary();
                    return operation.UNARY_MINUS;
                }
            }
            case 'm': {
                checkAfterBinaryOperation();
                if (expression.startsWith("min", currentExpressionIndex - 1)) {
                    currentExpressionIndex += 2;
                    checkForBinary();
                    checkAfterUnaryOperation();
                    return operation.MIN;
                } else if (expression.startsWith("max", currentExpressionIndex - 1)) {
                    currentExpressionIndex += 2;
                    checkForBinary();
                    checkAfterUnaryOperation();
                    return operation.MAX;
                } else {
                    throw new UnknownSymbolException(currentExpressionIndex - 1);
                }
            }
            case '*': {
                checkForBinary();
                return operation.MULTIPLY;
            }
            case '/': {
                checkForBinary();
                return operation.DIVIDE;
            }
            case ')': {
                if (!wasLastOperationIsEvaluate) {
                    throw new NoValueForOperationException(currentExpressionIndex - 1);
                }
                wasLastOperationIsEvaluate = true;
                if (openBracketsCounter == 0) {
                    throw new ExcessCloseBracketException();
                }
                openBracketsCounter--;
                return operation.CLOSE_BRACKET;
            }
            case '(': {
                if (wasLastOperationIsEvaluate) {
                    throw new NoOperationForValueException(currentExpressionIndex - 1);
                }
                wasLastOperationIsEvaluate = false;
                openBracketsCounter++;
                return operation.OPEN_BRACKET;
            }
            default: {
                if ('x' == currentChar || 'y' == currentChar || 'z' == currentChar) {
                    checkForEvaluate();
                    variableQueue.add(currentChar);
                    return operation.VARIABLE;
                } else if (Character.isDigit(currentChar)) {
                    checkForEvaluate();
                    int startIndex = currentExpressionIndex - 1;
                    while (expression.length() > currentExpressionIndex && Character.isDigit(expression.charAt(currentExpressionIndex))) {
                        currentExpressionIndex++;
                    }
                    checkOnOverflowForNumber(expression.substring(startIndex, currentExpressionIndex));
                    constQueue.add(Integer.parseUnsignedInt(expression.substring(startIndex, currentExpressionIndex)));
                    return operation.CONST;
                } else if (expression.startsWith("abs", currentExpressionIndex - 1)) {
                    CheckUnaryAndMovePointer(2);
                    return operation.ABS;
                } else if (expression.startsWith("sqrt", currentExpressionIndex - 1)) {
                    CheckUnaryAndMovePointer(3);
                    return operation.SQRT;
                } else if (expression.startsWith("low", currentExpressionIndex - 1)) {
                    CheckUnaryAndMovePointer(2);
                    return operation.LOW;
                } else if (expression.startsWith("high", currentExpressionIndex - 1)) {
                    CheckUnaryAndMovePointer(3);
                    return operation.HIGH;
                } else {
                    throw new UnknownSymbolException(currentExpressionIndex - 1);
                }
            }
        }
    }

}
