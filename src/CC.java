/*
 * Sample usage: java CC tinyG.txt
 * Sample usage: java CC mediumG.txt
 */

public class CC {
    private boolean[] marked;
    private int[] id;           // id[v] = id of connected component containing v
    private int count;          // number of connected components

    public CC(Graph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int s=0; s<G.V(); s++)
            if (!marked(s)){
                dfs(G, s);
                count++;
            }
    }

    private void dfs(Graph G, int v){
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

    public boolean connected(int w, int v){
        return  id[w]==id[v];
    }

    public int id(int v){
        return id[v];
    }

    public static void main(String[] args){
        Graph G = new Graph(new In(args[0]));
        CC cc = new CC(G);

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
