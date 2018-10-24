import java.util.Scanner;

public class Client {

    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Elements to merge: ");
        String s = scanner.next();
        s += scanner.nextLine();
        String[] parsed = parser(s);

        for (String word : parsed) {
            System.out.println(word);
        }
    }

    private static String[] parser(String input) {
        return input.split(" ");
    }

}