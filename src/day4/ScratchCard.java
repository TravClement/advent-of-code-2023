package day4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ScratchCard {
    int cardNum;
    Set<Integer> winningNums;
    Set<Integer> nums;

    public ScratchCard(String line) {
        String[] metaTokens = line.split(":");
        String[] numbers = metaTokens[1].split("\\|");
        cardNum = Integer.parseInt(metaTokens[0].split("\\s+")[1]);

        String[] winningNumTokens = numbers[0].trim().split("\\s+");
        String[] numTokens = numbers[1].trim().split("\\s+");

        winningNums = new HashSet<>();
        for (String num : winningNumTokens) {
            winningNums.add(Integer.parseInt(num));
        }

        nums = new HashSet<>();
        for (String num : numTokens) {
            nums.add(Integer.parseInt(num));
        }
    }

    public int calculateWins() {
        nums.retainAll(winningNums);
        return nums.size();
    }
}
