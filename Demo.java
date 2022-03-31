public class Demo {
    public static void main(String[] args) {

        /*
        Grammar gram = new Grammar();

        //gram setup
        char[] term = {'a', 'b'};

        gram.addTerminal(term);
        gram.addTerminal('a');

        char[] nonterm = {'S'};
        gram.addNonterminal(nonterm);

        gram.setAxiom('S');

        gram.addProduction('S', new String[] {"aSa", "bSb", "aa", "bb"});

        for(Grammar.Production p : gram.getProductions())
        {
            String result = gram.isChomskyProd(p)? " is" : " is not";
            System.out.println(p + result +" in Chomsky Form");
        }

        String result = gram.isChomskyForm()? " is" : " is not";
        System.out.println("Grammar" + result + " in Chomsky Form");


        gram.printAlphabet();

        System.out.println(gram);
        */

        //creazione grammatica esempio libro
        char[] terminals = {'a', 'b'};
        char[] nonterminals = {'A', 'B', 'S'};
        char axiom = 'S';
        Grammar.Production[] prods = new Grammar.Production[6];
        prods[0] = new Grammar.Production('S', "ASA");
        prods[1] = new Grammar.Production('S', "aB");
        prods[2] = new Grammar.Production('A', "B");
        prods[3] = new Grammar.Production('A', "S");
        prods[4] = new Grammar.Production('B', "b");
        prods[5] = new Grammar.Production('B', "");

        Grammar gram2 = new Grammar(terminals, nonterminals, axiom, prods);

        for(Grammar.Production p : gram2.getProductions())
        {
            String result = gram2.isChomskyProd(p)? " is" : " is not";
            System.out.println(p + result +" in Chomsky Form");
        }

        System.out.println(gram2);

        Derivator der = new Derivator(gram2);

        String result = der.randomDerivation();
    }


}
