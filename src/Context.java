public class Context {

    private String[] words;

    public Context(String[] words){
        this.words = words;
        create();
    }

    private void create(){

        for (String word : this.words) {
            System.out.println(word);
        }
    }
}
