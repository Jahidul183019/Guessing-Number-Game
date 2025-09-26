import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    private int number;
    private int attemptsLeft;
    private final int MAX_ATTEMPTS = 10;

    @Override
    public void start(Stage primaryStage) {
        // Initialize game
        number = 1 + new Random().nextInt(100);
        attemptsLeft = MAX_ATTEMPTS;

        // Game Title
        Label titleLabel = new Label("🎯 Guess The Number Game 🎯");
        titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        titleLabel.setTextFill(Color.DARKBLUE);

        // Instruction Label
        Label instructionLabel = new Label("Guess a number between 1 and 100");
        instructionLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        instructionLabel.setTextFill(Color.DARKGREEN);

        // Attempts Label
        Label attemptsLabel = new Label("Attempts left: " + attemptsLeft);
        attemptsLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        attemptsLabel.setTextFill(Color.DARKRED);

        // Feedback Label
        Label feedbackLabel = new Label();
        feedbackLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        feedbackLabel.setTextFill(Color.DARKVIOLET);

        // Input Field
        TextField guessInput = new TextField();
        guessInput.setPromptText("Enter your guess");
        guessInput.setMaxWidth(250);
        guessInput.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Guess Button
        Button guessButton = new Button("Guess");
        guessButton.setPrefWidth(120);
        guessButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        guessButton.setStyle("-fx-background-color: linear-gradient(#4CAF50, #2E7D32); -fx-text-fill: white;");

        // Layout
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: linear-gradient(to bottom right, #f0f8ff, #add8e6);"); // Gradient background
        layout.getChildren().addAll(titleLabel, instructionLabel, attemptsLabel, guessInput, guessButton, feedbackLabel);

        // Button action
        guessButton.setOnAction(e -> {
            String guessText = guessInput.getText();
            if (guessText.isEmpty()) {
                feedbackLabel.setText("⚠️ Please enter a number.");
                return;
            }

            try {
                int guess = Integer.parseInt(guessText);

                attemptsLeft--;
                attemptsLabel.setText("Attempts left: " + attemptsLeft);

                if (guess == number) {
                    feedbackLabel.setText("🎉 Congratulations! You guessed it!");
                    feedbackLabel.setTextFill(Color.GREEN);
                    guessButton.setDisable(true);
                } else if (guess < number) {
                    feedbackLabel.setText("⬆ The number is greater than " + guess);
                } else {
                    feedbackLabel.setText("⬇ The number is less than " + guess);
                }

                if (attemptsLeft == 0 && guess != number) {
                    feedbackLabel.setText("❌ Out of attempts! The number was: " + number);
                    feedbackLabel.setTextFill(Color.RED);
                    guessButton.setDisable(true);
                }

            } catch (NumberFormatException ex) {
                feedbackLabel.setText("⚠️ Please enter a valid integer.");
                feedbackLabel.setTextFill(Color.RED);
            }

            guessInput.clear();
        });

        // Scene setup
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setTitle("Guess The Number Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
