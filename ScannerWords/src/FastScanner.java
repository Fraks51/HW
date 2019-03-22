import java.io.*;
import java.lang.*;
import java.nio.charset.StandardCharsets;

public class FastScanner implements AutoCloseable {
    private static final int READ_NOT_STARTED = -2;
    private InputStreamReader reader;
    private int bufferSize;
    private char[] buffer;
    private int nextIndex;
    private int charsRead;
    private boolean flagForHasNext;
    private char cash;


    public FastScanner(InputStream in, int bufferSize) throws UnsupportedEncodingException {
        reader = new InputStreamReader(in, StandardCharsets.UTF_8.name());
        buffer = new char[bufferSize];
        this.bufferSize = bufferSize;
        nextIndex = 0;
        charsRead = READ_NOT_STARTED;
        flagForHasNext = false;
    }

    public FastScanner(String file, int bufferSize) throws UnsupportedEncodingException, FileNotFoundException {
        this(new FileInputStream(file), bufferSize);
    }

    private boolean isDashOrApostrophe(char currentChar) {
        return Character.getType(currentChar) == Character.DASH_PUNCTUATION || currentChar == '\'';
    }

    private char nextChar() throws IOException {
        if (flagForHasNext) {
            flagForHasNext = false;
        } else {
            nextIndex++;
        }
        if (nextIndex >= bufferSize || charsRead == READ_NOT_STARTED || nextIndex >= charsRead) {
            nextIndex = 0;
            charsRead = reader.read(buffer);
            if (buffer[nextIndex] == 0 || charsRead == -1) {
                return 0;
            }
            return buffer[nextIndex];
        }
        if (charsRead == -1) {
            return 0;
        }
        return buffer[nextIndex];
    }

    public String nextWordOnLine() throws IOException {
        if (cash == '\n') {
            cash = 0;
            return null;
        }
        char currentChar = nextChar();
        while (!Character.isLetter(currentChar) && currentChar != 0 && currentChar != '\n' && !isDashOrApostrophe(currentChar)) {
            currentChar = nextChar();
        }
        if (currentChar == 0 || currentChar == '\n') {
            return null;
        }
        StringBuilder charArray = new StringBuilder();
        while (Character.isLetter(currentChar) || isDashOrApostrophe(currentChar)) {
            charArray.append(currentChar);
            currentChar = nextChar();
        }
        if (currentChar == '\r') {
            currentChar = nextChar();
            cash = '\n';
        }
        if (currentChar == '\n' || currentChar == 0) {
            cash = '\n';
        }
        return charArray.toString();
    }

    public boolean hasNextLine() throws IOException {
        nextIndex++;
        flagForHasNext = true;
        if (nextIndex >= bufferSize || charsRead == READ_NOT_STARTED || nextIndex >= charsRead) {
            nextIndex = 0;
            charsRead = reader.read(buffer);
            return charsRead != -1;
        }
        return !(charsRead == -1);
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
