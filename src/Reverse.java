/*
 * Sample usage: java Reverse < numbers.txt
 */

public class Reverse {
    public static void main(String[] args){
        LinkedStack<Integer> stack;
        stack = new LinkedStack<Integer>();
        while (!StdIn.isEmpty())
            stack.push(StdIn.readInt());

        for (int i: stack)
            StdOut.println(i);
    }
}
