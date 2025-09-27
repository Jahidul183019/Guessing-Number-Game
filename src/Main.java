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

    private Label attemptsLabel;
    private Label feedbackLabel;
    private TextField guessInput;
    private Button guessButton;
    private Button restartButton;

    @Override
    public void start(Stage primaryStage) {
        initializeGame();

        // Game Title
        Label titleLabel = new Label("🎯 Guess The Number Game 🎯");
        titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        titleLabel.setTextFill(Color.DARKBLUE);

        // Instruction Label
        Label instructionLabel = new Label("Guess a number between 1 and 100");
        instructionLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        instructionLabel.setTextFill(Color.DARKGREEN);

        // Attempts Label
        attemptsLabel = new Label("Attempts left: " + attemptsLeft);
        attemptsLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        attemptsLabel.setTextFill(Color.DARKRED);

        // Feedback Label
        feedbackLabel = new Label();
        feedbackLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        feedbackLabel.setTextFill(Color.DARKVIOLET);

        // Input Field
        guessInput = new TextField();
        guessInput.setPromptText("Enter your guess");
        guessInput.setMaxWidth(250);
        guessInput.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Guess Button
        guessButton = new Button("Guess");
        guessButton.setPrefWidth(120);
        guessButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        guessButton.setStyle("-fx-background-color: linear-gradient(#4CAF50, #2E7D32); -fx-text-fill: white;");

        // Restart Button
        restartButton = new Button("Restart Game");
        restartButton.setPrefWidth(150);
        restartButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        restartButton.setStyle("-fx-background-color: linear-gradient(#2196F3, #1565C0); -fx-text-fill: white;");
        restartButton.setVisible(false); // Hidden initially

        // Layout
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: linear-gradient(to bottom right, #f0f8ff, #add8e6);");
        layout.getChildren().addAll(titleLabel, instructionLabel, attemptsLabel, guessInput, guessButton, feedbackLabel, restartButton);

        // Guess Button action
        guessButton.setOnAction(e -> handleGuess());

        // Restart Button action
        restartButton.setOnAction(e -> restartGame());

        // Scene setup
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setTitle("Guess The Number Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeGame() {
        number = 1 + new Random().nextInt(100);
        attemptsLeft = MAX_ATTEMPTS;
    }

    private void handleGuess() {
        String guessText = guessInput.getText();

        if (guessText.isEmpty()) {
            feedbackLabel.setText("⚠️ Please enter a number.");
            feedbackLabel.setTextFill(Color.RED);
            return;
        }

        try {
            int guess = Integer.parseInt(guessText);

            // Range check
            if (guess < 1 || guess > 100) {
                feedbackLabel.setText("⚠️ Number must be between 1 and 100.");
                feedbackLabel.setTextFill(Color.RED);
                guessInput.clear();
                return;
            }

            attemptsLeft--;
            attemptsLabel.setText("Attempts left: " + attemptsLeft);

            if (guess == number) {
                feedbackLabel.setText("🎉 Congratulations! You guessed it!");
                feedbackLabel.setTextFill(Color.GREEN);
                endGame();
            } else if (guess < number) {
                feedbackLabel.setText("⬆ The number is greater than " + guess);
                feedbackLabel.setTextFill(Color.DARKVIOLET);
            } else {
                feedbackLabel.setText("⬇ The number is less than " + guess);
                feedbackLabel.setTextFill(Color.DARKVIOLET);
            }

            if (attemptsLeft == 0 && guess != number) {
                feedbackLabel.setText("❌ Out of attempts! The number was: " + number);
                feedbackLabel.setTextFill(Color.RED);
                endGame();
            }

        } catch (NumberFormatException ex) {
            feedbackLabel.setText("⚠️ Please enter a valid integer.");
            feedbackLabel.setTextFill(Color.RED);
        }

        guessInput.clear();
    }

    private void endGame() {
        guessButton.setDisable(true);
        guessInput.setDisable(true);
        restartButton.setVisible(true);
    }

    private void restartGame() {
        initializeGame();
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        feedbackLabel.setText("");
        guessInput.clear();
        guessInput.setDisable(false);
        guessButton.setDisable(false);
        restartButton.setVisible(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
