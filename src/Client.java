import java.util.Scanner;

public class Client {

    public static void main (String[] args) {

        //Here implement load database
        MyApp.Load();

        //Here display the known elements
        /*
        for (MyElement elem: MyApp.getKnownElements()) {
            System.out.print(elem.getName());
        }
        */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Elements to merge: ");
        String s = scanner.next();
        s += scanner.nextLine();
        String[] parsed = parser(s);

        Context c = new Context(parsed);
    }

    private static String[] parser(String input) {
        return input.split(" ");
    }
}