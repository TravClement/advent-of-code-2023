package day3;

import java.io.FileNotFoundException;
import java.util.*;

import static day3.Schematic.getLines;

public class GearRatio {
    Map<String, List<Integer>> gearsToNums;

    public GearRatio() {
        gearsToNums = new HashMap<>();
    }

    public int findGearRatios(String inputPath) throws FileNotFoundException {
        List<String> lines = getLines(inputPath);
        int total = 0;

        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            int num = 0;
            int numInd = 0;
            boolean  addNum = false;
            Set<String> surroundingGears = new HashSet<>();
            for (int col = line.length() - 1; col >= 0; col--) {
                char c = line.charAt(col);
                if (Character.isDigit(c)) {
                    num += (int) (Character.getNumericValue(c) * Math.pow(10, numInd));
                    numInd++;
                    String gearLoc = isGearAround(lines, row, col);
                    addNum = addNum || gearLoc != null;
                    if (gearLoc !=  null) {
                        surroundingGears.add(gearLoc);
                    }
                } else {
                    numInd = 0;
                    if (addNum) {
                        for (String gearLoc : surroundingGears) {
                            if (!gearsToNums.containsKey(gearLoc)) {
                                gearsToNums.put(gearLoc, new ArrayList<>());
                            }
                            gearsToNums.get(gearLoc).add(num);
                        }
                    }
                    addNum = false;
                    num = 0;
                    surroundingGears.clear();
                }
            }
            if (addNum) {
                for (String gearLoc : surroundingGears) {
                    if (!gearsToNums.containsKey(gearLoc)) {
                        gearsToNums.put(gearLoc, new ArrayList<>());
                    }
                    gearsToNums.get(gearLoc).add(num);
                }
            }
        }
        return calculateTotal();
    }

    private int calculateTotal() {
        int total = 0;
        for (String gearLoc : gearsToNums.keySet()) {
            List<Integer> surroundingNums = gearsToNums.get(gearLoc);
            System.out.println(Arrays.toString(surroundingNums.toArray()));
            if (surroundingNums.size() == 2) {
                total += surroundingNums.get(0) * surroundingNums.get(1);
            }
        }
        return total;
    }

    // only called when the character at [row, col] is a digit
    // returns null if no gear is around
    public static String isGearAround(List<String> lines, int row, int col) {
        for (int rowMod = -1; rowMod <= 1; rowMod++) {
            for (int colMod = -1; colMod <= 1; colMod++) {
                int newRow = row + rowMod;
                int newCol = col + colMod;
                if (newRow >= 0 && newRow < lines.size() && newCol >= 0 && newCol < lines.get(row).length()) {
                    char c = lines.get(newRow).charAt(newCol);
                    if (!Character.isDigit(c) && c == '*') {
                        return newRow + ":" + newCol;
                    }
                }
            }
        }
        return null;
    }
}
