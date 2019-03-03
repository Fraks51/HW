package expression.exceptions;

public class ExcessCloseBracketException extends BracketsException {
    public ExcessCloseBracketException() {
        super("missing close bracket");
    }
}
