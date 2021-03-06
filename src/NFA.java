/*
 * Sample usage: java NFA (A*B|AC)D AAAABD
 * true
 *
 * Sample usage: java NFA (A*B|AC)D AAAAC
 * false
 *
 * Sample usage: java NFA (a|(bc)*d)* abcbcd
 * true
 *
 * Sample usage: java NFA (a|(bc)*d)* abcbcbcdaaaabcbcdaaaddd
 * true
 */

public class NFA {
    private char[] re;       // match transitions
    private Digraph G;       // epsilon transitions
    private int M;           // number of states

    public NFA(String regexp){
        // Create the NFA for the given regular expression.
        LinkedStack<Integer> ops = new LinkedStack<>();
        re = regexp.toCharArray();
        M = re.length;
        G = new Digraph(M+1);

        for (int i=0; i<M; i++){
            int lp = i; // (might be) left paren

            if (re[i] == '(' || re[i] == '|')
                ops.push(i);
            else if (re[i] == ')'){
                int or = ops.pop(); // (might be) or

                // 2-way or operator
                if (re[or] == '|'){
                    lp = ops.pop();
                    G.addEdge(lp, or+1);
                    G.addEdge(or, i);
                }
                else lp = or;
            }

            // closure operator (uses 1-character lookahead)
            if (i<M-1 && re[i+1] == '*'){
                G.addEdge(lp, i+1);
                G.addEdge(i+1, lp);
            }

            if (re[i] == '(' || re[i] == '*' || re[i] == ')')
                G.addEdge(i, i+1);
        }
    }

    // Returns true if the text is matched by the regular expression.
    public boolean recognizes(String txt){
        LinkedBag<Integer> pc = new LinkedBag<>();
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for (int v=0; v<G.V(); v++)
            if (dfs.marked(v))
                pc.add(v);

        // Compute possible NFA states for txt[i+1]
        for (int i = 0; i<txt.length(); i++){
            LinkedBag<Integer> match = new LinkedBag<>();
            for (int v : pc)
                if (v<M)
                    if (re[v] == txt.charAt(i) || re[v] == '.')
                        match.add(v+1);
            pc = new LinkedBag<>();
            dfs = new DirectedDFS(G, match);
            for (int v=0; v<G.V(); v++)
                if (dfs.marked(v))
                    pc.add(v);
        }

        // check for accept state
        for (int v : pc)
            if (v==M) return true;
            return false;
    }

    public static void main(String[] args){
        String regexp = '(' + args[0] + ')';
        String txt = args[1];
        NFA nfa = new NFA(regexp);
        StdOut.println(nfa.recognizes(txt));
    }
}
