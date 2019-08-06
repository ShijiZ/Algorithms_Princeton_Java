/*
 * Sample usage: java-alg4 DepthFirstPaths tinyG.txt 0
 * Sample usage: java-alg4 DepthFirstPaths mediumG.txt 0
 */

public class DepthFirstPaths {
    private boolean[] marked;   // marked[v] = is there a s-v path?
    private int[] edgeTo;       // last vertex on known path to this vertex
    private final int s;        // the source

    public DepthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v){
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w]){
                edgeTo[w] = v;
                dfs(G, w);
            }
    }

    public boolean marked(int w){
        return marked[w];
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)) return null;
        LinkedStack<Integer> path = new LinkedStack<>();
        for (int x=v; x!=s; x=edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    public static void main(String[] args){
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstPaths search = new DepthFirstPaths(G, s);

        for (int v=0; v<G.V(); v++){
            StdOut.print(s + " to " + v + ": ");
            if (search.hasPathTo(v)){
                for (int x: search.pathTo(v))
                    if (x==s) StdOut.print(x);
                    else StdOut.print("-"+x);
            StdOut.println();
            }

            else StdOut.printf("%d to %d: not connected\n", s, v);
        }
    }
}
