/*
 * Sample usage: java SymbolDigraph routes.txt " "
 * Sample usage: java SymbolDigraph movies.txt "/"
 */

public class SymbolDigraph {
    private SeparateChainingST<String, Integer> st;      // String -> index
    private String[] keys;               // index -> String
    private Digraph G;                     // the graph

    public SymbolDigraph(String stream, String sp){
        st = new SeparateChainingST<>();
        In in = new In(stream);
        // First pass: builds the index by reading strings to associate
        // each distinct string with an index.
        while (in.hasNextLine()){
            String[] a = in.readLine().split(sp);
            for (int i=0; i<a.length; i++)
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
        }

        // Inverted index to get keys in an array
        keys = new String[st.size()];
        for (String name : st.keys())
            keys[st.get(name)] = name;

        // Second pass: builds the graph by connecting the first vertex
        // on each line to all the others.
        G = new Digraph(st.size());
        in = new In(stream);
        while (in.hasNextLine()){
            String[] a = in.readLine().split(sp);
            int v = st.get(a[0]);
            for (int i=1; i<a.length; i++)
                G.addEdge(v, st.get(a[i]));
        }
    }

    public boolean contains(String s){
        return st.contains(s);
    }

    public int index(String s){
        return st.get(s);
    }

    public String name(int v){
        return keys[v];
    }

    public Digraph G(){
        return G;
    }

    public static void main(String[] args){
        String filename = args[0];
        String delim = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, delim);

        Digraph G = sg.G();

        while (StdIn.hasNextLine()){
            String source = StdIn.readLine();
            for (int w : G.adj(sg.index(source)))
                StdOut.println("  "+sg.name(w));
        }
    }
}
