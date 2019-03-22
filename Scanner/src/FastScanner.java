import java.io.*;
import java.lang.*;

public class FastScanner {
    private InputStream reader;
    private int bufferSize = 8192;
    private byte[] buffer = new byte[bufferSize];
    private int nextIndex = 0;
    private int bytesRead = -2;
    private boolean flagHasNext = false;

    public FastScanner(InputStream in, int bufferSize) {
        reader = System.in;
        buffer = new byte[bufferSize];
        this.bufferSize = bufferSize;
    }

    public FastScanner(InputStream in) {
        reader = System.in;
    }

    public byte nextByte() throws IOException {
        if (flagHasNext) {
            flagHasNext = false;
        } else {
            nextIndex++;
        }
        if (nextIndex >= bufferSize || bytesRead == -2 || buffer[nextIndex] == 0) {
            nextIndex = 0;
            buffer = new byte[bufferSize];
            bytesRead = reader.read(buffer);
            if (buffer[nextIndex] == 0 || bytesRead == -1) {
                return 0;
            }
            return buffer[nextIndex];
        }
        if (buffer[nextIndex] == 0 || bytesRead == -1) {
            return 0;
        }
        return buffer[nextIndex];

    }

    public String nextLine() throws IOException {
        byte currentByte = nextByte();
        if (currentByte == 0) {
            return null;
        }
        if (currentByte == (byte) '\r' || currentByte == (byte) '\n') {
            if (currentByte == (byte) '\r') {
                nextByte();
            }
            return System.lineSeparator();
        }
        StringBuilder byteArray = new StringBuilder();
        while (currentByte != (byte) '\r' && currentByte != (byte) '\n') {
            byteArray.append((char) currentByte);
            currentByte = nextByte();
        }
        if (currentByte == (byte) '\r') {
            byteArray.append((char) currentByte);
            currentByte = nextByte();
        }
        byteArray.append((char) currentByte);
        return byteArray.toString();
    }

    public String nextWordOnLine() throws IOException {
        byte currentByte = nextByte();
        if (currentByte == -1) {
            return null;
        }
        if (currentByte == (byte) ' ') {
            currentByte = nextByte();
        }
        if (currentByte == (byte) '\n' || currentByte == (byte) '\r') {
            if (currentByte == (byte) '\r') {
                currentByte = nextByte();
            }
            return null;
        }
        StringBuilder byteArray = new StringBuilder();
        while (currentByte != (byte) ' ' && currentByte != (byte) '\n' && currentByte != (byte) '\r') {
            byteArray.append((char) currentByte);
            currentByte = nextByte();
        }
        return byteArray.toString();
    }

    public Boolean HasNextLine() {
        nextIndex++;
        flagHasNext = true;
        if (nextIndex >= bufferSize || bytesRead == -2 || buffer[nextIndex] == 0) {
            try {
                nextIndex = 0;
                buffer = new byte[bufferSize];
                bytesRead = reader.read(buffer);
                return bytesRead != -1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer[nextIndex] != 0 && bytesRead != -1;
    }

    public void close() throws IOException {
        reader.close();
    }
}
