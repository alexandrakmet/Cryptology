package Lab2MARS.test;

import static org.junit.jupiter.api.Assertions.*;

import Lab2MARS.main.HexByteConverter;
import Lab2MARS.main.MARS;
import org.junit.jupiter.api.Test;

import java.util.Random;

class MARSTest {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private Random random;

    MARSTest() {
        random = new Random();
    }

    private String generateKey() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++)
            sb.append(HEX_ARRAY[random.nextInt(HEX_ARRAY.length)]);
        return sb.toString();
    }

    @Test
    void encryptBlock() {
        String key = generateKey();
        String in = generateKey();
        byte[] keyBytes = HexByteConverter.hexToByte(key);
        byte[] inBytes = HexByteConverter.hexToByte(in);

        MARS mars = new MARS(keyBytes);

        byte[] encodedBytes = mars.encryptBlock(inBytes);
        String encodedString = HexByteConverter.bytesToHex(encodedBytes);

        byte[] decodedBytes = mars.decryptBlock(encodedBytes);
        String decodedString = HexByteConverter.bytesToHex(decodedBytes);
        assertEquals(in, decodedString);

    }
}