/*
 * Sample usage: java-alg4 BinarySearchST < tinyST.txt
 */

public class BinarySearchST<Key extends Comparable<Key>, Value>{
    private Key[] keys;
    private Value[] vals;
    private int N;

    // Initializes an empty symbol table with the initial capacity 2.
    public BinarySearchST(){
        keys = (Key[]) new Comparable[2];
        vals = (Value[]) new Object[2];
    }

    // resize the underlying arrays
    private void resize(int capacity){
        assert capacity >= N;
        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i=0; i<N; i++){
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
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

    // Returns the number of keys in this symbol table strictly less than key
    public int rank(Key key){
        int lo=0, hi = N-1;
        while (lo <= hi){
            int mid = lo+(hi-lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid-1;
            else if (cmp > 0) lo = mid+1;
            else return mid;
        }
        return lo;
    }

    public Value get(Key key){
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key)==0) return vals[i];
        else return null;
    }

    public void put(Key key, Value val){
        // Search for key. Update value if found; grow table if new.
        int i = rank(key);
        if (i<N && keys[i].compareTo(key)==0){
            vals[i] = val;
            return;
        }
        // insert new key-value pair
        if (N==keys.length) resize(2*keys.length);
        for (int j=N; j>i; j--){
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public void delete(Key key){
        int i = rank(key);
        for (int j = i; j < N-1; j++){
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        N--;
        keys[N] = null; // to avoid loitering
        vals[N] = null;
        //resize if 1/4 full
        if (N>0 && N==keys.length/4) resize(keys.length/2);
    }

    public Key min(){
        return keys[0];
    }

    public Key max(){
        return keys[N-1];
    }

    public Key select(int k){
        return keys[k];
    }

    // Returns the smallest key in this symbol table greater than or equal to key.
    public Key ceiling(Key key){
        int i = rank(key);
        if (i==N) return null;
        else return keys[i];
    }

    // Returns the greatest key in this symbol table less than or equal to key.
    public Key floor(Key key){
        int i = rank(key);
        if (i<N && key.compareTo(keys[i])==0) return keys[i];
        if (i==0) return null;
        else return keys[i-1];
    }

    public void deleteMin(){
        delete(min());
    }

    public void deleteMax(){
        delete(max());
    }

    public int size(Key lo, Key hi){
        if (hi.compareTo(lo)<0) return 0;
        else if (contains(hi)) return rank(hi)-rank(lo)+1;
        else return rank(hi)-rank(lo);
    }

    public Iterable<Key> keys(Key lo, Key hi){
        LinkedQueue<Key> queue = new LinkedQueue<>();
        if (lo.compareTo(hi)>0) return queue;
        for (int i = rank(lo); i < rank(hi); i++)
            queue.enqueue(keys[i]);
        if (contains(hi)) queue.enqueue(keys[rank(hi)]);
        return queue;
    }

    public Iterable<Key> keys(){
        return keys(min(), max());
    }

    public static void main(String[] args){
        BinarySearchST<String, Integer> st = new BinarySearchST<>();

        for (int i=0; !StdIn.isEmpty(); i++){
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s: st.keys())
            StdOut.println(s + " " + st.get(s));
    }

}
