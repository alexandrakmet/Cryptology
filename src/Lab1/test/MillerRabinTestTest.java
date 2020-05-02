package Lab1.test;

import Lab1.main.MillerRabinTest;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class MillerRabinTestTest {

    @Test
    void isProbablyPrime() {

        int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
                43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
                101, 103, 107, 109, 113, 127, 131, 137, 139, 149,
                151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199,
                271, 331, 397, 547, 631, 919, 1657, 1801, 1951, 2269, 2437,
                2791, 3169, 3571, 4219, 4447, 5167, 5419, 6211, 7057, 7351,
                8269, 9241, 10267, 11719, 12097, 13267, 13669, 16651, 19441, 19927, 22447, 23497};

        int[] notPrimes = {4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22,
                24, 25, 26, 27, 28, 30, 32, 33, 34, 35, 36, 38,
                39, 40, 42, 44, 45, 46, 48, 49, 50, 51, 52, 54};

        for (int k = 3; k < 10; ++k) {

            for (int i = 0; i < primes.length; ++i) {
                assertTrue(MillerRabinTest.isProbablyPrime(BigInteger.valueOf(primes[i]),k));
            }

            int misses = 0;

            for (int i = 0; i < notPrimes.length; ++i) {
                if (MillerRabinTest.isProbablyPrime(BigInteger.valueOf(notPrimes[i]),k))
                    misses++;
            }

            if ((double)(misses/notPrimes.length) > Math.pow(0.25, k)) fail();
        }
    }
}