/*
 * Sample usage: java MinPQ < tinyPQ.txt
 */

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;     // heap-ordered complete binary tree
    private int N = 0;    // in pq[1..N] with pq[0] unused

    public MinPQ(int maxN){
        pq = (Key[]) new Comparable[maxN+1];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }


    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
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

    public void insert(Key v){
        N++;
        pq[N] = v;
        swim(N);
    }

    public Key delMin(){
        Key min = pq[1];
        exch(1, N);
        N--;
        pq[N+1] = null;
        sink(1);
        return min;
    }

    public static void main(String[] args) {
        MinPQ<String> pq = new MinPQ<String>(20);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMin() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }
}
