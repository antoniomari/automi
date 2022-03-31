import java.util.LinkedList;

public class Grammar
{

    public static class Production
    {
        private char head;
        private char[] body;

        Production(char head, char[] body)
        {
            this.head = head;
            this.body = body;
        }

        public char getTesta()
        {
            return head;
        }

        public char[] getCorpo() {
            return body;
        }

        public void setTesta(char testa)
        {
            this.head = testa;
        }

        public void setCorpo(char[] corpo)
        {
            this.body = corpo;
        }
    }

    private char axiom;
    private LinkedList<Character> alphabet;
    private LinkedList<Character> nonterminals;
    private LinkedList<Production> prod;
    /*
    public void addProduction(char A, char[] b)
    {
        prod = new Produzione[prod.length + 1];
        prod[prod.length - 1] = new Produzione(A, b);
    }
    */

    // CONSTRUCTOR
    Grammar()
    {
        alphabet = new LinkedList<Character>();
        nonterminals = new LinkedList<Character>();
        prod = new LinkedList<Production>();
    }

    public void addTerminal(char t)
    {
        if(!alphabet.contains(t) && !nonterminals.contains(t))
            alphabet.add(t);
    }//end addTerminal

    public void addTerminal(char[] tArray)
    {
        for(char t : tArray)
        {
            if(!alphabet.contains(t) && !nonterminals.contains(t))
                alphabet.add(t);
        }//end for
    }//end addTerminal

    public void printAlphabet()
    {
        for(char t : alphabet)
            System.out.print(t + " ");

        System.out.println();
    }//end printAlphabet

    public void addNonterminal(char nt)
    {
        if(!alphabet.contains(nt) && !nonterminals.contains(nt))
            nonterminals.add(nt);
    }//end addNonterminal

    public void addNonterminal(char[] ntArray)
    {
        for(char nt : ntArray)
        {
            if(!alphabet.contains(nt) && !nonterminals.contains(nt))
                nonterminals.add(nt);
        }//end for
    }//end addNonterminal

    public void printNonterminals()
    {
        for(char nt : nonterminals)
            System.out.print(nt + " ");

        System.out.println();
    }//end printNonterminals

    public void addProduction(char head, String body)
    {
        if(!nonterminals.contains(head))
            return;
        for(char c : body.toCharArray())
        {
            if(!nonterminals.contains(c) && !alphabet.contains(c))
                return;
        }//end for

        Production newProd = new Production(head, body.toCharArray());
        this.prod.add(newProd);
    }
}
