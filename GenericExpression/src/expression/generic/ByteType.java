package expression.generic;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.SqrtOfNegativeNumberException;

public class ByteType implements Type<Byte> {

    public Byte add(Byte firstOperand, Byte secondOperand) {
        return (byte) (firstOperand + secondOperand);
    }

    public Byte sub(Byte firstOperand, Byte secondOperand) {
        return (byte) (firstOperand - secondOperand);
    }

    public Byte parseNumber(String number) {
        return (byte) Integer.parseInt(number);
    }

    public Byte min(Byte firstOperand, Byte secondOperand) {
        if (firstOperand < secondOperand) {
            return firstOperand;
        } else {
            return secondOperand;
        }
    }

    public Byte max(Byte firstOperand, Byte secondOperand) {
        if (firstOperand > secondOperand) {
            return firstOperand;
        } else {
            return secondOperand;
        }
    }

    public Byte sqrt(Byte operand) throws SqrtOfNegativeNumberException {
        if (operand < 0) {
            throw new SqrtOfNegativeNumberException();
        }
        return (byte) Math.sqrt(operand);
    }

    public Byte negate(Byte operand) {
        return (byte) (-operand);
    }

    public Byte mul(Byte firstOperand, Byte secondOperand) {
        return (byte) (firstOperand * secondOperand);
    }

    public Byte div(Byte firstOperand, Byte secondOperand) throws DivisionByZeroException {
        if (secondOperand == 0) {
            throw new DivisionByZeroException();
        }
        return (byte) (firstOperand / secondOperand);
    }

    public Byte abs(Byte operand) {
        if (operand >= 0) {
            return operand;
        } else {
            return (byte) -operand;
        }
    }

    public Byte mod(Byte firstOperand, Byte secondOperand) throws DivisionByZeroException {
        if (secondOperand == 0) {
            throw new DivisionByZeroException();
        }
        return (byte) (firstOperand % secondOperand);
    }
}
