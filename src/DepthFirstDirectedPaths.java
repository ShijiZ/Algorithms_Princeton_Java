/*
 * Sample usage: java DepthFirstDirectedPaths tinyDG.txt 3
 * Sample usage: java DepthFirstDirectedPaths mediumDG.txt 3
 */

public class DepthFirstDirectedPaths {
    private boolean[] marked;   // marked[v] = is there a s-v path?
    private int[] edgeTo;       // last vertex on known path to this vertex
    private final int s;        // the source

    public DepthFirstDirectedPaths(Digraph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Digraph G, int v){
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
        Digraph G = new Digraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(G, s);

        for (int v=0; v<G.V(); v++){
            StdOut.print(s + " to " + v + ": ");
            if (dfs.hasPathTo(v)){
                for (int x: dfs.pathTo(v))
                    if (x==s) StdOut.print(x);
                    else StdOut.print("->"+x);
                StdOut.println();
            }

            else StdOut.print("not connected\n");
        }
    }
}
