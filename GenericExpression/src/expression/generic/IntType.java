package expression.generic;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowNumberException;
import expression.exceptions.OverflowOperationException;
import expression.exceptions.SqrtOfNegativeNumberException;

public class IntType implements Type<Integer> {

    public Integer add(final Integer firstOperand, final Integer secondOperand) throws OverflowOperationException {
        if (firstOperand > 0 && Integer.MAX_VALUE - firstOperand < secondOperand) {
            throw new OverflowOperationException("add", firstOperand, secondOperand);
        }
        if (firstOperand < 0 && Integer.MIN_VALUE - firstOperand > secondOperand) {
            throw new OverflowOperationException("add", firstOperand, secondOperand);
        }
        return firstOperand + secondOperand;
    }

    public Integer sub(final Integer firstOperand, final Integer secondOperand) throws OverflowOperationException {
        if ((firstOperand >= 0 && secondOperand < 0 && firstOperand - Integer.MAX_VALUE > secondOperand) ||
                (firstOperand <= 0 && secondOperand > 0 && Integer.MIN_VALUE - firstOperand > -secondOperand)) {
            throw new OverflowOperationException("subtract", firstOperand, secondOperand);
        }
        return firstOperand - secondOperand;
    }

    public Integer mul(final Integer firstOperand, final Integer secondOperand) throws OverflowOperationException {
        if ((firstOperand > 0 && secondOperand > 0 && Integer.MAX_VALUE / firstOperand < secondOperand) ||
                (firstOperand < 0 && secondOperand < 0 && Integer.MAX_VALUE / firstOperand > secondOperand) ||
                (firstOperand > 0 && secondOperand < 0 && Integer.MIN_VALUE / firstOperand > secondOperand) ||
                (firstOperand < 0 && secondOperand > 0 && Integer.MIN_VALUE / secondOperand > firstOperand)) {
            throw new OverflowOperationException("multiply", firstOperand, secondOperand);
        }
        return firstOperand * secondOperand;
    }

    public Integer parseNumber(final String number) throws OverflowNumberException {
        try {
            return Integer.parseInt(number);
        } catch (Exception ex) {
            throw new OverflowNumberException(number);
        }
    }

    public Integer max(final Integer firstOperand, final Integer secondOperand) {
        if (firstOperand > secondOperand) {
            return firstOperand;
        } else {
            return secondOperand;
        }
    }

    public Integer min(final Integer firstOperand, final Integer secondOperand) {
        if (firstOperand > secondOperand) {
            return secondOperand;
        } else {
            return firstOperand;
        }
    }

    public Integer sqrt(final Integer operand) throws SqrtOfNegativeNumberException {
        if (operand < 0) {
            throw new SqrtOfNegativeNumberException();
        }
        return (int) Math.sqrt(operand);
    }

    public Integer negate(final Integer operand) throws OverflowOperationException {
        if (operand == Integer.MIN_VALUE) {
            throw new OverflowOperationException("unary minus", operand);
        }
        return -operand;
    }

    public Integer div(final Integer firstOperand, final Integer secondOperand) throws DivisionByZeroException, OverflowOperationException {
        if (secondOperand == 0) {
            throw new DivisionByZeroException();
        }
        if (firstOperand == Integer.MIN_VALUE && secondOperand == -1) {
            throw new OverflowOperationException("divide", firstOperand, secondOperand);
        }
        return firstOperand / secondOperand;
    }

    public Integer abs(final Integer operand) throws OverflowOperationException {
        if (operand.equals(Integer.MIN_VALUE)) {
            throw new OverflowOperationException("abs", operand);
        }
        if (operand >= 0) {
            return operand;
        } else {
            return -operand;
        }
    }
}
