
//Parts of the design pattern interpreter
public class MyElement extends TerminalExpression {

    //name of the element
    private String name;

    //id og the element
    private Integer id;

    //constructor
    public MyElement(String name, int id){
        this.name = name;
        this.id = id;
    }

    //Name getter
    public String getName(){
        return this.name;
    }

    //ID getter
    public Integer getID(){
        return this.id;
    }

    @Override
    public AbstractExpression interpret(){
        return this;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean compare(MyElement element){
        return (element.getID().equals(this.getID()) & element.getName().equals(this.getName()));
    }
}
