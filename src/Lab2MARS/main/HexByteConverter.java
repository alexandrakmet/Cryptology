package Lab2MARS.main;

import java.math.BigInteger;

public class HexByteConverter {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static byte[] hexToByte(String hexString) {
        byte[] keyByte = new byte[hexString.length() / 2];
        String keyBinary = new BigInteger(hexString, 16).toString(2);

        int k = 0;
        if (keyBinary.length() < keyByte.length * 8) {
            StringBuffer tmp = new StringBuffer();
            for (int i = 0; i < keyByte.length * 8 - keyBinary.length(); i++) {
                tmp.append("0");
            }
            tmp.append(keyBinary);
            keyBinary = tmp.toString();
        }
        for (int i = 0; i < keyBinary.length(); i += 8) {
            StringBuffer tmp = new StringBuffer();
            for (int j = i; j < i + 8; j++) {
                tmp.append(keyBinary.charAt(j));
            }
            keyByte[k] = (byte) Integer.parseInt(tmp.toString(), 2);
            k++;
        }

        return keyByte;
    }
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
