/*
 * java LZW - < ababLZW.txt | java LZW +
 */

public class LZW {
    private static final int R = 256;   // number of input chars = 2^8
    private static final int L = 4096;  // number of codewords = 2^12
    private static final int W = 12;    // codeword width

    public static void compress() {
        String input = BinaryStdIn.readString();
        TrieST<Integer> st = new TrieST<>();

        for (int i = 0;i < R; i++)
            st.put("" + (char) i, i);

        int code = R + 1;  // R is codeword for EOF.
        while (input.length() > 0) {
            // Find max prefix match.
            String s = st.longestPrefixOf(input);

            // Print s's encoding.
            BinaryStdOut.write(st.get(s), W);

            // Add s to symbol table.
            int t = s.length();
            if (t < input.length() && code < L) {
                st.put(input.substring(0, t + 1), code);
                code++;
            }

            // Scan past s in input.
            input = input.substring(t);
        }

        BinaryStdOut.write(R, W);  // Write EOF
        BinaryStdOut.close();
    }

    public static void expand() {
        String[] st = new String[L];

        int i;   // next available codeword value
        // Initialize table for chars.
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i] = ""; // (unused) lookahead for EOF
        i++;

        int codeword = BinaryStdIn.readInt(W);
        String val = st[codeword];
        while (true) {
            // Write current substring.
            BinaryStdOut.write(val);

            // Get next codeword.
            codeword = BinaryStdIn.readInt(W);
            if (codeword == R) break;

            String s;
            // If lookahead is invalid, make substring from the last one.
            if (i == codeword)
                s = val + val.charAt(0);
            // If lookahead is valid, get substring from symbol table.
            else
                s = st[codeword];

            // Add new entry to symbol table. Update current codeword.
            if (i < L) {
                st[i] = val + s.charAt(0);
                i++;
            }
            val = s;
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) compress();
        if (args[0].equals("+")) expand();
    }
}
