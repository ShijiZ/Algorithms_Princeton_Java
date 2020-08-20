/*
 * Sample usage: java SET
 */

import java.util.Iterator;
import java.util.TreeSet;

public class SET<Key extends Comparable<Key>> implements Iterable<Key>{
    private TreeSet<Key> set;

    public SET(){
        set = new TreeSet<Key>();
    }

    public SET(SET<Key> x) {
        set = new TreeSet<Key>(x.set);
    }

    public void add(Key key){
        set.add(key);
    }

    public void delete(Key key){
        set.remove(key);
    }

    public boolean contains(Key key){
        return set.contains(key);
    }

    public int size(){
        return set.size();
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public Key ceiling(Key key) {
        Key k = set.ceiling(key);
        return k;
    }

    public Key floor(Key key) {
        Key k = set.floor(key);
        return k;
    }

    public Iterator<Key> iterator(){
        return set.iterator();
    }

    public static void main(String[] args) {
        SET<String> set = new SET<String>();
        StdOut.println("set = " + set);

        // insert some keys
        set.add("www.cs.princeton.edu");
        set.add("www.cs.princeton.edu");    // overwrite old value
        set.add("www.princeton.edu");
        set.add("www.math.princeton.edu");
        set.add("www.yale.edu");
        set.add("www.amazon.com");
        set.add("www.simpsons.com");
        set.add("www.stanford.edu");
        set.add("www.google.com");
        set.add("www.ibm.com");
        set.add("www.apple.com");
        set.add("www.slashdot.com");
        set.add("www.whitehouse.gov");
        set.add("www.espn.com");
        set.add("www.snopes.com");
        set.add("www.movies.com");
        set.add("www.cnn.com");
        set.add("www.iitb.ac.in");


        StdOut.println(set.contains("www.cs.princeton.edu"));
        StdOut.println(!set.contains("www.harvardsucks.com"));
        StdOut.println(set.contains("www.simpsons.com"));
        StdOut.println();

        StdOut.println("ceiling(www.simpsonr.com) = " + set.ceiling("www.simpsonr.com"));
        StdOut.println("ceiling(www.simpsons.com) = " + set.ceiling("www.simpsons.com"));
        StdOut.println("ceiling(www.simpsont.com) = " + set.ceiling("www.simpsont.com"));
        StdOut.println("floor(www.simpsonr.com)   = " + set.floor("www.simpsonr.com"));
        StdOut.println("floor(www.simpsons.com)   = " + set.floor("www.simpsons.com"));
        StdOut.println("floor(www.simpsont.com)   = " + set.floor("www.simpsont.com"));
        StdOut.println();

        StdOut.println("set = " + set);
        StdOut.println();

        // print out all keys in this set in lexicographic order
        for (String s : set) {
            StdOut.println(s);
        }

        StdOut.println();
        SET<String> set2 = new SET<String>(set);
        StdOut.println(set.equals(set2));
    }
}
