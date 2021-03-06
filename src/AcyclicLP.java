/*
 * Sample usage: java AcyclicLP tinyEWDAG.txt 5
 */

public class AcyclicLP {
    private DirectedEdge[] edgeTo;     // edgeTo[v] = last edge on longest s->v path
    private double[] distTo;           // distTo[v] = distance of longest s->v path

    public AcyclicLP(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        for (int v=0; v<G.V(); v++)
            distTo[v] = Double.NEGATIVE_INFINITY;
        distTo[s] = 0;

        Topological top = new Topological(G);

        for (int v : top.order())
            relax(G, v);
    }

    // relax vertex v (i.e. relax each edge e from v)
    private void relax(EdgeWeightedDigraph G, int v){
        for (DirectedEdge e : G.adj(v))
            relax(e);
    }

    // relax edge e
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] < distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] > Double.NEGATIVE_INFINITY;
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
        AcyclicLP sp = new AcyclicLP(G, s);

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
