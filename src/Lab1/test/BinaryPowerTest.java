package Lab1.test;

import Lab1.main.BinaryPower;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BinaryPowerTest {

    @Test
    void binPower() {

        BigInteger a;
        int b;
        int bound = 20;
        Random rand = new Random();
        int iter = 100;

        for (int i = 0; i < iter; ++i) {
            a = BigInteger.valueOf(Math.abs(rand.nextInt()));
            b = Math.abs(rand.nextInt() % bound);
            assertEquals(a.pow(b), BinaryPower.binPower(a, b));
        }
    }
}