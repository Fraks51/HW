package expression.generic;

import expression.exceptions.OverflowNumberException;
import expression.exceptions.OverflowOperationException;
import expression.exceptions.SqrtOfNegativeNumberException;

public class DoubleType implements Type<Double> {

    public Double add(Double firstOperand, Double secondOperand) throws OverflowOperationException {
        return firstOperand + secondOperand;
    }

    public Double sub(Double firstOperand, Double secondOperand) throws OverflowOperationException {
        return firstOperand - secondOperand;
    }

    public Double parseNumber(String number) throws OverflowNumberException {
        try {
            return Double.parseDouble(number);
        } catch (Exception ex) {
            throw new OverflowNumberException(number);
        }
    }

    public Double min(Double firstOperand, Double secondOperand) {
        if (firstOperand > secondOperand) {
            return secondOperand;
        } else {
            return firstOperand;
        }
    }

    public Double max(Double firstOperand, Double secondOperand) {
        if (firstOperand > secondOperand) {
            return firstOperand;
        } else {
            return secondOperand;
        }
    }

    public Double sqrt(Double operand) throws SqrtOfNegativeNumberException {
        if (operand < 0) {
            throw new SqrtOfNegativeNumberException();
        }
        return Math.sqrt(operand);
    }

    public Double negate(Double operand) {
        return -operand;
    }

    public Double mul(Double firstOperand, Double secondOperand) {
        return firstOperand * secondOperand;
    }

    public Double div(Double firstOperand, Double secondOperand) {
        return firstOperand / secondOperand;
    }

    public Double abs(Double operand) {
        if (operand >= 0) {
            return operand;
        } else {
            return -operand;
        }
    }
}
