package Lab3MD5.test;

import Lab3MD5.MD5;
import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MD5Test {

    @Test
    public void testHashGeneral() throws NoSuchAlgorithmException {
        final int NUMBER_OF_TEST = 1000;
        final int MAX_INPUT_LEN = 1000;
        MessageDigest digest = MessageDigest.getInstance("MD5");
        Random rand = new Random();

        for(int i = 0; i < NUMBER_OF_TEST; ++i) {
            byte[] input = new byte[rand.nextInt(MAX_INPUT_LEN)];
            rand.nextBytes(input);

            assertArrayEquals(MD5.computeMD5(input), digest.digest(input));
        }
    }
}