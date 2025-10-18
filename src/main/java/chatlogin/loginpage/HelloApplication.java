package chatlogin.loginpage;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

/**
 * Premium dark-themed authentication system with Sign In/Sign Up flow
 */
public class HelloApplication extends Application {

    // --- Premium Dark Theme CSS ---
    private static final String APP_CSS = """
        .root {
            -fx-background-color: linear-gradient(135deg, #0f2027 0%, #203a43 50%, #2c5364 100%);
            -fx-padding: 20;
        }
        
        .auth-card {
            -fx-background-color: rgba(30, 41, 59, 0.85);
            -fx-padding: 50;
            -fx-background-radius: 20;
            -fx-border-color: rgba(71, 85, 105, 0.5);
            -fx-border-width: 1;
            -fx-border-radius: 20;
            -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 50, 0.7, 0, 20);
        }
        
        .title-label {
            -fx-font-size: 32px;
            -fx-font-weight: bold;
            -fx-text-fill: #e2e8f0;
            -fx-padding-bottom: 8px;
        }
        
        .subtitle-label {
            -fx-font-size: 14px;
            -fx-text-fill: #94a3b8;
            -fx-padding-bottom: 30px;
        }
        
        .label {
            -fx-font-size: 12px;
            -fx-text-fill: #cbd5e0;
            -fx-font-weight: 600;
            -fx-padding-bottom: 8px;
        }
        
        .text-field, .password-field {
            -fx-pref-height: 48px;
            -fx-font-size: 14px;
            -fx-background-radius: 10;
            -fx-background-color: rgba(51, 65, 85, 0.6);
            -fx-border-color: rgba(71, 85, 105, 0.8);
            -fx-border-width: 1px;
            -fx-border-radius: 10;
            -fx-padding: 0 16 0 16;
            -fx-text-fill: #e2e8f0;
            -fx-prompt-text-fill: #64748b;
        }
        
        .text-field:focused, .password-field:focused {
            -fx-border-color: #3b82f6;
            -fx-background-color: rgba(51, 65, 85, 0.8);
            -fx-effect: dropshadow(gaussian, rgba(59, 130, 246, 0.4), 12, 0.6, 0, 0);
        }
        
        .primary-button {
            -fx-background-color: linear-gradient(to right, #3b82f6, #2563eb);
            -fx-text-fill: white;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-padding: 14 20;
            -fx-background-radius: 10;
            -fx-cursor: hand;
        }
        
        .primary-button:hover {
            -fx-background-color: linear-gradient(to right, #2563eb, #1d4ed8);
            -fx-effect: dropshadow(gaussian, rgba(59, 130, 246, 0.5), 20, 0.8, 0, 5);
            -fx-scale-y: 1.02;
            -fx-scale-x: 1.02;
        }
        
        .secondary-button {
            -fx-background-color: transparent;
            -fx-text-fill: #94a3b8;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-padding: 14 20;
            -fx-background-radius: 10;
            -fx-border-color: rgba(71, 85, 105, 0.8);
            -fx-border-width: 1px;
            -fx-border-radius: 10;
            -fx-cursor: hand;
        }
        
        .secondary-button:hover {
            -fx-background-color: rgba(71, 85, 105, 0.3);
            -fx-text-fill: #e2e8f0;
            -fx-border-color: #64748b;
        }
        
        .link-button {
            -fx-background-color: transparent;
            -fx-text-fill: #3b82f6;
            -fx-font-size: 13px;
            -fx-cursor: hand;
            -fx-padding: 5;
        }
        
        .link-button:hover {
            -fx-text-fill: #60a5fa;
            -fx-underline: true;
        }
        
        .message-label {
            -fx-font-size: 13px;
            -fx-padding-top: 12px;
            -fx-font-weight: 600;
        }
        
        .avatar-circle {
            -fx-fill: linear-gradient(to bottom right, #3b82f6, #8b5cf6);
        }
        
        .divider-text {
            -fx-text-fill: #64748b;
            -fx-font-size: 12px;
        }
        
        .check-box {
            -fx-text-fill: #cbd5e0;
            -fx-font-size: 13px;
        }
        
        .check-box .box {
            -fx-background-color: rgba(51, 65, 85, 0.6);
            -fx-border-color: rgba(71, 85, 105, 0.8);
            -fx-border-radius: 5;
            -fx-border-width: 1;
        }
        
        .check-box:selected .box {
            -fx-background-color: #3b82f6;
            -fx-border-color: #3b82f6;
        }
        
        .check-box:selected .mark {
            -fx-background-color: white;
        }
        """;

    // User database (mock)
    private Map<String, UserData> userDatabase = new HashMap<>();

    // UI Components
    private Stage primaryStage;
    private VBox root;
    private VBox cardContainer;

    static class UserData {
        String username;
        String email;
        String password;

        UserData(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;

        // Add demo user
        userDatabase.put("user@example.com", new UserData("user", "user@example.com", "password"));

        // Setup root
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("root");

        cardContainer = new VBox();
        cardContainer.setAlignment(Pos.CENTER);
        root.getChildren().add(cardContainer);

        // Show welcome screen
        showWelcomeScreen();

        Scene scene = new Scene(root, 700, 750);
        scene.getStylesheets().add("data:text/css;base64," +
                java.util.Base64.getEncoder().encodeToString(APP_CSS.getBytes()));

        primaryStage.setTitle("Real-Time Chat Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Welcome screen with Sign In / Sign Up options
     */
    private void showWelcomeScreen() {
        VBox card = new VBox(20);
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("auth-card");
        card.setPrefWidth(420);

        // Avatar
        Circle avatar = new Circle(40);
        avatar.getStyleClass().add("avatar-circle");
        StackPane avatarPane = new StackPane(avatar);

        // Title
        Label title = new Label("Welcome back");
        title.getStyleClass().add("title-label");

        Label subtitle = new Label("Sign in to your account to continue");
        subtitle.getStyleClass().add("subtitle-label");

        // Buttons
        Button signInBtn = new Button("Sign In");
        signInBtn.getStyleClass().add("primary-button");
        signInBtn.setPrefWidth(320);
        signInBtn.setPrefHeight(50);
        signInBtn.setOnAction(e -> transitionTo(createSignInScreen()));

        Button signUpBtn = new Button("Create Account");
        signUpBtn.getStyleClass().add("secondary-button");
        signUpBtn.setPrefWidth(320);
        signUpBtn.setPrefHeight(50);
        signUpBtn.setOnAction(e -> transitionTo(createSignUpScreen()));

        card.getChildren().addAll(avatarPane, title, subtitle, signInBtn, signUpBtn);

        cardContainer.getChildren().clear();
        cardContainer.getChildren().add(card);
        animateCardEntrance(card);
    }

    /**
     * Sign In screen
     */
    private VBox createSignInScreen() {
        VBox card = new VBox(15);
        card.setAlignment(Pos.TOP_CENTER);
        card.getStyleClass().add("auth-card");
        card.setPrefWidth(420);

        // Header
        Label title = new Label("Sign In");
        title.getStyleClass().add("title-label");

        Label subtitle = new Label("Enter your credentials to continue");
        subtitle.getStyleClass().add("subtitle-label");

        // Email field
        VBox emailBox = new VBox(8);
        Label emailLabel = new Label("EMAIL ADDRESS");
        emailLabel.getStyleClass().add("label");
        TextField emailField = new TextField();
        emailField.setPromptText("name@example.com");
        emailField.getStyleClass().add("text-field");
        emailField.setPrefWidth(320);
        emailBox.getChildren().addAll(emailLabel, emailField);

        // Password field
        VBox passwordBox = new VBox(8);
        Label passwordLabel = new Label("PASSWORD");
        passwordLabel.getStyleClass().add("label");

        HBox passwordContainer = new HBox(5);
        passwordContainer.setAlignment(Pos.CENTER_LEFT);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.getStyleClass().add("password-field");
        passwordField.setPrefWidth(280);

        TextField passwordVisible = new TextField();
        passwordVisible.setPromptText("Enter your password");
        passwordVisible.getStyleClass().add("text-field");
        passwordVisible.setPrefWidth(280);
        passwordVisible.setVisible(false);
        passwordVisible.setManaged(false);
        passwordField.textProperty().bindBidirectional(passwordVisible.textProperty());

        Button toggleBtn = new Button("👁");
        toggleBtn.getStyleClass().add("link-button");
        toggleBtn.setOnAction(e -> {
            boolean visible = !passwordVisible.isVisible();
            passwordVisible.setVisible(visible);
            passwordVisible.setManaged(visible);
            passwordField.setVisible(!visible);
            passwordField.setManaged(!visible);
            toggleBtn.setText(visible ? "🙈" : "👁");
        });

        StackPane passwordStack = new StackPane(passwordField, passwordVisible);
        passwordStack.setPrefWidth(280);
        passwordContainer.getChildren().addAll(passwordStack, toggleBtn);
        passwordBox.getChildren().addAll(passwordLabel, passwordContainer);

        // Remember me
        CheckBox rememberMe = new CheckBox("Remember me");
        rememberMe.getStyleClass().add("check-box");

        // Message label
        Label messageLabel = new Label();
        messageLabel.getStyleClass().add("message-label");
        messageLabel.setMaxWidth(320);
        messageLabel.setWrapText(true);
        messageLabel.setAlignment(Pos.CENTER);

        // Sign in button
        Button signInBtn = new Button("Sign In");
        signInBtn.getStyleClass().add("primary-button");
        signInBtn.setPrefWidth(320);
        signInBtn.setPrefHeight(50);
        signInBtn.setOnAction(e -> handleSignIn(emailField.getText(), passwordField.getText(),
                messageLabel, card, rememberMe.isSelected()));

        // Back to welcome
        HBox footer = new HBox(5);
        footer.setAlignment(Pos.CENTER);
        Label footerText = new Label("Don't have an account?");
        footerText.setStyle("-fx-text-fill: #94a3b8; -fx-font-size: 13px;");
        Button signUpLink = new Button("Sign Up");
        signUpLink.getStyleClass().add("link-button");
        signUpLink.setOnAction(e -> transitionTo(createSignUpScreen()));
        footer.getChildren().addAll(footerText, signUpLink);

        VBox.setMargin(emailBox, new Insets(10, 0, 0, 0));
        VBox.setMargin(signInBtn, new Insets(10, 0, 0, 0));

        card.getChildren().addAll(title, subtitle, emailBox, passwordBox, rememberMe,
                signInBtn, messageLabel, footer);

        return card;
    }

    /**
     * Sign Up screen
     */
    private VBox createSignUpScreen() {
        VBox card = new VBox(15);
        card.setAlignment(Pos.TOP_CENTER);
        card.getStyleClass().add("auth-card");
        card.setPrefWidth(420);

        // Header
        Label title = new Label("Create Account");
        title.getStyleClass().add("title-label");

        Label subtitle = new Label("Sign up to get started");
        subtitle.getStyleClass().add("subtitle-label");

        // Username field
        VBox usernameBox = new VBox(8);
        Label usernameLabel = new Label("USERNAME");
        usernameLabel.getStyleClass().add("label");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Choose a username");
        usernameField.getStyleClass().add("text-field");
        usernameField.setPrefWidth(320);
        usernameBox.getChildren().addAll(usernameLabel, usernameField);

        // Email field
        VBox emailBox = new VBox(8);
        Label emailLabel = new Label("EMAIL ADDRESS");
        emailLabel.getStyleClass().add("label");
        TextField emailField = new TextField();
        emailField.setPromptText("name@example.com");
        emailField.getStyleClass().add("text-field");
        emailField.setPrefWidth(320);
        emailBox.getChildren().addAll(emailLabel, emailField);

        // Password field
        VBox passwordBox = new VBox(8);
        Label passwordLabel = new Label("PASSWORD");
        passwordLabel.getStyleClass().add("label");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Create a password");
        passwordField.getStyleClass().add("password-field");
        passwordField.setPrefWidth(320);
        passwordBox.getChildren().addAll(passwordLabel, passwordField);

        // Confirm Password field
        VBox confirmBox = new VBox(8);
        Label confirmLabel = new Label("CONFIRM PASSWORD");
        confirmLabel.getStyleClass().add("label");
        PasswordField confirmField = new PasswordField();
        confirmField.setPromptText("Confirm your password");
        confirmField.getStyleClass().add("password-field");
        confirmField.setPrefWidth(320);
        confirmBox.getChildren().addAll(confirmLabel, confirmField);

        // Message label
        Label messageLabel = new Label();
        messageLabel.getStyleClass().add("message-label");
        messageLabel.setMaxWidth(320);
        messageLabel.setWrapText(true);
        messageLabel.setAlignment(Pos.CENTER);

        // Sign up button
        Button signUpBtn = new Button("Create Account");
        signUpBtn.getStyleClass().add("primary-button");
        signUpBtn.setPrefWidth(320);
        signUpBtn.setPrefHeight(50);
        signUpBtn.setOnAction(e -> handleSignUp(usernameField.getText(), emailField.getText(),
                passwordField.getText(), confirmField.getText(),
                messageLabel, card));

        // Back to welcome
        HBox footer = new HBox(5);
        footer.setAlignment(Pos.CENTER);
        Label footerText = new Label("Already have an account?");
        footerText.setStyle("-fx-text-fill: #94a3b8; -fx-font-size: 13px;");
        Button signInLink = new Button("Sign In");
        signInLink.getStyleClass().add("link-button");
        signInLink.setOnAction(e -> transitionTo(createSignInScreen()));
        footer.getChildren().addAll(footerText, signInLink);

        VBox.setMargin(usernameBox, new Insets(10, 0, 0, 0));
        VBox.setMargin(signUpBtn, new Insets(10, 0, 0, 0));

        card.getChildren().addAll(title, subtitle, usernameBox, emailBox, passwordBox,
                confirmBox, signUpBtn, messageLabel, footer);

        return card;
    }

    /**
     * Handle sign in
     */
    private void handleSignIn(String email, String password, Label messageLabel,
                              VBox card, boolean rememberMe) {
        if (email.isEmpty() || password.isEmpty()) {
            showError(messageLabel, "Please fill in all fields", card);
            return;
        }

        UserData user = userDatabase.get(email.toLowerCase());
        if (user != null && user.password.equals(password)) {
            showSuccess(messageLabel, "✓ Welcome back, " + user.username + "!", card);

            if (rememberMe) {
                System.out.println("Remember me enabled for: " + email);
            }

            // Fade out and launch chat
            FadeTransition fade = new FadeTransition(Duration.millis(500), card);
            fade.setToValue(0);
            fade.setOnFinished(e -> {
                System.out.println("Launching chat for: " + user.username);
                // new MainChatApp(user.username).start(new Stage());
                primaryStage.close();
            });
            fade.play();
        } else {
            showError(messageLabel, "✗ Invalid email or password", card);
        }
    }

    /**
     * Handle sign up
     */
    private void handleSignUp(String username, String email, String password,
                              String confirmPassword, Label messageLabel, VBox card) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showError(messageLabel, "Please fill in all fields", card);
            return;
        }

        if (!password.equals(confirmPassword)) {
            showError(messageLabel, "Passwords do not match", card);
            return;
        }

        if (password.length() < 6) {
            showError(messageLabel, "Password must be at least 6 characters", card);
            return;
        }

        if (userDatabase.containsKey(email.toLowerCase())) {
            showError(messageLabel, "Email already registered", card);
            return;
        }

        // Create account
        userDatabase.put(email.toLowerCase(), new UserData(username, email, password));
        showSuccess(messageLabel, "✓ Account created! Redirecting to sign in...", card);

        // Transition to sign in after delay
        PauseTransition pause = new PauseTransition(Duration.millis(1500));
        pause.setOnFinished(e -> transitionTo(createSignInScreen()));
        pause.play();
    }

    /**
     * Show error message with shake animation
     */
    private void showError(Label label, String message, VBox card) {
        label.setTextFill(Color.web("#ef4444"));
        label.setText(message);
        shakeCard(card);
    }

    /**
     * Show success message
     */
    private void showSuccess(Label label, String message, VBox card) {
        label.setTextFill(Color.web("#10b981"));
        label.setText(message);
    }

    /**
     * Transition between screens
     */
    private void transitionTo(VBox newCard) {
        if (cardContainer.getChildren().isEmpty()) {
            cardContainer.getChildren().add(newCard);
            animateCardEntrance(newCard);
            return;
        }

        VBox oldCard = (VBox) cardContainer.getChildren().get(0);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(200), oldCard);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> {
            cardContainer.getChildren().clear();
            cardContainer.getChildren().add(newCard);
            animateCardEntrance(newCard);
        });
        fadeOut.play();
    }

    /**
     * Animate card entrance
     */
    private void animateCardEntrance(VBox card) {
        card.setScaleX(0.9);
        card.setScaleY(0.9);
        card.setOpacity(0);

        ScaleTransition scale = new ScaleTransition(Duration.millis(300), card);
        scale.setToX(1.0);
        scale.setToY(1.0);

        FadeTransition fade = new FadeTransition(Duration.millis(300), card);
        fade.setToValue(1.0);

        ParallelTransition transition = new ParallelTransition(scale, fade);
        transition.play();
    }

    /**
     * Shake animation for errors
     */
    private void shakeCard(VBox card) {
        TranslateTransition shake = new TranslateTransition(Duration.millis(50), card);
        shake.setFromX(0);
        shake.setByX(8);
        shake.setCycleCount(6);
        shake.setAutoReverse(true);
        shake.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
