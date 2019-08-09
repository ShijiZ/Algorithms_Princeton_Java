/*
 * Sample usage: java-alg4 KruskalMST tinyEWG.txt
 * Sample usage: java-alg4 KruskalMST mediumEWG.txt
 */

public class KruskalMST {
    private LinkedQueue<Edge> mst;
    private double weight;

    public KruskalMST(EdgeWeightedGraph G){
        mst = new LinkedQueue<>();
        MinPQ<Edge> pq = new MinPQ<Edge>(G.E());   // Note: The MinPQ on booksite is resizable
        for (Edge e : G.edges())
            pq.insert(e);
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(G.V());

        while (!pq.isEmpty() && mst.size()<G.V()-1){
            // Get min weight edge on pq and its vertices
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            // Ignore ineligible edges
            if (uf.connected(v, w)) continue;
            // Merge components
            uf.union(v, w);
            // Add edge to mst
            mst.enqueue(e);
            weight += e.weight();
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        return weight;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        KruskalMST mst = new KruskalMST(G);

        for (Edge e: mst.edges())
            StdOut.println(e);
        StdOut.printf("%.5f\n", mst.weight());
    }
}
