package Game.Game1;

import java.util.Scanner;

public class GameMain {
    public static void main(String[] args) {
        do {
            Game1 game1 = new Game1();
            game1.PlayGame();
            System.out.println("Enter Play Game ! (Y/N): ");
            Scanner sc = new Scanner(System.in);
            String sum = new Scanner(System.in).nextLine();
            if (sum.equalsIgnoreCase("N"))
                break;
        }while (true);
        System.out.println("Good Bye! Sea you again!!!");
    }
}
