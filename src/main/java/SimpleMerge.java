import java.util.HashSet;
import java.util.Set;

//Parts of the design pattern interpreter
//Merges elements
public class SimpleMerge extends NonTerminalExpression{
    private Set<MyElement> Elements;

    //Constructor
    public SimpleMerge(Set<MyElement> Elements){
        this.Elements = Elements;
    }


    //Interprets a merge based on elements he gets
    //Create a fake link and compare it to existent links
    //Move the element created from the unknown elements into the known elements
    @Override
    public AbstractExpression interpret() {
        Set<Integer> ids = new HashSet<Integer>();

        for (MyElement elem : Elements) {
            ids.add(elem.getID());
        }

        Link fakeLink = new Link(ids);

        try {
            Link foundLink = MyApp.getLinks().stream().filter(link -> fakeLink.compare(link)).findAny().orElseThrow();
            MyElement out = MyApp.getUnknownElements().stream().filter(element -> element.getID().equals(foundLink.getCreated())).findAny().orElseThrow();
            return out;
        } catch (Exception e) {
            System.out.println("Unknown Link");
            return null;
        }
    }
}
