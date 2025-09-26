import java.util.Scanner;

public class rawCode {
    public static void guessingNumberGame() {
        Scanner myScanner = new Scanner(System.in);

        int number = 1 + (int) (100 * Math.random()); // random number between 1 and 100
        int K = 10; // attempts

        System.out.println("A number is between 1 & 100");

        System.out.println("You have " + K + " attempts to guess the correct number.");

        for (int i = 0; i < K; i++) {
            System.out.print("Enter your guess: ");
            int guess = myScanner.nextInt();

            if (guess == number) {
                System.out.println(" Congratulations! You guessed the correct number.");
                break; // game over
            } else if (guess < number) {
                System.out.println("The number is greater than " + guess);
            } else {
                System.out.println("The number is less than " + guess);
            }

            if (i == K - 1) {
                System.out.println("You've exhausted all attempts. The correct number was: " + number);
            }
        }

        myScanner.close(); // close only once
    }

    public static void main(String[] args) {
        guessingNumberGame();
    }
}