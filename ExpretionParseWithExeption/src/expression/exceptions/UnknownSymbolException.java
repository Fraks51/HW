package expression.exceptions;

public class UnknownSymbolException extends ParsingException {
    public UnknownSymbolException(int symbolIndex) {
        super("unknown symbol on " + symbolIndex + " index");
    }
}
