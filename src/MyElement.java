public class MyElement extends TerminalExpression {
    private String name;
    private int id;

    public MyElement(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public int getID(){
        return this.id;
    }
    public void setID(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public AbstractExpression interpret(){
        return this;
    }

    @Override
    public String toString() {
        return name;
    }
}
