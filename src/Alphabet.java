/*
 * Sample usage: java Alphabet
 */

public class Alphabet {
    private char[] alphabet;     // the characters in the alphabet
    private int[] inverse;       // indices
    private final int R;         // the radix of the alphabet

    public Alphabet(String s) {
        alphabet = s.toCharArray();
        R = s.length();
        inverse = new int[Character.MAX_VALUE];
        for (int i = 0; i < inverse.length; i++)
            inverse[i] = -1;

        for (int c = 0; c < R; c++)
            inverse[alphabet[c]] = c;
    }

    private Alphabet(int radix) {
        this.R = radix;
        alphabet = new char[R];
        inverse = new int[R];

        for (int i = 0; i < R; i++)
            alphabet[i] = (char) i;
        for (int i = 0; i < R; i++)
            inverse[i] = i;
    }

    // Returns the character corresponding to the argument index.
    public char toChar(int index) {
        return alphabet[index];
    }

    // Returns the index corresponding to the argument character.
    public int toIndex(char c) {
        return inverse[c];
    }

    // Returns true if the argument is a character in this alphabet.
    public boolean contains(char c) {
        return inverse[c] != -1;
    }

    // Returns the number of characters in this alphabet (the radix).
    public int R() {
        return R;
    }

    // Returns the binary logarithm of the number of characters in this alphabet.
    public int lgR() {
        int lgR = 0;
        for (int t = R-1; t >= 1; t /= 2)
            lgR++;
        return lgR;
    }

    // Returns the indices corresponding to the argument characters.
    public int[] toIndices(String s) {
        char[] source = s.toCharArray();
        int[] target = new int[s.length()];
        for (int i = 0; i < source.length; i++)
            target[i] = toIndex(source[i]);
        return target;
    }

    // Returns the characters corresponding to the argument indices.
    public String toChars(int[] indices) {
        StringBuilder s = new StringBuilder(indices.length);
        for (int i = 0; i < indices.length; i++)
            s.append(toChar(indices[i]));
        return s.toString();
    }

    public static final Alphabet BINARY = new Alphabet("01");
    public static final Alphabet OCTAL = new Alphabet("01234567");
    public static final Alphabet DECIMAL = new Alphabet("0123456789");
    public static final Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");
    public static final Alphabet DNA = new Alphabet("ACGT");
    public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");
    public static final Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    public static final Alphabet PROTEIN = new Alphabet("ACDEFGHIKLMNPQRSTVWY");
    public static final Alphabet BASE64 = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
    public static final Alphabet ASCII = new Alphabet(128);
    public static final Alphabet EXTENDED_ASCII = new Alphabet(256);
    public static final Alphabet UNICODE16 = new Alphabet(65536);

    public static void main(String[] args) {
        int[] encoded1 = BASE64.toIndices("NowIsTheTimeForAllGoodMen");
        String decoded1 = BASE64.toChars(encoded1);
        StdOut.println(decoded1);

        int[] encoded2 = DNA.toIndices("AACGAACGGTTTACCCCG");
        String decoded2 = DNA.toChars(encoded2);
        StdOut.println(decoded2);

        int[] encoded3 = DECIMAL.toIndices("01234567890123456789");
        String decoded3 = DECIMAL.toChars(encoded3);
        StdOut.println(decoded3);
    }
}
