package Lab1.test;

import Lab1.main.KaratsubaMultiplication;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class KaratsubaMultiplicationTest {

    @Test
    void karatsubaMult() {

        BigInteger a, b;
        Random rand = new Random();
        int iter = 100;

        for (int i = 0; i < iter; ++i) {

            a = BigInteger.valueOf(Math.abs(rand.nextLong()));
            b = BigInteger.valueOf(Math.abs(rand.nextLong()));

            assertEquals(a.multiply(b), KaratsubaMultiplication.karatsubaMult(a, b));
        }
    }
}