import java.util.Scanner;
import java.util.Set;

public class Client {

    public static void main (String[] args) {

        //Here implement load database
        MyApp.load();

        //Here display the known elements
        /*
        for (MyElement elem: MyApp.getKnownElements()) {
            System.out.print(elem.getName());
        }
        */
        String s = "";
        while (!s.equals("stop")) {
            System.out.println(beautifullDislplayElements(MyApp.getKnownElements()));
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Elements to merge: ");
            s = scanner.next();
            s += scanner.nextLine();
            String[] parsed = parser(s);
            Context c = new Context(parsed);
        }
    }

    private static String[] parser(String input) {
        return input.split(" ");
    }

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