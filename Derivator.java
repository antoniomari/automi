import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Random;

public class Derivator
{
    private Grammar gram;
    private StringBuilder strBuffer;
    private int numOfNt;
    private LinkedList<Integer> ntIndexes;
    private Random random;

    Derivator(Grammar gram)
    {
        this();

        this.gram = gram;
        this.strBuffer = new StringBuilder( ((Character) gram.getAxiom()).toString());
        this.numOfNt = 1;
        this.ntIndexes = new LinkedList<Integer>();
        ntIndexes.add(0);
    }

    Derivator()
    {
        random = new Random();
    }

    public void assignGrammar(Grammar gram)
    {
        this.gram = gram;
        this.strBuffer = new StringBuilder( (Character) gram.getAxiom());
        this.numOfNt = 1;
        this.ntIndexes = new LinkedList<Integer>();
        ntIndexes.add(0);
    }

    public void randomMove()
    {
        //choose random nonterminal
        int i = random.nextInt(ntIndexes.size());
        int index = ntIndexes.get(i);
        char head = strBuffer.charAt(index);

        //choice random production to apply
        LinkedList<Grammar.Production> availableProds = Grammar.Production.select(gram.getProductions(), head);
        Grammar.Production choice = availableProds.get(random.nextInt(availableProds.size()));

        //edit strBuffer
        strBuffer.deleteCharAt(index);
        strBuffer.insert(index, choice.getBody());

        //calculate new indexes
        ntIndexes = new LinkedList<>();
        numOfNt = 0;

        String updatedStr = strBuffer.toString();
        for(int j = 0; j < updatedStr.length(); j++)
            if(gram.isNonterminal(updatedStr.charAt(j)))
            {
                numOfNt++;
                ntIndexes.add(j);
            }
    }

    public String randomDerivation()
    {
        System.out.println(strBuffer);

        while(numOfNt > 0)
        {
            randomMove();
            System.out.println(strBuffer);
        }

        return strBuffer.toString();
    }

}
