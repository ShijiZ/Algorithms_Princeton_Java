/*
 * Sample usage: java RabinKarp DABRA AABRAACADABRAACAADABRA
 */

import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
    private long patHash;       // pattern hash value
    private int M;              // pattern length
    private long Q;             // a large prime
    private int R = 256;        // alphabet size
    private long RM;            // R^(M-1) % Q

    public RabinKarp(String pat){
        this.M = pat.length();
        Q = longRandomPrime();
        RM = 1;
        // Compute R^(M-1) % Q for use in removing leading digits
        for (int i=1; i<=M-1; i++)
            RM = (R*RM)%Q;
        patHash = hash(pat, M);
    }

    private long hash(String key, int M){
        // Compute hash for key[0..M-1]
        long h = 0;
        for (int j=0; j<M; j++)
            h = (R*h + key.charAt(j)) % Q;
        return h;
    }

    // Note: This code modified based on https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/
    public int search(String txt){
        // Search for hash match in txt.
        int N = txt.length();
        long txtHash = hash(txt, M);
        if (patHash == txtHash) return 0;      // Match at beginning
        for (int i = M; i<N; i++){
            // Remove leading digit
            txtHash = txtHash - RM*txt.charAt(i-M);
            // Add trailing digit
            txtHash = (txtHash*R + txt.charAt(i)) % Q;
            // Convert txtHash to positive if get negative value
            if (txtHash < 0) txtHash += Q;
            // Check for match
            if (patHash == txtHash) return i-M+1; // match
        }
        return N;                                 // no match found
    }

    private static long longRandomPrime(){
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    public static void main(String[] args){
        String pat = args[0];
        String txt = args[1];
        RabinKarp rk = new RabinKarp(pat);
        StdOut.println("text:    " + txt);
        int offset = rk.search(txt);
        StdOut.print("pattern: ");
        for (int i = 0; i<offset; i++)
            StdOut.print(" ");
        StdOut.println(pat);
    }
}
