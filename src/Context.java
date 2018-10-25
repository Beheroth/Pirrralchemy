import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Context {

    private String[] words;

    public Context(String[] words){
        this.words = words;
        create();
    }

    private void create(){
        List<MyElement> knownElements  = MyApp.getKnownElements().stream()
                .filter(element -> Arrays.stream(this.words).allMatch(s -> s == element.getName()))
                .collect(Collectors.toList());

        for (MyElement elem : knownElements) {
            System.out.println(elem.getName());
        }
    }
}