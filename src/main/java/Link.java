import java.util.Set;

//Link between two elements
//Contains linked element and result of the merge
public class Link {

    //IDs of the linked element
    private Set<Integer> ids;

    //ID of the created element
    private int created;

    //IDs getter
    public Set<Integer> getIds() {
        return ids;
    }

    //ID of the created element getter
    public Integer getCreated() {
        return created;
    }


    //First constructor, without result (for the fake link)
    public Link(Set<Integer> ids){
        this.ids = ids;
    }

    //Second constructor, with the result (for the database loader)
    public Link(Set<Integer> ids, Integer created){
        this.created = created;
        this.ids = ids;
    }

    //Compares two links
    public boolean  compare(Link other){
        return ids.equals(other.getIds());
    }
}
