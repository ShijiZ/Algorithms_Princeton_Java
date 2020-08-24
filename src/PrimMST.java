/*
 * Sample usage: java PrimMST tinyEWG.txt
 * Sample usage: java PrimMST mediumEWG.txt
 */

public class PrimMST {
    private boolean[] marked;       // MST vertices
    private double[] distTo;        // distTo[w] = edgeTo[w].weight()
    private Edge[] edgeTo;          // Shortest from tree vertex
    private IndexMinPQ<Double> pq;  // eligible crossing edges

    public PrimMST(EdgeWeightedGraph G){
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        for (int v=0; v<G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        pq = new IndexMinPQ<>(G.V());
        marked = new boolean[G.V()];

        distTo[0] = 0.0;
        pq.insert(0, 0.0);  // Initialize pq with 0, weight 0
        while (!pq.isEmpty())
            visit(G, pq.delMin());  // add closest vertex to tree
    }

    private void visit(EdgeWeightedGraph G, int v){
        // add v to tree; update data structures
        marked[v] = true;
        for (Edge e:G.adj(v)){
            int w = e.other(v);
            if (marked[w]) continue;   // v-w is ineligible
            if (e.weight() < distTo[w]){
                // edge e is new best connection from tree to w
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges(){
        LinkedQueue<Edge> mst = new LinkedQueue<>();
        for (int v=0; v<edgeTo.length; v++){
            Edge e = edgeTo[v];
            if (e != null)
                mst.enqueue(e);
        }
        return mst;
    }

    public double weight(){
        double weight = 0.0;
        for (Edge e : edges())
            weight += e.weight();
        return weight;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        PrimMST mst = new PrimMST(G);

        StdOut.printf("Edges in MST:\n");
        for (Edge e: mst.edges())
            StdOut.println(e);
        StdOut.printf("Total weight of MST: %.5f\n", mst.weight());
    }
}
