package day5;

import Shared.Util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SeedMap {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("src/day5/input.txt"));
        String seedLine = sc.nextLine();
        BigInteger[] seeds = parseSeedLine(seedLine);

        sc.nextLine(); // get rid of the extra line after seeds
        AlmanacRules rules = new AlmanacRules(sc);

        BigInteger res = getSmallestLocation(rules, seeds);
        System.out.println(res);
    }

    public static BigInteger[] parseSeedLine(String line) {
        String s = line.split(":")[1];
        BigInteger[] ranges = Util.getWhitespaceSeparatedBigInts(s);
        return ranges;
    }

    public static BigInteger getSmallestLocation(AlmanacRules rules, BigInteger[] seeds) {
       List<BigInteger> locs = new ArrayList<>();
        for (BigInteger seed : seeds) {
            BigInteger soil = findNewValue(seed, rules.seedToSoil);
            BigInteger fert = findNewValue(soil, rules.soilToFert);
            BigInteger water = findNewValue(fert, rules.fertToWater);
            BigInteger light = findNewValue(water, rules.waterToLight);
            BigInteger temp = findNewValue(light, rules.lightToTemp);
            BigInteger humid = findNewValue(temp, rules.tempToHumid);
            BigInteger loc = findNewValue(humid, rules.humidToLoc);
            locs.add(loc);
        }

        return locs.stream().min(BigInteger::compareTo).get();
    }

    public static BigInteger findNewValue(BigInteger input, List<Rule> mapRules) {
        for (Rule r : mapRules) {
            if (input.compareTo(r.sourceStart) >= 0 && input.compareTo(r.sourceStart.add(r.range)) <= 0) {
                return r.destStart.add(input).subtract(r.sourceStart);
            }
        }
        return new BigInteger(input.toByteArray());
    }
}
