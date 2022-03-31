import com.sun.scenario.effect.impl.prism.PrDrawable;
import sun.awt.image.ImageWatched;

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

    private LinkedList<Character> availableTerminals;
    private LinkedList<Character> availableNonterminals;

    // CONSTRUCTOR
    Grammar()
    {
        alphabet = new LinkedList<>();
        nonterminals = new LinkedList<>();
        prod = new LinkedList<>();

        availableTerminals = new LinkedList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

        availableNonterminals = new LinkedList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
    }

    /**
     *
     * @param t terminale da aggiungere
     */
    public void addTerminal(char t)
    {
        if(availableTerminals.contains(t))
        {
            alphabet.add(t);
            availableTerminals.remove( (Character)t );
        }//end if

    }//end addTerminal

    /**
     *
     * @param tArray array di terminali da aggiungere
     */
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

    /**
     *
     * @param nt non terminale da aggiungere
     */
    public void addNonterminal(char nt)
    {
        if(availableNonterminals.contains(nt))
        {
            nonterminals.add(nt);
            availableNonterminals.remove( (Character)nt );
        }

    }//end addNonterminal

    /**
     *
     * @param ntArray array di non terminali da aggiungere
     */
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

    public LinkedList<Production> getProductions()
    {
        return prod;
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

    public boolean isChomskyProd(Production p)
    {
        //first type: A -> a
        if(p.body.length == 1 && alphabet.contains(p.body[0]))
            return true;
        //second type: A -> BC
        else if(p.body.length == 2 && nonterminals.contains(p.body[0]) && p.body[0] != axiom &&
                nonterminals.contains(p.body[1]) && p.body[1] != axiom)
            return true;
        //third type: axiom -> e
        else if(p.body.length == 0 && p.head == axiom)
            return true;
        else
            return false;
    }

    public boolean isChomskyForm()
    {
        for(Production p : prod)
            if(!isChomskyProd(p))
                return false;

        return true;
    }

    /*
    private void replaceEmptyProduction(char nt, Production p)
    {

        LinkedList<Integer> indexes =
    }

    */

    /*
    public Grammar toChomskyForm()
    {
        // for now, I assume that there are no prod whose body contains the axiom
        //step 1: to do

        //step 2: delete e-prod
        for(Production p : prod)
        {
            if(p.body.length == 0)
            {
                char head = p.head;
                for(Production p1 : prod)
                {
                    if(Arrays.asList(p1.body).contains(p1))
                    {
                        replaceEmptyProduction(head, p1);
                    }
                }
            }
        }

        //step 3: delete unary prod

        //step 4: the remaining prod
    }
    */
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
