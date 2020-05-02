package Lab2MARS.main;

public class Main {

    public static void main(String[] args) {
        String key = "80000000000000000000000000000000";
        String in = "00000000000000000000000000000000";
        byte[] keyBytes = HexByteConverter.hexToByte(key);
        byte[] inBytes = HexByteConverter.hexToByte(in);

        MARS mars = new MARS(keyBytes);

        byte[] encodedBytes = mars.encryptBlock(inBytes);
        String encodedString = HexByteConverter.bytesToHex(encodedBytes);

        byte[] decodedBytes = mars.decryptBlock(encodedBytes);
        String decodedString = HexByteConverter.bytesToHex(decodedBytes);

        System.out.println("Initial string: " + in);
        System.out.println("Encoded string: " + encodedString);
        System.out.println("Decoded string: " + decodedString);

    }
}
