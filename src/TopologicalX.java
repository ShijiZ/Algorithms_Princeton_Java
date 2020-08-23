/*
 * Sample usage: java TopologicalX jobs.txt "/"
 */

public class TopologicalX {
    private LinkedQueue<Integer> order;       // topological order

    public TopologicalX(Digraph G){
        DirectedCycle cyclefinder = new DirectedCycle(G);
        if (!cyclefinder.hasCycle()){
            // indegrees of remaining vertices
            int[] indegree = new int[G.V()];
            for (int v = 0; v < G.V(); v++) {
                for (int w : G.adj(v)) {
                    indegree[w]++;
                }
            }

            order = new LinkedQueue<>();

            // initialize queue to contain all vertices with indegree = 0;
            LinkedQueue<Integer> queue = new LinkedQueue<>();
            for (int v = 0; v < G.V(); v++) {
                if (indegree[v] == 0)
                    queue.enqueue(v);
            }

            while (!queue.isEmpty()) {
                int v = queue.dequeue();
                order.enqueue(v);
                for (int w : G.adj(v)) {
                    indegree[w]--;
                    if (indegree[w] == 0)
                        queue.enqueue(w);
                }
            }
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public boolean isDAG(){
        return order==null;
    }

    public static void main(String[] args){
        String filename = args[0];
        String delim = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, delim);

        TopologicalX top = new TopologicalX(sg.G());

        for (int v: top.order())
            StdOut.println(sg.name(v));

    }
}
