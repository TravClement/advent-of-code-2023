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

//        int res = calculateWinnings(scratchCards);

        int res = calculateCascadingCards(scratchCards);
        System.out.println("result: " + res);
    }

    public static int calculateWinnings(List<ScratchCard> scratchCards) {
        int total = 0;
        for (ScratchCard scratchCard : scratchCards) {
            int wins = scratchCard.calculateWins();
            if (wins > 0) {
                total += (int) Math.pow(2, wins - 1);
            }
        }
        return total;
    }

    public static int calculateCascadingCards(List<ScratchCard> scratchCards) {
        int[] cardWins = new int[scratchCards.size()];
        int[] numCards = new int[scratchCards.size()];
        for (int i = 0; i < numCards.length; i++) {
            numCards[i] = 1;
        }

        for (ScratchCard scratchCard : scratchCards) {
            int wins = scratchCard.calculateWins();
            cardWins[scratchCard.cardNum - 1] = wins;
        }
        for (int i = 0; i < cardWins.length; i++) {
            int wins = cardWins[i];
            for (int j = 1; j <= wins; j++) {
                numCards[i + j] += numCards[i];
            }
        }

        int total = 0;
        for (int i = 0; i < numCards.length; i++) {
            total += numCards[i];
        }
        return total;
    }
}
