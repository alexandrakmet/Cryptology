package Lab1.main;

import java.math.BigInteger;

public class BinaryPower {

    public static BigInteger binPower(BigInteger a, int n) {
        BigInteger res = BigInteger.ONE;
        while (n != 0) {
            if (n % 2 == 1)
                res = res.multiply(a);
            a = a.multiply(a);
            n >>= 1;
        }
        return res;
    }
}
