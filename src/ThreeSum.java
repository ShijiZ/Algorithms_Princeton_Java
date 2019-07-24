/*
 * Sample usage: java-alg4 ThreeSum 1Kints.txt
 * Sample usage: java-alg4 ThreeSum 2Kints.txt
 * Sample usage: java-alg4 ThreeSum 3Kints.txt
 * Sample usage: java-alg4 ThreeSum 4Kints.txt
 */

public class ThreeSum {
    public static int count(int[] a){
        // Count triples that sum to 0
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                for (int k = j+1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0)
                        cnt++;
        return cnt;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        StdOut.println(count(a));
    }
}
