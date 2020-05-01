package Lab1.test;
import Lab1.main.MontgomeryArithmetic;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class MontgomeryArithmeticTest {
    @Test
    void modMul() {
        BigInteger a, b, n;

        a = BigInteger.valueOf(123L);
        b = BigInteger.valueOf(567L);
        n = BigInteger.valueOf(43L);
        assertEquals(a.multiply(b).mod(n), MontgomeryArithmetic.modMul(a,b,n));

        a = BigInteger.valueOf(1400L);
        b = BigInteger.valueOf(200L);
        n = BigInteger.valueOf(5L);
        assertEquals(a.multiply(b).mod(n), MontgomeryArithmetic.modMul(a,b,n));
    }

    @Test
    void modExp() {
        BigInteger a, b, n;

        a = BigInteger.valueOf(12L);
        b = BigInteger.valueOf(5L);
        n = BigInteger.valueOf(43L);
        assertEquals(a.modPow(b, n), MontgomeryArithmetic.modExp(a,b,n));

        a = BigInteger.valueOf(1400L);
        b = BigInteger.valueOf(20L);
        n = BigInteger.valueOf(5L);
        assertEquals(a.modPow(b, n), MontgomeryArithmetic.modExp(a,b,n));
    }

}