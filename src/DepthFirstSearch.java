/*
 * Sample usage: java DepthFirstSearch tinyG.txt 0
 * Sample usage: java DepthFirstSearch mediumG.txt 0
 */

public class DepthFirstSearch {
    private boolean[] marked;   // marked[v] = is there a s-v path?
    private int count;          // number of vertices connected to s

    public DepthFirstSearch(Graph G, int s){
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v){
        marked[v] = true;
        count++;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
    }

    public boolean marked(int w){
        return marked[w];
    }

    public int count(){
        return count;
    }

    public static void main(String[] args){
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch search = new DepthFirstSearch(G, s);

        for (int v=0; v<G.V(); v++)
            if (search.marked(v))
                StdOut.println(v + " ");
        StdOut.println();

        if (search.count() != G.V())
            StdOut.print("NOT ");
        StdOut.println("connected");
    }
}
