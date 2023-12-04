package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trebuchet {
    public static void main(String[] args) {
        try {
            int total = fixInput();
            System.out.println("Total: " + total);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   public static int fixInput() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/day1/input.txt"));
        int total = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
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
            total +=  firstNum * 10 + lastNum;
        }
        return total;
   }
}
