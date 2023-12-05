package day3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Schematic {
    public static void main(String[] args) throws FileNotFoundException {
        int res = sumParts("src/day3/input.txt");
        System.out.println("result: " + res);
    }

    public static int sumParts(String inputPath) throws FileNotFoundException {
        int total = 0;
        List<String> lines = new ArrayList<>();
        Scanner sc = new Scanner(new FileReader(inputPath));
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            int num = 0;
            int numInd = 0;
            boolean  addNum = false;
            for (int col = line.length() - 1; col >= 0; col--) {
                char c = line.charAt(col);
                if (Character.isDigit(c)) {
                    num += (int) (Character.getNumericValue(c) * Math.pow(10, numInd));
                    numInd++;
                    addNum = addNum || isSymbolAround(lines, row, col);
                } else {
                    numInd = 0;
                    if (addNum) {
                        total += num;
                    }
                    addNum = false;
                    num = 0;
                }
            }
            if (addNum) {
                total += num;
            }
            addNum = false;
        }

        return total;
    }

    // only called when the character at [row, col] is a digit
    public static boolean isSymbolAround(List<String> lines, int row, int col) {
        for (int rowMod = -1; rowMod <= 1; rowMod++) {
            for (int colMod = -1; colMod <= 1; colMod++) {
                int newRow = row + rowMod;
                int newCol = col + colMod;
                if (newRow >= 0 && newRow < lines.size() && newCol >= 0 && newCol < lines.get(row).length()) {
                    char c = lines.get(newRow).charAt(newCol);
                    if (!Character.isDigit(c) && c != '.') {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
