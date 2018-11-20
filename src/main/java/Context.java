import java.util.HashSet;
import java.util.Set;

//Reads the parsed sentence
//Tries to find a link between the two elements in the sentence
//Merges them if a link is found
public class Context {

    private String[] words;

    //Constructor
    public Context(String[] words){
        this.words = words;
    }

    // Here is where the alchemy performs.
    public void create(){
        Set<MyElement> knownElements  = MyApp.getKnownElements();
        Set<MyElement> elementsToMerge = new HashSet<MyElement>();
        for (String word: words) {
            try {
                elementsToMerge.add(knownElements.stream().filter(element -> word.equals(element.getName())).findAny().orElseThrow());
            }
            catch (Exception e) {
                System.out.println("Tryed to merge an unknown element.");
            }
        }

        SimpleMerge merge = new SimpleMerge(elementsToMerge);
        MyElement out = (MyElement) merge.interpret();
        if (out != null){
            System.out.println("You created " + out.getName() + " !");
            MyApp.elementMerged(out);
            MyApp.askIfWin();
        }
    }
}
