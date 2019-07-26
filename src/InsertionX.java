/*
 * Sample usage: java-alg4 InsertionX < tiny.txt
 * Sample usage: java-alg4 InsertionX < words3.txt
 */

public class InsertionX {
    public static void sort(Comparable[] a){
        // Sort a[] into increasing order.
        int N = a.length;          // array length

        // put smallest element in position to serve as sentinel
        int exchanges = 0;
        for (int i=N-1; i>0 ; i--){
            if (less(a[i],a[i-1])) {
                exch(a, i, i - 1);
                exchanges++;
            }
        }
        if (exchanges==0) return;

        // insertion sort with half-exchanges
        for (int i=2; i<N; i++){
            Comparable v = a[i];
            int j=i;
            while (less(v,a[j-1])){
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
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
