package day4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WinningNumbers {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("src/day4/input.txt"));
        List<ScratchCard> scratchCards = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            scratchCards.add(new ScratchCard(line));
        }

        int res = calculateWinnings(scratchCards);
        System.out.println("result: " + res);
    }

    public static int calculateWinnings(List<ScratchCard> scratchCards) {
        int total = 0;
        for (ScratchCard scratchCard : scratchCards) {
//            System.out.println("Winning numbers: " + Arrays.toString(scratchCard.winningNums.toArray()));
            scratchCard.nums.retainAll(scratchCard.winningNums);
//            System.out.println("Nums: " + Arrays.toString(scratchCard.nums.toArray()));
            int wins = scratchCard.nums.size();
            if (wins > 0) {
                total += (int) Math.pow(2, wins - 1);
            }
        }
        return total;
    }
}
