import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Context {

    private String[] words;

    public Context(String[] words){
        this.words = words;
        create();
    }

    private void create(){

        Set<MyElement> knownElements  = MyApp.getKnownElements();
        System.out.println(knownElements);
        Set<MyElement> myElements = new HashSet<MyElement>();
        for (String word: words) {
            try {
                myElements.add(knownElements.stream().filter(element -> word.equals(element.getName())).findAny().orElseThrow());
            }
            catch (Exception e) {
                System.out.println("Unknown element.");
            }
        }

        for (MyElement elem : myElements) {
            System.out.println(elem.getName());
    }
    }
}