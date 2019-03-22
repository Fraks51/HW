import java.io.IOException;
import java.io.InputStream;

public class Test {
    public static void main(String[] args) throws IOException {
        FastScanner scan = new FastScanner(System.in);
        byte[] buffer = new byte[8];
        InputStream reader = System.in;
        reader.read(buffer);
        for (int i = 0; i < 8; i++) {
            System.out.println(buffer[i]);
        }
        buffer = new byte[8];
        reader.read(buffer);
        for (int i = 0; i < 8; i++) {
            System.out.println(buffer[i]);
        }
//        while (scan.HasLine()) {
//            System.out.print(scan.nextInt() + " ");
//        }
        //while ((a = scan.nextByte()) != -1) {
        //  System.out.print((char)a);
        //}
    }
}