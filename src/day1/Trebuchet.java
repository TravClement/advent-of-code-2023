package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.util.Map.entry;

public class Trebuchet {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            int total = fixInputPart2();
            System.out.println("Total: " + total);
        } catch (Exception e) {
            throw e;
        }
    }

   public static int fixInput() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/day1/input.txt"));
        int total = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            total += fixLine(line);
        }
        return total;
   }

   public static int fixLine(String line) {
       int firstNum = -1;
       int lastNum = -1;
       for (char c : line.toCharArray()) {
           if (Character.isDigit(c)) {
               if (firstNum < 0) {
                   firstNum = Character.getNumericValue(c);
               }
               lastNum = Character.getNumericValue(c);
           }
       }
       return firstNum * 10 + lastNum;
   }

    public static int fixInputPart2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/day1/input.txt"));
        int total = 0;

        Map<String, Integer> writtenNumbers = Map.ofEntries(
                entry("one", 1),
                entry("two", 2),
                entry("three", 3),
                entry("four", 4),
                entry("five", 5),
                entry("six", 6),
                entry("seven", 7),
                entry("eight", 8),
                entry("nine", 9)
        );

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            Set<String> matchingWords = new HashSet<>(writtenNumbers.keySet());
            int firstNum = -1;
            int lastNum = -1;
            int matchingIndex = 0;

            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (Character.isDigit(c)) {
                    matchingWords = new HashSet<>(writtenNumbers.keySet());
                    matchingIndex = 0;
                    if (firstNum < 0) {
                        firstNum = Character.getNumericValue(c);
                    }
                    lastNum = Character.getNumericValue(c);
                } else {
                    Set<String> removingNum = new HashSet<>();
                    boolean found = false;
                    for (String writtenNum : matchingWords) {
                        if (writtenNum.charAt(matchingIndex) != c) {
                            removingNum.add(writtenNum);
                        } else if (matchingIndex == writtenNum.length() - 1) {
                            if (firstNum < 0) {
                                firstNum = writtenNumbers.get(writtenNum);
                            }
                            lastNum = writtenNumbers.get(writtenNum);
                            matchingWords = new HashSet<>(writtenNumbers.keySet());
                            i--;
                            matchingIndex = 0;
                            found = true;
                        }
                    }
                    if (!found) {
                        matchingWords.removeAll(removingNum);
                        if (matchingWords.isEmpty()) {
                            matchingWords = new HashSet<>(writtenNumbers.keySet());
                            if (removingNum.size() < writtenNumbers.size()) {
                                i -= matchingIndex;
                            }
                            matchingIndex = 0;
                        } else {
                            matchingIndex++;
                        }
                    }
                }
            }
            total +=  firstNum * 10 + lastNum;

            int part2Solution = firstNum * 10 + lastNum;
        }
        return total;
    }
}
