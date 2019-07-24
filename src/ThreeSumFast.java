/*
 * Sample usage: java-alg4 ThreeSumFast 1Kints.txt
 * Sample usage: java-alg4 ThreeSumFast 2Kints.txt
 * Sample usage: java-alg4 ThreeSumFast 3Kints.txt
 * Sample usage: java-alg4 ThreeSumFast 4Kints.txt
 */

import java.util.Arrays;

public class ThreeSumFast {
    public static int count(int[] a){
        // Count triples that sum to 0.
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                if (BinarySearch.rank(-(a[i] + a[j]), a) > j)
                    cnt++;
        return cnt;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        StdOut.println(count(a));
    }
}
