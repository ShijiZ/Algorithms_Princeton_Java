/*
* java Multiway data/m1.txt data/m2.txt data/m3.txt
*/

public class Multiway {
    public static void merge(In[] streams){
        int N = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<>(N);

        for (int i=0; i<N; i++)
            if (!streams[i].isEmpty())
                pq.insert(i, streams[i].readString());
        // Now pq contains the first key from each input file.

        while (!pq.isEmpty()){
            StdOut.println(pq.min());
            int i = pq.delMin(); // The key from the ith file is deleted.
            if (!streams[i].isEmpty())
                pq.insert(i, streams[i].readString());
        }
    }

    public static void main(String[] args){
        int N = args.length;
        In[] streams = new In[N];
        for (int i=0; i<N; i++)
            streams[i] = new In(args[i]);
        merge(streams);
    }
}
