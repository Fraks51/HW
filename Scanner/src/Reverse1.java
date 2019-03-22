import java.util.*;
import java.lang.*;
import java.io.*;

public class Reverse1 {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        a = new ArrayList<String>();
        while (in.next()) {
            String s = in.nextLine();
            a.add(s);
        }

        for (int i = matrix.size() - 1; i >= 0; i--) {
            for (int j = matrix.get(i).size() - 1; j >= 0; j--)
                System.out.print(matrix.get(i).get(j) + " ");
            System.out.println();
        }
    }
}