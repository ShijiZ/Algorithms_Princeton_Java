/*
 * Sample usage: java LSD < words3.txt
 */

public class LSD {
    public static void sort(String[] a, int W){
        // Sort a[] on leading W characters.
        int N = a.length;
        int R = 256; // Extended ASCII alphabet size
        String[] aux = new String[N];

        // Sort by key-indexed counting on dth char.
        for (int d = W-1; d >= 0; d--){
            // Compute frequency counts.
            int[] count = new int[R+1];
            for (int i=0; i<N; i++){
                int r = a[i].charAt(d) + 1;
                count[r]++;
            }

            // Transform counts to indices.
            for (int r=0; r<R; r++)
                count[r+1] += count[r];

            // Distribute.
            for (int i=0; i<N; i++){
                int r = a[i].charAt(d);
                aux[count[r]] = a[i];
                count[r]++;
            }

            // Copy back.
            for (int i=0; i<N; i++)
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
