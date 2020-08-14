/*
 * Sample usage: java WeightedQuickUnionUF < tinyUF.txt
 * Sample usage: java WeightedQuickUnionUF < mediumUF.txt
 */

public class WeightedQuickUnionUF {
    private int[] id;     // access to component id (site indexed)
    private int[] sz;     // size of component for roots (site indexed)
    private int count;    // number of components

    public WeightedQuickUnionUF(int N){
        // Initialize component id and component sz array.
        count = N;
        id = new int[N];
        for (int i=0; i<N; i++)
            id[i] = i;
        sz = new int[N];
        for (int i=0; i<N; i++)
            sz[i] = 1;
    }

    public int count(){
        return count;
    }

    public boolean connected(int p, int q){
        return find(p)==find(q);
    }

    public int find(int p){
        // Find component name.
        while (p != id[p])
            p = id[p];
        return p;
    }

    public void union(int p, int q){
        // Give p and q the same root.
        int i = find(p);
        int j = find(q);

        // Nothing to do if p and q are already in the same component.
        if (i == j)
            return;

        // Make smaller root point to larger one.
        if (sz[i] < sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }
        else{
            id[j] = i;
            sz[i] += sz[j];
        }

        count--;
    }

    public static void main(String[] args){
        // Solve dynamic connectivity problem on StdIn.
        int N = StdIn.readInt();               // Read number of sites.
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N); // Initialize N components.
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();           // Read pair to connect.
            if (uf.connected(p, q))            // Ignore if connected.
                continue;
            uf.union(p,q);                     // Combine components
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
