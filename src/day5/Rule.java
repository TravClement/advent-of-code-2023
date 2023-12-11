package day5;

import java.math.BigInteger;
import java.util.StringTokenizer;

public class Rule {
    public BigInteger sourceStart;
    public BigInteger destStart;
    public BigInteger range;

    // rule has to be of form <num> <num> <num>
    public Rule(String rule) {
        StringTokenizer st = new StringTokenizer(rule);
        destStart = new BigInteger(st.nextToken());
        sourceStart = new BigInteger(st.nextToken());
        range = new BigInteger(st.nextToken());
    }

    public Rule(BigInteger sourceStart, BigInteger destStart, BigInteger range) {
        this.sourceStart = sourceStart;
        this.destStart = destStart;
        this.range = range;
    }
}
