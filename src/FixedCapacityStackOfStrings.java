/*
 * Sample usage: java-alg4 FixedCapacityStackOfStrings < tobe.txt
 */

public class FixedCapacityStackOfStrings {
    private String[] a; //stack entries
    private int N;      // sizes

    public FixedCapacityStackOfStrings(int cap){
        a = new String[cap];
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    public void push(String item){
        // Add item to top of the stack
        a[N++] = item;
    }

    public String pop(){
        // Remove item from top of the stack
        return a[--N];
    }

    public static void main(String[] args){
        FixedCapacityStackOfStrings s;
        s = new FixedCapacityStackOfStrings(100);

        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!item.equals("-")){
                s.push(item);
            }

            else if (!item.isEmpty()){
                StdOut.print(s.pop()+" ");
            }
        }

        StdOut.println("("+s.size()+" left in stack)");
    }

}
