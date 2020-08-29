/*
 * Sample usage: java MSD < words3.txt
 * Sample usage: java MSD < shells.txt
 */

public class MSD {
    private static int R = 256;        // radix
    private static final int M = 15;   // cutoff for small subarrays
    private static String[] aux;       // auxiliary array for distribution

    private static void sort(String[] a, int lo, int hi, int d){
        // Sort from a[lo] to a[hi], starting at the dth character.
        if (hi <= lo+M){
            insertionSort(a, lo, hi, d);
            return;
        }

        // Compute frequency counts.
        int[] count = new int[R+2];
        for (int i=lo; i<=hi; i++) {
            int r = charAt(a[i], d) + 2;
            count[r]++;
        }

        // Transform counts to indices.
        for (int r=0; r<R+1; r++)
            count[r+1] += count[r];

        // Distribute.
        for (int i=lo; i<=hi; i++) {
            int r = charAt(a[i], d)+1;
            aux[count[r]] = a[i];
            count[r]++;
        }

        // Copy back.
        for (int i=lo; i<=hi; i++)
            a[i] = aux[i - lo];

        // Recursively sort for each character value.
        for (int r=0; r<R; r++)
            sort(a, lo+count[r], lo+count[r+1]-1, d+1);
    }

    public static void sort(String[] a){
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N-1, 0);
    }

    // return dth character of s, -1 if d = length of string
    private static int charAt(String s, int d){
        if (d<s.length())
            return s.charAt(d);
        else
            return -1;
    }

    private static void insertionSort(String[] a, int lo, int hi, int d){
        // Sort from a[lo] to a[hi], starting at the dth character.
        for (int i=lo; i<=hi; i++)
            for (int j=i; j>lo && less(a[j], a[j-1], d); j--)
                exch(a, j, j-1);
    }

    private static boolean less(String v, String w, int d){
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    private static void exch(String[] a, int i, int j){
        String t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        int n = a.length;
        sort(a);
        for (int i = 0; i < n; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
}
