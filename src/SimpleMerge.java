import java.util.HashSet;
import java.util.Set;

public class SimpleMerge extends NonTerminalExpression{
    private Set<MyElement> Elements;

    public SimpleMerge(Set<MyElement> Elements){
        this.Elements = Elements;
    }

    @Override
    public AbstractExpression interpret(){
        Set<Integer> ids = new HashSet<Integer>();

        for(MyElement elem : Elements){
            ids.add(elem.getID());
        }

        Link fakeLink = new Link(ids);

        Link foundLink = MyApp.getLinks().stream().filter(link -> fakeLink.compare(link)).findAny().orElseThrow();

        MyElement out = MyApp.getUnknownElements().stream().filter(element -> element.getID() == foundLink.getCreated()).findAny().orElseThrow();

        return out;
    }


}
