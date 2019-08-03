/*
 * Sample usage: java-alg4 ST < tinyST.txt
 */

import java.util.TreeMap;

public class ST<Key extends Comparable<Key>, Value> {
    private TreeMap<Key, Value> st;

    public ST(){
        st = new TreeMap<Key, Value>();
    }

    public Value get(Key key){
        return st.get(key);
    }

    public void put(Key key, Value val){
        if (val == null) st.remove(key);
        else st.put(key, val);
    }

    public void delete(Key key){
        st.remove(key);
    }

    public boolean contains(Key key){
        return st.containsKey(key);
    }

    public int size(){
        return st.size();
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public Iterable<Key> keys() {
        return st.keySet();
    }

    public static void main(String[] args){
        ST<String, Integer> st = new ST<>();

        for (int i=0; !StdIn.isEmpty(); i++){
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s: st.keys())
            StdOut.println(s + " " + st.get(s));
    }

}
