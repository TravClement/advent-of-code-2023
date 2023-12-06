package Shared;

import java.math.BigInteger;

public class Util {
    public static int[] getWhitespaceSeparatedNums(String s) {
        String[] stringNums = s.trim().split("\\s+");
        int[] nums = new int[stringNums.length];
        for (int i = 0; i < stringNums.length; i++) {
            nums[i] = Integer.parseInt(stringNums[i]);
        }
        return nums;
    }

    public static BigInteger[] getWhitespaceSeparatedBigInts(String s) {
        String[] stringNums = s.trim().split("\\s+");
        BigInteger[] nums = new BigInteger[stringNums.length];
        for (int i = 0; i < stringNums.length; i++) {
            nums[i] = new BigInteger(stringNums[i]);
        }
        return nums;
    }
}
