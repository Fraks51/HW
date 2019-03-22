package md2html;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class Md2HtmlSource implements AutoCloseable {
    public static char END = '\0';
    private final Reader reader;
    private char NULL_CHAR = '\uFFFF';
    private int line;
    private int posInLine;
    private char c;
    private boolean hasReturnedChar = false;

    public Md2HtmlSource(final String fileName) throws Md2HtmlException {
        line = 1;
        posInLine = 1;
        try {
            reader = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8));
        } catch (final IOException e) {
            throw new Md2HtmlException("Can't read file: %s", fileName);
        }
    }

    public char nextChar() throws Md2HtmlException {
        if (hasReturnedChar && c != NULL_CHAR) {
            hasReturnedChar = false;
            return c;
        }
        try {
            final int read = reader.read();
            c = (char) read;
            if (c == '\n') {
                line++;
                posInLine = 0;
            }
            if (c == '\r') {
                return nextChar();
            }
            posInLine++;
            return read == -1 ? END : (char) read;
        } catch (IOException e) {
            throw new Md2HtmlException("IOException");
        }
    }

    public char getChar() {
        return c;
    }

    public void returnLastChar() {
        hasReturnedChar = true;
    }

    public int getPosition() {
        return posInLine;
    }

    public int getLineNumber() {
        return line;
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}
