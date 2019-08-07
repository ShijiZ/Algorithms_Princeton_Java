/*
 * Sample usage: java-alg4 DepthFirstOrder tinyDAG.txt
 */

public class DepthFirstOrder {
    private boolean[] marked;   // marked[v] = is there a s-v path?

    private LinkedQueue<Integer> pre;         // vertices in preorder
    private LinkedQueue<Integer> post;        // vertices in postorder
    private LinkedStack<Integer> reversePost; // vertices in reverse postorder

    public DepthFirstOrder(Digraph G){
        marked = new boolean[G.V()];
        pre = new LinkedQueue<>();
        post = new LinkedQueue<>();
        reversePost = new LinkedStack<>();

        for (int v=0; v<G.V(); v++)
            if (!marked[v])
                dfs(G, v);
    }

    private void dfs(Digraph G, int v){
        pre.enqueue(v);

        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);

        post.enqueue(v);
        reversePost.push(v);
    }

    public boolean marked(int w){
        return marked[w];
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public Iterable<Integer> reversePost(){
        return reversePost;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        DepthFirstOrder dfs = new DepthFirstOrder(G);

        StdOut.print("Preorder: ");
        for (int v: dfs.pre())
            StdOut.print(v+" ");
        StdOut.println();

        StdOut.print("Postorder: ");
        for (int v: dfs.post())
            StdOut.print(v+" ");
        StdOut.println();

        StdOut.print("Reverse postorder: ");
        for (int v: dfs.reversePost())
            StdOut.print(v+" ");
        StdOut.println();
    }
}
