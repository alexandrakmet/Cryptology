package Lab1.main;

import java.math.BigInteger;

public class KaratsubaMultiplication {
    private final static int MIN_LENGTH_FOR_KARATSUBA = 20;

    public static BigInteger karatsubaMult(BigInteger a, BigInteger b) {
        int n = Math.max(a.bitLength(), b.bitLength());
        if (n < MIN_LENGTH_FOR_KARATSUBA) return a.multiply(b);
        n = n / 2 + (n % 2);

        BigInteger a1 = a.shiftRight(n);
        BigInteger a2 = a.subtract(a1.shiftLeft(n));
        BigInteger b1 = b.shiftRight(n);
        BigInteger b2 = b.subtract(b1.shiftLeft(n));

        BigInteger a1b1 = karatsubaMult(a1, b1);
        BigInteger a2b2 = karatsubaMult(a2, b2);
        BigInteger a1a2b1b2 = karatsubaMult(a1.add(a2), b1.add(b2));

        return a1b1.shiftLeft(n*2).add(a2b2).add(a1a2b1b2.subtract(a1b1).subtract(a2b2).shiftLeft(n));

    }

    public static void main(String[] args) {

        System.out.println(karatsubaMult(BigInteger.valueOf(2000100000), BigInteger.valueOf(25000000)));
    }
}
