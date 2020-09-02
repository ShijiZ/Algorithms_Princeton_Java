/*
 * Sample usage: java DirectedDFS tinyDG.txt 1
 * Sample usage: java DirectedDFS tinyDG.txt 2
 * Sample usage: java DirectedDFS tinyDG.txt 1 2 6
 */

public class DirectedDFS {
    private boolean[] marked;   // marked[v] = is there a s-v path?

    public DirectedDFS(Digraph G, int s){
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources){
        marked = new boolean[G.V()];
        for (int s: sources)
            if (!marked[s])
                dfs(G, s);
    }

    private void dfs(Digraph G, int v){
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
    }

    public boolean marked(int w){
        return marked[w];
    }

    public static void main(String[] args){
        Digraph G = new Digraph(new In(args[0]));

        LinkedBag<Integer> sources = new LinkedBag<>();
        for (int i=1; i<args.length; i++)
            sources.add(Integer.parseInt(args[i]));

        DirectedDFS reachable = new DirectedDFS(G, sources);

        for (int v=0; v<G.V(); v++)
            if (reachable.marked(v))
                StdOut.println(v + " ");
        StdOut.println();
    }
}
