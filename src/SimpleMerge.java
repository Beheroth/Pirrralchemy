public class SimpleMerge extends NonTerminalExpression{
    private MyElement firstelem;
    private MyElement secondelem;

    public SimpleMerge(MyElement firstelem, MyElement secondelem){
        this.firstelem = firstelem;
        this.secondelem = secondelem;
    }

    @Override
    public AbstractExpression interpret(){
        return new MyElement( "Hello",  "World");
    }

    private String generateLinkAlias(){
        return firstelem.getID() + secondelem.getID();
    }
}
