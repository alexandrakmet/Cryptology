package Lab1.test;

import Lab1.main.ExtendedEuclid;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class ExtendedEuclidTest {

    @Test
    void gcd() {
        BigInteger a, b;
        BigInteger[] res;

        a = BigInteger.valueOf(150L);
        b = BigInteger.valueOf(180L);
        res = ExtendedEuclid.gcd(a, b);
        assertEquals(BigInteger.valueOf(-1), res[0]);
        assertEquals(BigInteger.valueOf(1), res[1]);
        assertEquals(BigInteger.valueOf(30), res[2]);

        a = BigInteger.valueOf(12345678L);
        b = BigInteger.valueOf(87654321L);
        res = ExtendedEuclid.gcd(a, b);
        assertEquals(BigInteger.valueOf(-1217430), res[0]);
        assertEquals(BigInteger.valueOf(171469), res[1]);
        assertEquals(BigInteger.valueOf(9), res[2]);

        a = BigInteger.valueOf(7777);
        b = BigInteger.valueOf(5555);
        res = ExtendedEuclid.gcd(a, b);
        assertEquals(BigInteger.valueOf(-2), res[0]);
        assertEquals(BigInteger.valueOf(3), res[1]);
        assertEquals(BigInteger.valueOf(1111), res[2]);
    }

}