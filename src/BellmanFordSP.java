/*
 * Sample usage: java-alg4 BellmanFordSP tinyEWDn.txt 0
 * Sample usage: java-alg4 BellmanFordSP tinyEWDnc.txt 0
 */

public class BellmanFordSP {
    private DirectedEdge[] edgeTo;           // last edge on path to v
    private double[] distTo;                 // length of path to v
    private boolean[] onQ;                   // Is this vertex on the queue?
    private LinkedQueue<Integer> queue;      // vertices being relaxed
    private int cost;                        // number of calls to relax()
    private Iterable<DirectedEdge> cycle;    // negative cycle in edgeTo[]?

    public BellmanFordSP(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        onQ = new boolean[G.V()];
        queue = new LinkedQueue<>();

        for (int v=0; v<G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0;

        queue.enqueue(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !this.hasNegativeCycle()){
            int v = queue.dequeue();
            onQ[v] = false;
            relax(G, v);
        }
    }

    // relax vertex v (i.e. relax each edge e from v)
    private void relax(EdgeWeightedDigraph G, int v){
        for (DirectedEdge e : G.adj(v)){
            relax(e);

            cost++;
            if (cost % G.V()==0)
                findNegativeCycle();
        }
    }

    // relax edge e and put other endpoints on queue if changed
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()){
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (!onQ[w]){
                queue.enqueue(w);
                onQ[w] = true;
            }
        }
    }

    private void findNegativeCycle(){
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for (int v=0; v<V; v++)
            if (edgeTo[v] != null)
                // spt only contains edges belong to the negative cycle
                spt.addEdge(edgeTo[v]);

        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();
    }

    public boolean hasNegativeCycle(){
        return cycle != null;
    }

    public Iterable<DirectedEdge> negativeCycle(){
        return cycle;
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        if (!hasPathTo(v)) return null;
        LinkedStack<DirectedEdge> path = new LinkedStack<>();
        for (DirectedEdge e = edgeTo[v]; e!=null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    public static void main(String[] args){
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        BellmanFordSP sp = new BellmanFordSP(G, s);

        if (sp.hasNegativeCycle()){
            for (DirectedEdge e : sp.negativeCycle())
                StdOut.println(e);
        }

        else{
            for (int t=0; t < G.V(); t++){
                StdOut.print(s + " to " + t);
                StdOut.printf(" (%4.2f) ", sp.distTo(t));
                if (sp.hasPathTo(t))
                    for (DirectedEdge e : sp.pathTo(t))
                        StdOut.print(e + "  ");
                StdOut.println();
            }
        }
    }
}
