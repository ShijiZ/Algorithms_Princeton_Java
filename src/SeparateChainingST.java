/*
 * Sample usage: java SeparateChainingST < tinyST.txt
 */

public class SeparateChainingST<Key, Value> {
    private int N;      // number of key-value pairs
    private int M;      // hash table size
    private SequentialSearchST<Key, Value>[] st; // array of ST objects

    public SeparateChainingST(){
        this(997);
    }

    public SeparateChainingST(int M){
        // Create M linked lists.
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i=0; i<M; i++)
            st[i] = new SequentialSearchST();
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

    public Value get(Key key){
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value val){
        int i = hash(key);
        if (!st[i].contains(key)) N++;
        st[i].put(key, val);
    }

    public void delete(Key key){
        int i = hash(key);
        if (st[i].contains(key)) N--;
        st[i].delete(key);
    }

    public Iterable<Key> keys(){
        LinkedQueue<Key> queue = new LinkedQueue<>();
        for (int i=0 ; i < M; i++)
            for (Key key: st[i].keys())
                queue.enqueue(key);
        return queue;
    }

    public static void main(String[] args){
        SeparateChainingST<String, Integer> st = new SeparateChainingST<>();

        for (int i=0; !StdIn.isEmpty(); i++){
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s: st.keys())
            StdOut.println(s + " " + st.get(s));
    }

}
