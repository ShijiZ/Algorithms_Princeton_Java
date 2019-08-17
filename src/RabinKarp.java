/*
 * Sample usage: java-alg4 RabinKarp DABRA AABRAACADABRAACAADABRA
 */

import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
    private String pat;         // Pattern (only needed for Las Vegas)
    private long patHash;       // pattern hash value
    private int M;              // pattern length
    private long Q;             // a large prime
    private int R = 256;        // alphabet size
    private long RM;            // R^(M-1) % Q

    public RabinKarp(String pat){
        this.pat = pat;                      // save pattern (only needed for Las Vegas)
        this.M = pat.length();
        Q = longRandomPrime();
        RM = 1;
        // Compute R^(M-1) % Q for use in removing leading digits
        for (int i=1; i<=M-1; i++)
            RM = (R*RM)%Q;
        patHash = hash(pat, M);
    }

    public boolean check(int i){
        return true;
    }

    private long hash(String key, int M){
        // Compute hash for key[0..M-1]
        long h = 0;
        for (int j=0; j<M; j++)
            h = (R*h + key.charAt(j)) % Q;
        return h;
    }

    public int search(String txt){
        // Search for hash match in txt.
        int N = txt.length();
        long txtHash = hash(txt, M);
        if (patHash == txtHash) return 0;      // Match at beginning
        for (int i = M; i<N; i++){
            // Remove leading digit, add trailing digit, check for match
            txtHash = (txtHash + Q - RM*txt.charAt(i-M)%Q)%Q;
            txtHash = (txtHash*R + txt.charAt(i)) % Q;
            if (patHash == txtHash)
                if (check(i-M+1))
                    return i-M+1;              // match
        }
        return N;                              // no match found
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
