public class FixedCapacityStack<Item> {
    private Item[] a; //stack entries
    private int N;      // sizes

    public FixedCapacityStack(int cap){
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    public void push(Item item){
        // Add item to top of the stack
        a[N++] = item;
    }

    public Item pop(){
        // Remove item from top of the stack
        return a[--N];
    }

    public static void main(String[] args){
        FixedCapacityStack<String> s;
        s = new FixedCapacityStack<String>(100);

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
