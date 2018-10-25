public class MyElement extends TerminalExpression {
    private String name;
    private String id;

    public MyElement(String name, String id){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public String getID(){
        return this.id;
    }
    public void setID(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public AbstractExpression interpret(){
        return this;
    }
}
