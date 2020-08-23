/*
 * Sample usage: java BreadthFirstDirectedPaths tinyDG.txt 3
 * Sample usage: java BreadthFirstDirectedPaths mediumDG.txt 3
 */

public class BreadthFirstDirectedPaths {
    private boolean[] marked;   // marked[v] = is there a shortest s-v path?
    private int[] edgeTo;       // last vertex on known path to this vertex
    private final int s;        // the source

    public BreadthFirstDirectedPaths(Digraph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Digraph G, int s){
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        marked[s] = true;            // mark the source
        queue.enqueue(s);            // put source in the queue
        while(!queue.isEmpty()){
            int v = queue.dequeue(); // Remove the next vertex from the queue
            for (int w : G.adj(v))
                if (!marked[w]){     // For every unmarked adjacent vertex
                    edgeTo[w] = v;   // save last edge on a shortest path
                    marked[w] = true;// mark it because path is known
                    queue.enqueue(w);// and add it to the queue
                }
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
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, s);

        for (int v=0; v<G.V(); v++){
            StdOut.print(s + " to " + v + ": ");
            if (bfs.hasPathTo(v)){
                for (int x: bfs.pathTo(v))
                    if (x==s) StdOut.print(x);
                    else StdOut.print("->"+x);
                StdOut.println();
            }

            else StdOut.print("not connected\n");
        }
    }
}
