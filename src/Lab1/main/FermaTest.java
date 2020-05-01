package Lab1.main;

import java.math.BigInteger;
import java.util.Random;

public class FermaTest {

    public static boolean isProbablyPrime(BigInteger x){
        if(x.compareTo(BigInteger.ONE) == 0) return false;
        if(x.compareTo(BigInteger.TWO) == 0) return true;

        for(int i=0;i<100;i++){
            BigInteger a = new BigInteger(x.bitLength(), new Random());
            a = a.mod(x.subtract(BigInteger.TWO)).add(BigInteger.TWO);
            if (!gcd(a, x).equals(BigInteger.ONE))
                return false;
            if(!a.modPow(x.subtract(BigInteger.ONE), x).equals(BigInteger.ONE))
                return false;
        }
        return true;
    }

    private static BigInteger gcd(BigInteger a, BigInteger b){
        if(b.equals(BigInteger.ZERO))
            return a;
        return gcd(b, a.mod(b));
    }


    public static void main(String[] args) {
        System.out.println(isProbablyPrime(BigInteger.valueOf(2L)));
        System.out.println(isProbablyPrime(BigInteger.valueOf(3L)));
        System.out.println(isProbablyPrime(BigInteger.valueOf(8L)));
        System.out.println(isProbablyPrime(BigInteger.valueOf(17L)));
        System.out.println(isProbablyPrime(BigInteger.valueOf(256L)));
    }
}
