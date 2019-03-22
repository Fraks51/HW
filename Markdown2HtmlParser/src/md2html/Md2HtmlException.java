package md2html;

public class Md2HtmlException extends Throwable {
    public Md2HtmlException(final String message, Object... args) {
        super(String.format(message, args));
    }
}
