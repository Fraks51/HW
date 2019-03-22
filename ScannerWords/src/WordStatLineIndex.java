import java.io.*;
import java.lang.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import javafx.util.Pair;

public class WordStatLineIndex {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage java WordStatLineIndex <input> <output>");
            return;
        }
        Map<String, StatLineIndexStruct> wordsStatIndexMap = new TreeMap<>();
        try (FastScanner scanner = new FastScanner(args[0], 512)) {
            int numberLine = 1;
            while (scanner.hasNextLine()) {
                String currentWord = scanner.nextWordOnLine();
                int numberWord = 1;
                while (currentWord != null) {
                    currentWord = currentWord.toLowerCase();
                    StatLineIndexStruct tempStatLineIndexStruct = new StatLineIndexStruct();
                    Pair<Integer, Integer> tempPair = new Pair<>(numberLine, numberWord);
                    if (wordsStatIndexMap.get(currentWord) != null) {
                        tempStatLineIndexStruct.statWord = wordsStatIndexMap.get(currentWord).statWord + 1;
                        tempStatLineIndexStruct.indexesOnLines = wordsStatIndexMap.get(currentWord).indexesOnLines;
                        tempStatLineIndexStruct.indexesOnLines.add(tempPair);
                        wordsStatIndexMap.put(currentWord, tempStatLineIndexStruct);
                    } else {
                        tempStatLineIndexStruct.statWord = 1;
                        tempStatLineIndexStruct.indexesOnLines.add(tempPair);
                        wordsStatIndexMap.put(currentWord, tempStatLineIndexStruct);
                    }
                    currentWord = scanner.nextWordOnLine();
                    numberWord++;
                }
                numberLine++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file didn't find");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Input file wrong encoding, please use UTF-8");
        } catch (IOException e) {
            System.out.println("IOException");
        }

        try (PrintWriter fileWriter = new PrintWriter(args[1], StandardCharsets.UTF_8.name())) {
            for (Map.Entry<String, StatLineIndexStruct> currentEntryMap : wordsStatIndexMap.entrySet()) {
                fileWriter.print(currentEntryMap.getKey() + " ");
                fileWriter.print(currentEntryMap.getValue().statWord);
                for (Pair<Integer, Integer> currentPair : currentEntryMap.getValue().indexesOnLines) {
                    fileWriter.print(" " + currentPair.getKey() + ":" + currentPair.getValue());
                }
                fileWriter.println();
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("Output file wrong encoding, please use UTF-8");
        } catch (FileNotFoundException e) {
            System.out.println("Output file could not create");
        }
    }
}