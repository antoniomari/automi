import javax.sound.sampled.Line;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Grammar
{


    public static class Production
    {
        private char head;
        private char[] body;

        Production(char head, String body)
        {
            this.head = head;
            this.body = body.toCharArray();
        }

        public char getHead()
        {
            return head;
        }

        public char[] getBody() {
            return body;
        }

        public void setTesta(char testa)
        {
            this.head = testa;
        }

        public void setBody(String body)
        {
            this.body = body.toCharArray();
        }

        public static Collection<Production> select(Collection<Production> prods, char head)
        {
            Collection<Production> selected = new LinkedList<Production>();
            for(Production p : prods)
                if(p.head == head)
                    selected.add(p);

            return selected;
        }

        public static Collection<Production> select(Collection<Production> prods, String body)
        {
            Collection<Production> selected = new LinkedList<Production>();
            for(Production p : prods)
                if(Arrays.equals(p.body, body.toCharArray())) //
                    selected.add(p);

            return selected;
        }

        @Override
        public String toString()
        {
            String bodyString;

            if(body.length == 0)
                bodyString = "_";
            else
                bodyString = String.valueOf(body);

            return head + "->" + bodyString;
        }
    }

    private char axiom;
    private Collection<Character> alphabet;
    private Collection<Character> nonterminals;
    private Collection<Production> prod;

    private Collection<Character> availableTerminals;
    private Collection<Character> availableNonterminals;

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

    Grammar(char[] alphabet, char[] nonterminals, char axiom, Production[] prod)
    {
        this();
        addTerminal(alphabet);
        addNonterminal(nonterminals);
        setAxiom(axiom);

        for(Production p : prod)
        {
            addProduction(p);
        }
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

    public boolean isTerminal(char c)
    {
        return alphabet.contains(c);
    }

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

    public boolean isNonterminal(char c)
    {
        return nonterminals.contains(c);
    }

    public void addProduction(char head, String body)
    {

        if(!nonterminals.contains(head))
            return;
        for(char c : body.toCharArray())
        {
            if(!nonterminals.contains(c) && !alphabet.contains(c))
                return;
        }//end for

        for(Production p : prod)
        {
            if(p.head == head && Arrays.equals(p.body, body.toCharArray()))
                return;
        }

        Production newProd = new Production(head, body);
        this.prod.add(newProd);
    }

    public void addProduction(Production p)
    {
        addProduction(p.head, String.valueOf(p.body));
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

    public Collection<Production> getProductions()
    {
        return prod;
    }
    public char getAxiom()
    {
        return axiom;
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

        //step 1: new axiom
        char newAxiom;
        if(availableNonterminals.isEmpty())
            return this;
        else
        {
            newAxiom = availableNonterminals.getLast();
            addNonterminal(newAxiom);
            addProduction(newAxiom, ((Character) axiom).toString());
            axiom = newAxiom;
        }
        /*
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

        return this;
    }
    */


    public boolean CYK(String str, boolean comment) throws Exception {
        if(!alphabetCheck(str))
            return false;

        if(!isChomskyForm())
            throw new Exception("Grammar not in Chomsky Form. Inapplicable algorithm");

        int len = str.length();
        LinkedList<Character>[][] matrixCYK = new LinkedList[len][len];

        //populate first row
        for(int j = 0; j < str.length(); j++)
        {
            LinkedList<Character> temp = new LinkedList<Character>();
            for(Production p : Production.select(prod, ((Character) str.charAt(j)).toString()))
            {
                char head = p.getHead();
                if(!temp.contains(head))
                    temp.add(head);
            }

            matrixCYK[0][j] = temp;

            if(comment)
                System.out.println("Element in [0][" + j + "]: " + temp);
        }

        //populate other rows
        for(int i = 1; i < len; i++)
        {
            for(int j = 0; j < len - i; j++)
            {
                LinkedList<Character> temp = new LinkedList<Character>();

                for(int k = 1; k <= i; k++)
                {

                    for(Character c : concatenateListCYK(matrixCYK[k-1][j], matrixCYK[i-k][j+k]))
                        if(!temp.contains(c))
                            temp.add(c);
                }
                matrixCYK[i][j] = temp;

                if(comment)
                    System.out.println("Element in [" + i + "][" + j + "]: " + temp);
            }
        }

        LinkedList<Character> finalList = matrixCYK[len-1][0];

        return finalList.contains(axiom);
    }

    private List<Character> concatenateListCYK(List<Character> l1, List<Character> l2)
    {
        List<char[]> concList = new LinkedList<char[]>();
        List<Character> resultList = new LinkedList<Character>();

        for(Character nt1 : l1)
        {
            for(Character nt2 : l2)
            {
                char[] tempArr = {nt1, nt2};
                concList.add(tempArr);
            }
        }

        for(char[] conc : concList)
        {

            Collection<Production> selected = Production.select(prod, String.valueOf(conc));
            for(Production p : selected)
            {
                Character head = p.getHead();

                if(!resultList.contains(head))
                    resultList.add(head);
            }
        }

        return resultList;


    }


}
