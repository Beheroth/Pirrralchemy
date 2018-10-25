import java.util.Set;

public class Link {

    public Set<Integer> getIds() {
        return ids;
    }

    private Set<Integer> ids;

    public int getCreated() {
        return created;
    }

    private int created;

    public Link(Set<Integer> ids){
        this.ids = ids;
    }

    public Link(Set<Integer> ids, int created){
        this.created = created;
        this.ids = ids;
    }

    public boolean  compare(Link other){
        return ids.equals(other.getIds());
    }
}
