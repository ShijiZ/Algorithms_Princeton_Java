/*
 * Sample usage: java-alg4 IndexMinPQ
 */

public class IndexMinPQ<Key extends Comparable<Key>> {
    private int maxN;    // maximum number of elements on PQ
    private int N;       // number of elements on PQ
    private int[] pq;    // binary heap using 1-based indexing
    private int[] qp;    // inverse of pq: qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;  // keys[i] = priority of i

    public IndexMinPQ(int maxN){
        this.maxN = maxN;
        N = 0;
        keys = (Key[]) new Comparable[maxN+1];
        pq = new int[maxN+1];
        qp = new int[maxN+1];
        for (int i=0; i <= maxN; i++)
            qp[i] = -1;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public boolean contains(int i){
        return qp[i] != -1;
    }

    public int size(){
        return N;
    }

    private boolean less(int i, int j){
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exch(int i, int j){
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k){
        while (k > 1 && less(k, k/2)){
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k){
        while (2*k <= N){
            int j = 2*k;
            if (j < N && less(j+1, j)) j++;
            if (!less(j, k)) break;
            exch(k, j);
            k = j;
        }
    }

    public void insert(int i, Key key){
        N++;
        qp[i] = N;
        pq[N] = i;
        keys[i] = key;
        swim(N);
    }

    public int delMin(){
        int min = pq[1];
        exch(1, N--);
        sink(1);
        qp[min] = -1;
        keys[min] = null;
        pq[N+1] = -1;
        return min;
    }

    // Change the key associated with index i to the specified value.
    public void change(int i, Key key){
        keys[i] = key;
        swim(pq[i]);
        sink(pq[i]);
    }

    // Returns the minimum key.
    public Key min(){
        return keys[pq[1]];
    }

    public static void main(String[] args) {
        // insert a bunch of strings
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        IndexMinPQ<String> pq = new IndexMinPQ<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // delete and print each key
        while (!pq.isEmpty()) {
            int i = pq.delMin();
            StdOut.println(i + " " + strings[i]);
        }
        StdOut.println();
    }

}
