package expression.generic;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowNumberException;
import expression.exceptions.OverflowOperationException;
import expression.exceptions.SqrtOfNegativeNumberException;

import java.math.BigInteger;

public class BigIntType implements Type<BigInteger> {

    public BigInteger add(BigInteger firstOperand, BigInteger secondOperand) {
        return firstOperand.add(secondOperand);
    }

    public BigInteger sub(BigInteger firstOperand, BigInteger secondOperand) {
        return firstOperand.subtract(secondOperand);
    }

    public BigInteger parseNumber(String number) {
        return new BigInteger(number);
    }

    public BigInteger min(BigInteger firstOperand, BigInteger secondOperand) {
        return firstOperand.min(secondOperand);
    }

    public BigInteger max(BigInteger firstOperand, BigInteger secondOperand) {
        return firstOperand.max(secondOperand);
    }

    public BigInteger sqrt(BigInteger operand) throws SqrtOfNegativeNumberException {
        if (operand.compareTo(BigInteger.ZERO) < 0) {
            throw new SqrtOfNegativeNumberException();
        }
        return operand.sqrt();
    }

    public BigInteger negate(BigInteger operand) {
        return operand.negate();
    }

    public BigInteger mul(BigInteger firstOperand, BigInteger secondOperand) {
        return firstOperand.multiply(secondOperand);
    }

    public BigInteger div(BigInteger firstOperand, BigInteger secondOperand) throws DivisionByZeroException {
        if (secondOperand.compareTo(BigInteger.ZERO) == 0) {
            throw new DivisionByZeroException();
        }
        return firstOperand.divide(secondOperand);
    }

    public BigInteger abs(BigInteger operand) {
        return operand.abs();
    }

}
