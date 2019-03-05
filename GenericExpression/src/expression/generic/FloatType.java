package expression.generic;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowNumberException;
import expression.exceptions.OverflowOperationException;
import expression.exceptions.SqrtOfNegativeNumberException;

public class FloatType implements Type<Float> {
    public Float add(Float firstOperand, Float secondOperand) throws OverflowOperationException {
        return firstOperand + secondOperand;
    }

    public Float sub(Float firstOperand, Float secondOperand) throws OverflowOperationException {
        return firstOperand - secondOperand;
    }

    public Float parseNumber(String number) throws OverflowNumberException {
        try {
            return Float.parseFloat(number);
        } catch (Exception ex) {
            throw new OverflowNumberException(number);
        }
    }

    public Float min(Float firstOperand, Float secondOperand) {
        if (firstOperand > secondOperand) {
            return secondOperand;
        } else {
            return firstOperand;
        }
    }

    public Float max(Float firstOperand, Float secondOperand) {
        if (firstOperand > secondOperand) {
            return firstOperand;
        } else {
            return secondOperand;
        }
    }

    public Float sqrt(Float operand) throws SqrtOfNegativeNumberException {
        if (operand < 0) {
            throw new SqrtOfNegativeNumberException();
        }
        return (float) Math.sqrt(operand);
    }

    public Float negate(Float operand) throws OverflowOperationException {
        return -operand;
    }

    public Float mul(Float firstOperand, Float secondOperand) throws OverflowOperationException {
        return firstOperand * secondOperand;
    }

    public Float div(Float firstOperand, Float secondOperand) throws DivisionByZeroException, OverflowOperationException {
        return firstOperand / secondOperand;
    }

    public Float abs(Float operand) throws OverflowOperationException {
        if (operand >= 0) {
            return operand;
        } else {
            return -operand;
        }
    }
}
