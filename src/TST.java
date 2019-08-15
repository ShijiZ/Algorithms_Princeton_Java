/*
 * Sample usage: java-alg4 TST < shellsST.txt
 */

public class TST<Value> {
    private Node root;          // root of trie
    private int N;              // number of keys in trie

    private class Node{
        char c;                 // character
        Node left, mid, right;  // left, middle and right subtries
        Value val;              // value associated with string
    }

    public Value get(String key){
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d){
        // Return value associated with key in the subtrie rooted at x.
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length()-1) return get(x.mid, key, d+1);
        else return x;
    }

    public void put(String key, Value val){
        if (!contains(key)) N++;
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d){
        // Change value associated with key if in subtrie rooted at x.
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) x.left = put(x.left, key, val, d);
        else if (c > x.c) x.right = put(x.right, key, val, d);
        else if (d < key.length()-1) x.mid = put(x.mid, key, val, d+1);
        else x.val = val;

        return x;
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public boolean contains(String key){
        return get(key) != null;
    }

    public static void main(String[] args){
        // build symbol table from standard input
        TrieST<Integer> st = new TrieST<>();
        for (int i = 0; !StdIn.isEmpty(); i++){
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print results
        if (st.size() < 100){
            StdOut.println("keys(\"\"):");
            for (String key : st.keys()){
                StdOut.println(key + " " + st.get(key));
            }
            StdOut.println();
        }
    }
}
