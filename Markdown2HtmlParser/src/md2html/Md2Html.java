package md2html;

import java.io.*;

public class Md2Html {
    public static void main(String[] args) throws Md2HtmlException {
        if (args.length != 2) {
            System.out.println("Usage java WordStatLineIndex <input> <output>");
            return;
        }
        try (Md2HtmlSource s = new Md2HtmlSource(args[0])) {
            Md2HtmlParser p = new Md2HtmlParser(s);
            try (FileWriter writer = new FileWriter(args[1])) {
                writer.write(p.parse());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

