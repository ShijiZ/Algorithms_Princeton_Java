/*
 * Sample usage: java-alg4 Selection < tiny.txt
 * Sample usage: java-alg4 Selection < words3.txt
 */

public class Selection {
    public static void sort(Comparable[] a){
        // Sort a[] into increasing order.
        int N = a.length;          // array length
        for (int i=0; i<N; i++){
            // Exchange a[i] with smallest entry in a[i+1...N).
            int min = i;           // index of minimal entry
            for (int j=i+1; j<N; j++){
                if (less(a[j],a[min])) min = j;
            }
            exch(a, i, min);
        }
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
