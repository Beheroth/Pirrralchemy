import java.util.HashSet;
import java.util.Set;

public class Context {

    private String[] words;

    public Context(String[] words){
        this.words = words;
        create();
    }

    private void create(){

        Set<MyElement> knownElements  = MyApp.getKnownElements();
        Set<MyElement> elementsToMerge = new HashSet<MyElement>();
        for (String word: words) {
            try {
                elementsToMerge.add(knownElements.stream().filter(element -> word.equals(element.getName())).findAny().orElseThrow());
            }
            catch (Exception e) {
                System.out.println("Unknown element.");
            }
        }

        SimpleMerge merge = new SimpleMerge(elementsToMerge);
        MyElement out = (MyElement) merge.interpret();
        if (out != null){
            System.out.println("You created " + out.getName() + " !");
            //MyApp.elementMerged(out);
            MyApp.askIfWin();
        }
    }
}