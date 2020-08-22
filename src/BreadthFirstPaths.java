/*
 * Sample usage: java BreadthFirstPaths tinyG.txt 0
 * Sample usage: java BreadthFirstPaths mediumG.txt 0
 */

public class BreadthFirstPaths {
    private boolean[] marked;   // marked[v] = is there a shortest s-v path?
    private int[] edgeTo;       // last vertex on known path to this vertex
    private final int s;        // the source

    public BreadthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s){
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
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths search = new BreadthFirstPaths(G, s);

        for (int v=0; v<G.V(); v++){
            StdOut.print(s + " to " + v + ": ");
            if (search.hasPathTo(v)){
                for (int x: search.pathTo(v))
                    if (x==s) StdOut.print(x);
                    else StdOut.print("-"+x);
                StdOut.println();
            }

            else StdOut.print("not connected\n");
        }
    }
}
