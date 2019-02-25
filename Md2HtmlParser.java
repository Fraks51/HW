package md2html;

import java.util.Stack;

public class Md2HtmlParser {
    private Md2HtmlSource source;
    private Stack<String> tagsStack;
    private StringBuilder finalHtmlFile;
    private char currentChar;
    private boolean wasOpenParagraphTag;
    private final String CLOSE_TAG_FOR_PARAGRAPH_AND_HEADLINES = "close";

    public Md2HtmlParser(Md2HtmlSource source) {
        this.source = source;
        finalHtmlFile = new StringBuilder();
        tagsStack = new Stack<>();
        wasOpenParagraphTag = false;
    }

    private void makeOpenParagraphTag() {
        tagsStack.push("<p>");
        finalHtmlFile.append("<p>");
        source.returnLastChar();
        wasOpenParagraphTag = true;
    }

    private void makeOpenHeadlineTag() throws Md2HtmlException {
        int counter = 1;
        while (source.nextChar() == '#') {
            counter++;
        }
        currentChar = source.getChar();
        if (Character.isWhitespace(currentChar)) {
            if (counter > 6) {
                throw new Md2HtmlException("Too many # : %d", counter);
            }
            finalHtmlFile.append(String.format("<h%d>", counter));
            tagsStack.push(String.format("<h%d>", counter));
        } else {
            tagsStack.push("<p>");
            finalHtmlFile.append("<p>");
            for (int i = 0; i < counter; i++) {
                finalHtmlFile.append('#');
            }
            source.returnLastChar();
        }
        wasOpenParagraphTag = true;
    }

    private void writeCloseTag(final String mark, final String tag, final String currentOpenTag) throws Md2HtmlException {
        if (currentOpenTag.equals(tag + mark)) {
            finalHtmlFile.append("</" + tag.substring(1));
        } else {
            throw new Md2HtmlException("Didn't find current open tag, on %d line, for %s", source.getLineNumber(), mark);
        }
    }

    private void makeCloseTag(final String typeCloseTag) throws Md2HtmlException {
        String currentOpenTag = tagsStack.pop();
        switch (typeCloseTag) {
            case CLOSE_TAG_FOR_PARAGRAPH_AND_HEADLINES: {
                if (currentOpenTag.startsWith("<p>") || currentOpenTag.startsWith("<h")) {
                    finalHtmlFile.deleteCharAt(finalHtmlFile.length() - 1);
                    finalHtmlFile.append(String.format("</%s\n", currentOpenTag.substring(1)));
                    wasOpenParagraphTag = false;
                } else {
                    throw new Md2HtmlException("Didn't find open paragraph or headline tag, for %d line", source.getLineNumber());
                }
                break;
            }
            case "*": {
                writeCloseTag("*", "<em>", currentOpenTag);
                break;
            }
            case "_": {
                writeCloseTag("_", "<em>", currentOpenTag);
                break;
            }
            case "**": {
                writeCloseTag("**", "<strong>", currentOpenTag);
                break;
            }
            case "__": {
                writeCloseTag("__", "<strong>", currentOpenTag);
                break;
            }
            case "--": {
                writeCloseTag("--", "<s>", currentOpenTag);
                break;
            }
            case "~": {
                writeCloseTag("~", "<mark>", currentOpenTag);
                break;
            }
        }
    }

    public String parse() throws Md2HtmlException {
        while (source.nextChar() != Md2HtmlSource.END) {
            currentChar = source.getChar();
            if (!wasOpenParagraphTag) {
                if (currentChar == '#') {
                    makeOpenHeadlineTag();
                } else if (currentChar != '\n') {
                    makeOpenParagraphTag();
                }
            } else {
                switch (currentChar) {
                    case '\n': {
                        if (finalHtmlFile.charAt(finalHtmlFile.length() - 1) == '\n') {
                            makeCloseTag(CLOSE_TAG_FOR_PARAGRAPH_AND_HEADLINES);
                        } else {
                            finalHtmlFile.append(currentChar);
                        }
                        break;
                    }
                    case '\\': {
                        screening();
                        break;
                    }
                    case '`': {
                        codeTag();
                        break;
                    }
                    case '>': {
                        finalHtmlFile.append("&gt;");
                        break;
                    }
                    case '<': {
                        finalHtmlFile.append("&lt;");
                        break;
                    }
                    case '&': {
                        finalHtmlFile.append("&amp;");
                        break;
                    }
                    case '*': {
                        findSymbol(currentChar);
                        break;
                    }
                    case '_': {
                        findSymbol(currentChar);
                        break;
                    }
                    case '-': {
                        findSymbol(currentChar);
                        break;
                    }
                    case '~': {
                        parseOneSymbol(currentChar);
                        break;
                    }
                    default:
                        finalHtmlFile.append(currentChar);
                }
            }
        }
        cleanStack();
        return finalHtmlFile.toString();
    }

    private void parseTwoSymbol(final char symbol) throws Md2HtmlException {
        currentChar = source.nextChar();
        if (Character.isWhitespace(currentChar)) {
            finalHtmlFile.append(symbol + symbol + currentChar);
        } else {
            makeOpenTag(Character.toString(symbol) + Character.toString(symbol));
            source.returnLastChar();
        }
    }

    private void checkOpenOrClose(final String checkedTag, final char symbol) throws Md2HtmlException {
        if (tagsStack.peek().startsWith(checkedTag) && !Character.isWhitespace(finalHtmlFile.charAt(finalHtmlFile.length() - 1))) {
            makeCloseTag(Character.toString(symbol) + Character.toString(symbol));
        } else {
            parseTwoSymbol(symbol);
        }
    }

    private void findSymbol(char symbol) throws Md2HtmlException {
        currentChar = source.nextChar();
        if (currentChar == symbol) {
            switch (symbol) {
                case '-': {
                    checkOpenOrClose("<s>", symbol);
                    break;
                }
                case '*': {
                    checkOpenOrClose("<strong>", symbol);
                    break;
                }
                case '_': {
                    checkOpenOrClose("<strong>", symbol);
                    break;
                }
                default: {
                    parseTwoSymbol(symbol);
                }
            }
        } else {
            parseOneSymbol(symbol);
        }
    }

    private void codeTag() {
        if (tagsStack.peek().equals("<code>")) {
            tagsStack.pop();
            finalHtmlFile.append("</code>");
        } else {
            tagsStack.push("<code>");
            finalHtmlFile.append("<code>");
        }
    }

    private void parseOneSymbol(char symbol) throws Md2HtmlException {
        if (symbol == '-') {
            finalHtmlFile.append(symbol);
            source.returnLastChar();
            return;
        }
        String tempTag = "";
        switch (symbol) {
            case '~': {
                tempTag = "<mark>";
                break;
            }
            case '*': {
                tempTag = "<em>";
                source.returnLastChar();
                break;
            }
            case '_': {
                tempTag = "<em>";
                source.returnLastChar();
                break;
            }
        }
        if (tagsStack.peek().startsWith(tempTag) && !Character.isWhitespace(finalHtmlFile.charAt(finalHtmlFile.length() - 1))) {
            makeCloseTag(Character.toString(symbol));
        } else {
            if (Character.isWhitespace(currentChar)) {
                finalHtmlFile.append(symbol);
            } else {
                makeOpenTag(Character.toString(symbol));
            }
        }
    }

    private void makeOpenTag(final String markOfOpenTag) {
        switch (markOfOpenTag) {
            case "*": {
                tagsStack.push("<em>*");
                finalHtmlFile.append("<em>");
                break;
            }
            case "~": {
                tagsStack.push("<mark>~");
                finalHtmlFile.append("<mark>");
                break;
            }
            case "_": {
                tagsStack.push("<em>_");
                finalHtmlFile.append("<em>");
                break;
            }
            case "__": {
                tagsStack.push("<strong>__");
                finalHtmlFile.append("<strong>");
                break;
            }
            case "**": {
                tagsStack.push("<strong>**");
                finalHtmlFile.append("<strong>");
                break;
            }
            case "--": {
                tagsStack.push("<s>--");
                finalHtmlFile.append("<s>");
                break;
            }
        }
    }

    private void screening() throws Md2HtmlException {
        currentChar = source.nextChar();
        if (currentChar == Md2HtmlSource.END) {
            finalHtmlFile.append('\\');
        }
        finalHtmlFile.append(currentChar);
    }

    private void cleanStack() throws Md2HtmlException {
        if (tagsStack.empty()) {
            return;
        }
        if (finalHtmlFile.charAt(finalHtmlFile.length() - 1) != '\n') {
            finalHtmlFile.append('\n');
        }
        makeCloseTag(CLOSE_TAG_FOR_PARAGRAPH_AND_HEADLINES);
    }

}