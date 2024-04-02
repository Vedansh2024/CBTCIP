import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        final int MAX_NUMBER = 100;
        final int MIN_NUMBER = 1;
        
        Random random = new Random();
        int secretNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;

        System.out.println("Welcome to Guess the Number Game!");
        System.out.println("I've picked a number between 1 and 100. Can you guess it?");

        while (true) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess < secretNumber) {
                System.out.println("Too low, try again.");
            } else if (guess > secretNumber) {
                System.out.println("Too high, try again.");
            } else {
                System.out.println("Congratulations! You've guessed the number " + secretNumber + " in " + attempts + " attempts.");
                break;
            }
        }

        scanner.close();
    }
}