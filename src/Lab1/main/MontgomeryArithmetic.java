package Lab1.main;

import java.math.BigInteger;

public class MontgomeryArithmetic {


    public static BigInteger monPro(BigInteger a, BigInteger b, BigInteger n) {
        BigInteger r = BigInteger.TWO.shiftLeft(n.bitLength());
        BigInteger[] euclid = ExtendedEuclid.gcd(n, r);

        BigInteger t = a.multiply(b);
        BigInteger m = t.multiply(euclid[0].negate()).and(r.subtract(BigInteger.ONE));
        BigInteger u = t.add(m.multiply(n)).shiftRight(n.bitLength());
        if (u.compareTo(n) >= 0) return u.subtract(n);
        else return u;
    }

    public static BigInteger modMul(BigInteger a, BigInteger b, BigInteger n) {
        BigInteger a1 = a.shiftLeft(n.bitLength()).mod(n);
        BigInteger b1 = b.shiftLeft(n.bitLength()).mod(n);
        BigInteger u1 = monPro(a1, b1, n);
        return monPro(u1, BigInteger.ONE, n);
    }

    public static BigInteger modExp(BigInteger m, BigInteger e, BigInteger n) {
        BigInteger m1 = m.shiftLeft(n.bitLength()).mod(n);
        BigInteger x1 = BigInteger.ONE.shiftLeft(n.bitLength()).mod(n);
        for (int i = e.bitLength() - 1; i >= 0; i--) {
            x1 = monPro(x1, x1, n);
            if (e.testBit(i)) x1 = monPro(m1, x1, n);
        }

        return monPro(x1, BigInteger.ONE, n);
    }

    public static void main(String[] args) {
        System.out.println(modMul(BigInteger.valueOf(11), BigInteger.valueOf(3), BigInteger.valueOf(15)));
        System.out.println(BigInteger.valueOf(11).multiply(BigInteger.valueOf(3)).mod(BigInteger.valueOf(15)));


        System.out.println(modExp(BigInteger.valueOf(345), BigInteger.valueOf(30), BigInteger.valueOf(19)));
        System.out.println(BigInteger.valueOf(345).modPow(BigInteger.valueOf(30), BigInteger.valueOf(19)));
    }
}
