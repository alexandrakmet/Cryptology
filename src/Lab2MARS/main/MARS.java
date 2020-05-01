package Lab2MARS.main;

public class MARS extends MARSAbstract {


    public MARS(byte[] key) {
        super(key);
    }

    int[] byteToInt(byte[] in) {
        int[] data = new int[in.length / 4];

        int off = 0;
        for (int i = 0; i < data.length; i++) {
            data[i] = (
                    (in[off++] & 0xff)) |
                    ((in[off++] & 0xff) << 8) |
                    ((in[off++] & 0xff) << 16) |
                    ((in[off++] & 0xff) << 24);
        }
        return data;
    }

    byte[] intToByte(int[] data) {
        byte[] tmp = new byte[data.length * 4];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = (byte) ((data[i / 4] >>> (i % 4) * 8) & 0xff);
        }
        return tmp;
    }

    @Override
    public byte[] decryptBlock(byte[] in) {
      //  byte[] tmp = new byte[in.length];
        int[] data = byteToInt(in);


        int A = data[0], B = data[1], C = data[2], D = data[3];
        int swap;

        //Backward mixing decode
        A += K[36];
        B += K[37];
        C += K[38];
        D += K[39];

        for (int i = 7; i >= 0; i--) {
            swap = D;
            D = C;
            C = B;
            B = A;
            A = rotateRight(swap, 13);

            D ^= s_box[rotateLeft(A, 24) & HEX_255];
            D += s_box[(rotateLeft(A, 16) & HEX_255) + 256];
            C += s_box[rotateLeft(A, 8) & HEX_255];
            B ^= s_box[(A & HEX_255) + 256];
            if (i == 3 || i == 7) A += B;
            if (i == 2 || i == 6) A += D;

        }

        // Cryptographic Core decode
        int L, M, R;
        for (int i = 15; i >= 0; i--) {
            swap = D;
            D = C;
            C = B;
            B = A;
            A = rotateRight(swap, 13);

            R = rotateLeft(rotateLeft(A, 13) * K[2 * i + 5], 5);
            M = rotateLeft(A + K[2 * i + 4], R & 0x1f);
            L = s_box[M & 0x000001ff] ^ R;
            R = rotateLeft(R, 5);
            L = rotateLeft(L ^ R, R & 0x1f);

            if (i < 8) D ^= R;
            else D -= L;
            C -= M;
            if (i < 8) B -= L;
            else B ^= R;

        }


        //Forward mixing decode
        for (int i = 7; i >= 0; i--) {
            swap = D;
            D = C;
            C = B;
            B = A;
            A = swap;

            if (i == 0 || i == 4) A -= D;
            if (i == 1 || i == 5) A -= B;


            A = rotateLeft(A, 24);

            D = D ^ s_box[(rotateRight(A, 24) & 0xff) + 256];
            C = C - s_box[rotateRight(A, 16) & 0xff];
            B = B - s_box[(rotateRight(A, 8) & 0xff) + 256];
            B = B ^ s_box[A & 0xff];

        }

        data[0] = A - K[0];
        data[1] = B - K[1];
        data[2] = C - K[2];
        data[3] = D - K[3];


        return intToByte(data);
    }

    @Override
    public byte[] encryptBlock(byte[] in) {
        int[] data = byteToInt(in);

        int A = data[0], B = data[1], C = data[2], D = data[3];
        int swap;
        A = A + K[0];
        B = B + K[1];
        C = C + K[2];
        D = D + K[3];

        //FORWARD MIXING
        for (int i = 0; i < 8; i++) {
            B ^= s_box[A & HEX_255];
            B += s_box[(rotateRight(A, 8) & HEX_255) + 256];
            C += s_box[rotateRight(A, 16) & 0xff];
            D ^= s_box[(rotateRight(A, 24) & 0xff) + 256];

            A = rotateRight(A, 24);

            if (i == 1 || i == 5) A += B;
            if (i == 0 || i == 4) A += D;

            swap = A;
            A = B;
            B = C;
            C = D;
            D = swap;
        }

        //CRYPTOGRAPHIC CORE
        int M, L, R;
        for (int i = 0; i < 16; i++) {

            R = rotateLeft(rotateLeft(A, 13) * K[2 * i + 5], 5);
            M = rotateLeft(A + K[2 * i + 4], R & 0x1f); //опечатка в документе
            L = s_box[M & 0x000001ff] ^ R;
            R = rotateLeft(R, 5);
            L = rotateLeft(L ^ R, R & 0x1f);

            if (i < 8) B += L;
            else B ^= R;
            C += M;
            if (i < 8) D ^= R;
            else D += L;

            swap = A;
            A = B;
            B = C;
            C = D;
            D = rotateLeft(swap, 13);
        }

        //BACKWARD MIXING
        for (int i = 0; i < 8; i++) {
            if (i == 3 || i == 7) A -= B;
            if (i == 2 || i == 6) A -= D;
            B ^= s_box[(A & HEX_255) + 256];
            C -= s_box[rotateLeft(A, 8) & HEX_255];
            D = (D - s_box[(rotateLeft(A, 16) & HEX_255) + 256]) ^ s_box[rotateLeft(A, 24) & HEX_255];
            swap = A;
            A = B;
            B = C;
            C = D;
            D = rotateLeft(swap, 13);
        }

        A -= K[36];
        B -= K[37];
        C -= K[38];
        D -= K[39];

        data[0] = A;
        data[1] = B;
        data[2] = C;
        data[3] = D;

        return intToByte(data);
    }


}
