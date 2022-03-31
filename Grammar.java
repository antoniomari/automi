import java.util.Arrays;
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

        @Override
        public String toString() {
            return head + "->" + String.valueOf(body);
        }
    }

    private char axiom;
    private LinkedList<Character> alphabet;
    private LinkedList<Character> nonterminals;
    private LinkedList<Production> prod;

    // CONSTRUCTOR
    Grammar()
    {
        alphabet = new LinkedList<>();
        nonterminals = new LinkedList<>();
        prod = new LinkedList<>();
    }

    public void addTerminal(char t)
    {
        if(!alphabet.contains(t) && !nonterminals.contains(t))
            alphabet.add(t);
    }//end addTerminal

    public void addTerminal(char[] tArray)
    {
        for(char t : tArray)
            addTerminal(t);
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
            addNonterminal(nt);
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

    public void addProduction(char head, String[] bodies)
    {
        for(String body : bodies)
            addProduction(head, body);
    }

    public void setAxiom(char axiom)
    {
        if(!nonterminals.contains(axiom) || alphabet.contains(axiom))
            return;

        this.axiom = axiom;
    }

    @Override
    public String toString() {
        return "Grammar{" +
                "axiom=" + axiom +
                ", alphabet=" + alphabet +
                ", nonterminals=" + nonterminals +
                ", prod=" + prod +
                '}';
    }

    private boolean alphabetCheck(String str)
    {
        for(char c : str.toCharArray())
            if(!alphabet.contains(c))
                return false;
        return true;
    }


    /*
        CYK algorithm
    public boolean CYK(String str)
    {
        if(!alphabetCheck(str))
            return false;

        int len = str.length();
        Object[][] matrixCYK = new Object[len][len];


    }

    */
}
