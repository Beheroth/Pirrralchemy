import java.util.Scanner;
import java.util.Set;

//First class called
//Reads the input and send a parsed sentence to the Context class
public class Client {

    //Starting point of the application
    public static void main (String[] args) {
        MyApp.load();
        clientControl();
    }

    //Reads and executes command line control
    private static void clientControl(){
        String s = "";
        while (!s.equals("stop")) {
            System.out.println(beautifullDislplayElements(MyApp.getKnownElements()));
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Elements to merge: ");
            s = scanner.next();
            s += scanner.nextLine();
            String[] parsed = parser(s);
            Context c = new Context(parsed);
            c.create();
        }
    }

    //Cuts the sentence into words
    private static String[] parser(String input) {
        return input.split(" ");
    }

    //Returns a string for a nice display of the elements
    private static String beautifullDislplayElements(Set<MyElement> elems){
        String result = "Known elements : \n";
        int i = 0;
        for (MyElement elem: elems) {
            i++;
            result += " " + elem.toString();
            if (i%2 == 0 && i < elems.size()){
                result += "\n";
            }
        }
        return result+"\n";
    }
}