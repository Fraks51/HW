import java.io.*;
import java.lang.*;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            FastScanner scanner = new FastScanner("in.txt", 342);
            ArrayList<String> list = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String current = scanner.nextWordOnLine();
                while (current != null) {
                    list.add(current);
                    current = scanner.nextWordOnLine();
                }
            }
            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
