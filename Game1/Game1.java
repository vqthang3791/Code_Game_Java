package Game.Game1;

import java.util.Random;
import java.util.Scanner;

public class Game1 {
    public static void PlayGame() {
        Random rd = new Random();
        int Integer = rd.nextInt(11);
        int Count = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("The machine has selected a number between 0 and 10");
        System.out.println("Choose the correct number");
        while (true) {
            System.out.println("Enter Number: ");
            int IntegerUser = sc.nextInt();
            Count++;
            System.out.println("Number of times you have entered: " + Count);

            if (Integer == IntegerUser) {
                System.out.println("You are victory! You have chosen the correct number: " + Integer);
                break;
            } else {
                if (Integer < IntegerUser) {
                    System.out.println("You are lose! You have entered the smaller number");
                } else {
                    System.out.println("You are lose! You have entered the larger number");
                }
            }
            if (Count >= 5) {
                System.out.println("You are lose! Ban het luot choi");
                System.out.println("Number is: " + Integer);
                break;
            }
        }
    }
}
