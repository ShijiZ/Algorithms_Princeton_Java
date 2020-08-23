/*
 * Sample usage: java KosarajuSCC tinyDG.txt
 * Sample usage: java KosarajuSCC mediumDG.txt
 */

public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;           // id[v] = id of connected component containing v
    private int count;          // number of strong components

    public KosarajuSCC(Digraph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s: order.reversePost())
            if (!marked(s)){
                dfs(G, s);
                count++;
            }
    }

    private void dfs(Digraph G, int v){
        marked[v] = true;
        id[v] = count;
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

    public boolean stronglyConnected(int w, int v){
        return id[w]==id[v];
    }

    public int id(int v){
        return id[v];
    }

    public static void main(String[] args){
        Digraph G = new Digraph(new In(args[0]));
        KosarajuSCC cc = new KosarajuSCC(G);

        int M = cc.count();
        StdOut.println(M + " components");

        LinkedBag<Integer>[] components;
        components = (LinkedBag[]) new LinkedBag[M];

        for (int i=0; i<M; i++)
            components[i] = new LinkedBag<Integer>();
        for (int v=0; v<G.V(); v++)
            components[cc.id(v)].add(v);
        for (int i=0; i<M; i++){
            for (int v: components[i])
                StdOut.print(v + " ");
            StdOut.println();
        }
    }
}
