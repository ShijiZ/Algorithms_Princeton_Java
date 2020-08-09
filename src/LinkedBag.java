/*
 * Sample usage: java LinkedBag < tobe.txt
 */

import java.util.Iterator;

public class LinkedBag<Item> implements Iterable<Item> {
    private Node first;  // top of stack (most recently added node)
    private int N;       // number of items

    private class Node{
        // nested class to define nodes
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    } // Or: N == 0.

    public int size(){
        return N;
    }

    public void add(Item item){
        // Add item to the bag
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        public boolean hasNext(){
            return current != null;
        }

        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove(){ }
    }

    public static void main(String[] args) {
        LinkedBag<String> bag = new LinkedBag<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
        }

        StdOut.println("size of bag = " + bag.size());
        for (String s : bag) {
            StdOut.println(s);
        }
    }
}
