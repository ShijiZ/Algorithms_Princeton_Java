import java.util.Iterator;

public class LinkedQueue<Item> implements Iterable<Item>{
    private Node first;  // top of stack (most recently added node)
    private Node last;   // link to most recently added node
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

    public void enqueue(Item item){
        // Add item to the end of the list.
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()){
            first = last;
        }
        else{
            oldLast.next = last;
        }
        N++;
    }

    public Item dequeue(){
        // Remove item from the beginning of the list.
        Item item = first.item;
        first = first.next;
        if (isEmpty()){
            last = null;
        }
        N--;
        return item;
    }

    public Iterator<Item> iterator(){
        return new LinkedQueue.ListIterator();
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

    public static void main(String[] args){
        LinkedQueue<String> s;
        s = new LinkedQueue<String>();

        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!item.equals("-")){
                s.enqueue(item);
            }

            else if (!item.isEmpty()){
                StdOut.print(s.dequeue()+" ");
            }
        }

        StdOut.println("("+s.size()+" left in stack)");
    }
}
