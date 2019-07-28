/*
 * Sample usage: java-alg4 Quick3way < tiny.txt
 * Sample usage: java-alg4 Quick3way < words3.txt
 */

public class Quick3way {
    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int lt = lo, i = lo+1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt){
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }  // Now a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);   // Eliminate dependence on input.
        sort(a, 0, a.length-1);
    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a){
        // Print the array, on a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a){
        // Test whether the array entries are in order.
        for (int i=1; i<a.length; i++)
            if (less(a[i], a[i-1]))
                return false;
        return true;
    }

    public static void main(String[] args){
        // Read strings from standard input, sort them, and print.
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
