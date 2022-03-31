public class Demo {
    public static void main(String[] args) {
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
    }


}
