import java.util.Scanner;

public class Menu {

    // Initializing scanner class.
    Scanner userInput = new Scanner(System.in).useDelimiter("\n");

    // Menu method.
    public String reviewMenu() {

        // Initializing choice String variable.
        String choice = "";

        // Loop block for case of incorrect entry.
        while (true) {
            System.out.print(
                    "\n1) Review Product" +
                            "\n2) See my reviews" +
                            "\n3) See product review score" +
                            "\n4) Exit"
            );

            System.out.print("\nChoose menu number: ");
            String userChoice = userInput.next();  // User menu choice.

            if (userChoice.equals("1") || userChoice.equals("2") || userChoice.equals("3") ||
            userChoice.equals("4")) {

                choice = userChoice;
                break;

            }else {
                System.out.print("\nYou have not entered a valid menu option.");
            }
        }

        // User choice returned as String.
        return choice;
    }
}
