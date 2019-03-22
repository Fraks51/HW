import md2html.Md2HtmlException;
import md2html.Md2HtmlParser;
import md2html.Md2HtmlSource;

import java.io.FileWriter;
import java.io.IOException;

public class Md2HtmlTests {
    public static void main(String[] args) throws Md2HtmlException, IOException {
        try (Md2HtmlSource s = new Md2HtmlSource("C:\\Users\\MSI\\HomeWork\\Markdown2HtmlParser\\src\\test.txt")) {
            Md2HtmlParser p = new Md2HtmlParser(s);
            try (FileWriter writer = new FileWriter("C:\\Users\\MSI\\HW\\Markdown2HtmlParser\\src\\lol.txt")) {
                writer.write(p.parse());
            }
            System.out.print(p.parse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
