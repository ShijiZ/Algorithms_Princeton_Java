/*
 * Sample usage: java-alg4 Heap < tiny.txt
 * Sample usage: java-alg4 Heap < words3.txt
 */

public class Heap {
    public static void sort(Comparable[] a){
        // heap construction
        int N = a.length;
        for (int k=N/2; k>=1; k--)
            sink(a, k, N);
        // sort down
        while (N > 1){
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    private static void sink(Comparable[] pq, int k, int N){
        while (2*k <= N){
            int j = 2*k;
            if (j < N && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;

        }
    }

    private static boolean less(Comparable[] pq, int i, int j){
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j){
        Object t = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = t;
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
            if (less(a, i+1, i))
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
