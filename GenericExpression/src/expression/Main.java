package expression;

import expression.exceptions.ArgsException;
import expression.exceptions.IllegalOperationException;
import expression.exceptions.ParsingException;
import expression.parser.ExpressionParser;
import expression.parser.Parser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage arg for choice number type");
            return;
        }
        /*if (args.length != 1) {
            System.out.println("Usage only 1 arg");
        }
        switch (args[0]) {
            case "-i": {
                break;
            }
            case "-d": {
                break;
            }
            case "-bi": {
                break;
            }
            default: {

            }
        }
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        int x = in.nextInt(), y = in.nextInt(), z = in.nextInt();
        Parser parserExpr = new ExpressionParser();
        try {
            System.out.println(parserExpr.parse(expression).evaluate(x, y, z));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        */
    }
}
