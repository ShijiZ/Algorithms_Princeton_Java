/*
 * Sample usage: java DijkstraSP tinyEWD.txt 0
 * Sample usage: java DijkstraSP mediumEWD.txt 0
 */

public class DijkstraSP {
    private DirectedEdge[] edgeTo;     // edgeTo[v] = last edge on shortest s->v path
    private double[] distTo;           // distTo[v] = distance of shortest s->v path
    private IndexMinPQ<Double> pq;     // priority queue of vertices

    public DijkstraSP(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());

        for (int v=0; v<G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0;

        pq.insert(s, 0.0);
        // relax vertices in order of distance from s
        while (!pq.isEmpty())
            relax(G, pq.delMin());
    }

    // relax vertex v (i.e. relax each edge e from v)
    private void relax(EdgeWeightedDigraph G, int v){
        for (DirectedEdge e : G.adj(v))
            relax(e);
    }

    // relax edge e and update pq if changed
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.change(w, distTo[w]);
            else pq.insert(w, distTo[w]);
        }
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
        DijkstraSP sp = new DijkstraSP(G, s);

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
