/*
 * Sample usage: java-alg4 LSD < words3.txt
 */

public class LSD {
    public static void sort(String[] a, int W){
        // Sort a[] on leading W characters.
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];

        for (int d = W-1; d >= 0; d--){
            // Sort by key-indexed counting on dth char.
            int[] count = new int[R+1];    // Compute frequency counts.
            for (int i=0; i<N; i++)
                count[a[i].charAt(d) + 1]++;

            for (int r=0; r<R; r++)        // Transform counts to indices.
                count[r+1] += count[r];

            for (int i=0; i<N; i++)        // Distribute.
                aux[count[a[i].charAt(d)]++] = a[i];

            for (int i=0; i<N; i++)        // Copy back.
                a[i] = aux[i];
        }
    }

    public static void main(String[] args){
        String[] a = StdIn.readAllStrings();
        int N = a.length;

        // check that strings have fixed length
        int w = a[0].length();
        for (int i=0; i<N; i++)
            assert a[i].length() == w : "Strings must have fixed length";

        // sort the strings
        sort(a, w);

        // print results
        for (int i=0; i<N; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
}
