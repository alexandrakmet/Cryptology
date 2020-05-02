package Lab1.main;

import java.math.BigInteger;
import java.util.Random;

public class MillerRabinTest {

    public static boolean isProbablyPrime(BigInteger n, int k) {
        if (n.compareTo(BigInteger.ONE) == 0) return false;
        if (n.compareTo(BigInteger.TWO) == 0) return true;

        BigInteger s = BigInteger.ZERO, d = n.subtract(BigInteger.ONE);
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            s = s.add(BigInteger.ONE);
            d = d.divide(BigInteger.TWO);
        }

        BigInteger a, x;
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            a = new BigInteger(n.bitLength(), random);
            a = a.mod(n.subtract(BigInteger.TWO)).add(BigInteger.TWO);
            x = a.modPow(d, n);

            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) continue;
            int j = 0;
            for (; s.compareTo(BigInteger.valueOf(j)) > 0; j++) {
                x = x.modPow(BigInteger.TWO, n);
                if (x.equals(BigInteger.ONE)) return false;
                if (x.equals(n.subtract(BigInteger.ONE))) break;
            }
            if (s.equals(BigInteger.valueOf(j))) return false;

        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isProbablyPrime(BigInteger.valueOf(2L), 5));
        System.out.println(isProbablyPrime(BigInteger.valueOf(3L), 10));
        System.out.println(isProbablyPrime(BigInteger.valueOf(8L), 12));
        System.out.println(isProbablyPrime(BigInteger.valueOf(17L), 10));
        System.out.println(isProbablyPrime(BigInteger.valueOf(256L), 10));
    }
}
