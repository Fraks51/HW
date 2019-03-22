package expression.exceptions;

public class MissingCloseBracketException extends BracketsException {
    public MissingCloseBracketException(int symbolIndex) {
        super("missing close bracket on " + symbolIndex + "position");
    }
}
