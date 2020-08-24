/*
 * Sample usage: java LazyPrimMST tinyEWG.txt
 * Sample usage: java LazyPrimMST mediumEWG.txt
 */

public class LazyPrimMST {
    private boolean[] marked;       // MST vertices
    private LinkedQueue<Edge> mst;  // MST edges
    private MinPQ<Edge> pq;         // crossing (and ineligible) edges
    private double weight;          // edges with one endpoint in tree

    public LazyPrimMST(EdgeWeightedGraph G){
        pq = new MinPQ<>(1000);  // Note: The MinPQ on booksite is resizable
        mst = new LinkedQueue<>();
        marked = new boolean[G.V()];

        visit(G, 0);  // Assume G is connected
        while (!pq.isEmpty()){
            // Get lowest-weight edge from pq
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            // Skip if ineligible
            if (marked[v] && marked[w]) continue;
            // Add edge to tree
            mst.enqueue(e);
            weight += e.weight();
            // Add vertex to tree
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v){
        // Mark v and add to pq all edges from v to unmarked vertices
        marked[v] = true;
        for (Edge e:G.adj(v))
            if (!marked[e.other(v)])
                pq.insert(e);
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
        LazyPrimMST mst = new LazyPrimMST(G);

        StdOut.printf("Edges in MST:\n");
        for (Edge e: mst.edges())
            StdOut.println(e);
        StdOut.printf("Total weight of MST: %.5f\n", mst.weight());
    }
}
