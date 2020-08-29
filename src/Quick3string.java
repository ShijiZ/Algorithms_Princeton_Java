/*
* Sample usage: java Quick3string < words3.txt
* Sample usage: java Quick3string < shells.txt
*/

public class Quick3string {
    private static final int M = 15;   // cutoff for small subarrays

    private static void sort(String[] a, int lo, int hi, int d){
        if (hi <= lo+M){
            insertionSort(a, lo, hi, d);
            return;
        }

        // a[lo..lt-1] < v
        // a[gt+1..hi] > v
        // a[lt..i] == v
        // a[i+1..gt] not yet examined
        int lt = lo, i = lo+1, gt = hi;
        int v = charAt(a[lo], d);

        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) {
                exch(a, lt, i);
                lt++;
                i++;
            }
            else if (t > v) {
                exch(a, i, gt);
                gt--;
            }
            else {
                i++;
            }
        } // Now a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].

        sort(a, lo, lt-1, d);
        if (v >= 0) sort(a, lt, gt, d+1);
        sort(a, gt+1, hi, d);
    }

    public static void sort(String[] a){
        StdRandom.shuffle(a);   // Eliminate dependence on input.
        sort(a, 0, a.length-1, 0);
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
