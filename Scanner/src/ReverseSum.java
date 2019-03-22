import java.io.IOException;
import java.util.*;

public class ReverseSum {
    public static void main(String[] args) {
        try {
            FastScanner scanner = new FastScanner(System.in, 512);
            ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
            ArrayList<Integer> strSum = new ArrayList<>();
            ArrayList<Integer> colomnSum = new ArrayList<>();
            String currentStr;
            ArrayList<Integer> arrayNumber;
            while (scanner.HasNextLine()) {
                arrayNumber = new ArrayList<>();
                int sumStr = 0;
                int i = 0;
                while ((currentStr = scanner.nextWordOnLine()) != null) {
                    int tempInt = Integer.parseInt(currentStr);
                    arrayNumber.add(tempInt);
                    sumStr += arrayNumber.get(arrayNumber.size() - 1);
                    if (colomnSum.size() <= i) {
                        colomnSum.add(tempInt);
                    } else {
                        colomnSum.set(i, colomnSum.get(i) + tempInt);
                    }
                    i++;
                }
                matrix.add(arrayNumber);
                strSum.add(sumStr);

            }
            for (int i = 0; i < matrix.size(); i++) {
                for (int j = 0; j < matrix.get(i).size(); j++) {
                    System.out.print((strSum.get(i) + colomnSum.get(j) - matrix.get(i).get(j)) + " ");
                }
                System.out.print("\n");
            }
            scanner.close();
        } catch (NumberFormatException ex) {
            System.out.println("Not a number!!!");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}