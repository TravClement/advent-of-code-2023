package day5;

import Shared.Util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;

public class SeedMap {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("src/day5/input.txt"));
        String seedLine = sc.nextLine();
//        List<BigInteger> seeds = parseSeedLine(seedLine);
        List<Rule> seedRanges = parseSeedRanges(seedLine);

        sc.nextLine(); // get rid of the extra line after seeds
        AlmanacRules rules = new AlmanacRules(sc);

//        BigInteger res = getSmallestLocation(rules, seeds);
        System.out.println(res);
    }

    public static List<BigInteger> parseSeedLine(String line) {
        String s = line.split(":")[1];
        BigInteger[] ranges = Util.getWhitespaceSeparatedBigInts(s);
        List<BigInteger> ret = new ArrayList<>();
        for (int i = 0; i < ranges.length - 1; i += 2) {
            BigInteger start = ranges[i];
            BigInteger end = ranges[i + 1];
            for (BigInteger j = start; j.compareTo(start.add(end)) <= 0; j = j.add(BigInteger.ONE)) {
                ret.add(new BigInteger(j.toByteArray()));
            }
        }
        return ret;
    }

    public static BigInteger getSmallestLocation(AlmanacRules rules, List<BigInteger> seeds) {
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

    public static List<Rule> parseSeedRanges(String line) {
        List<Rule> seedRules = new ArrayList<>();
        BigInteger[] ranges = Util.getWhitespaceSeparatedBigInts(line);
        for (int i = 0; i < ranges.length - 1; i += 2) {
            BigInteger start = ranges[i];
            BigInteger range = ranges[i + 1];
            seedRules.add(new Rule(start, BigInteger.ZERO, range));
        }
        return seedRules;
    }

    public static BigInteger getSmallestLocationFromRanges(List<Rule> seedRule) {

    }

    //  for each source range
    //      for each dest range
    //          if the source range crosses the dest range boundary
    //              break up the source range into chunks for the destination range
    // 1 2 3 4 5 6 7 8 9
    // input: 1 -> 6
    // dest: 3 -> 7 goes to 4 -> 8
    // there can only be 1 range that the destination rules source covers
    public static List<Rule> findNewRanges(List<Rule> input, List<Rule> dest) {
        List<Rule> newRanges = new ArrayList<>();
        Queue<Rule> inputRanges = new LinkedList<>(input);
        while (!inputRanges.isEmpty()) {
            Rule inputRange = inputRanges.remove();
            for (Rule destRange : dest) {
                // if input range crosses dest range
                if (inputRange.sourceStart.compareTo(destRange.sourceStart) <= 0 && inputRange.sourceStart.add(inputRange.range).compareTo(destRange.sourceStart) >= 0 ||
                    inputRange.sourceStart.compareTo(destRange.sourceStart) >= 0 && inputRange.sourceStart.compareTo(destRange.sourceStart.add(destRange.range)) <= 0) {
                    // lower bound: max(input source, dest source)
                    // upper bound: min (input source + range, dest source + range)

                }
            }
        }
    }
}
