/*
 * Sample usage: java-alg4 SequentialSearchST < tinyST.txt
 */

public class SequentialSearchST<Key, Value> {
    private int N;      // number of key-value pairs
    private Node first; // first node in the linked list

    private class Node{
        // linked-list node
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key){
        // Search for key, return associated value.
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.val;  // search hit
        return null;           // search miss
    }

    public void put(Key key, Value val){
        // Search for key, update value if found; grow table if new.
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)){
                x.val = val;
                return;        // search hit: update all
            }
        first = new Node(key, val, first);  // search miss: add new node
        N++;
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

    public void delete(Key key){
        put(key, null);
        N--;
    }

    public Iterable<Key> keys(){
        LinkedQueue<Key> queue = new LinkedQueue<>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }

    public static void main(String[] args){
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();

        for (int i=0; !StdIn.isEmpty(); i++){
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s: st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
