/*
 * Sample usage: java BoyerMoore DABRA AABRAACADABRAACAADABRA
 */

public class BoyerMoore {
    private String pat;
    private int[] right;

    public BoyerMoore(String pat){
        // Compute skip table
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        right = new int[R];
        for (int c=0; c<R; c++)
            right[c] = -1;                   // -1 for chars not in pattern
        for (int j=0; j<M; j++)              // Right most position for chars in pattern
            right[pat.charAt(j)] = j;

    }

    public int search(String txt){
        // Search for pattern in txt.
        int N = txt.length(), M = pat.length();
        int skip;
        for (int i=0; i<N-M; i += skip){
            // Does the pattern match the text at position i?
            skip = 0;
            for (int j=M-1; j>=0; j--)
                if (pat.charAt(j) != txt.charAt(i+j)){
                    skip = j - right[txt.charAt(i+j)];
                    if (skip < 1) skip = 1;
                    break;
                }
            if (skip == 0) return i;    // found
        }
        return N;                       // not found
    }

    public static void main(String[] args){
        String pat = args[0];
        String txt = args[1];
        BoyerMoore bm = new BoyerMoore(pat);
        StdOut.println("text:    " + txt);
        int offset = bm.search(txt);
        StdOut.print("pattern: ");
        for (int i = 0; i<offset; i++)
            StdOut.print(" ");
        StdOut.println(pat);
    }
}
