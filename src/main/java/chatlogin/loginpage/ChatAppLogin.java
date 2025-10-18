package chatlogin.loginpage;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A highly polished, professional, dark-themed login and registration UI for a real-time chat application.
 * This version features a split-pane layout, custom SVG icons, subtle animations, and refined styling.
 *
 * @author A Senior UI Developer (Gemini)
 * @version 2.0
 */
public class ChatAppLogin extends Application {

    // --- Style Constants for a Refined Dark Theme ---
    private static final String FONT_FAMILY = "Inter";
    private static final String PRIMARY_BACKGROUND_COLOR = "#1a1a1a";
    private static final String SECONDARY_BACKGROUND_COLOR = "#2c2c2c";
    private static final String ACCENT_COLOR_PRIMARY = "#007AFF";
    private static final String ACCENT_COLOR_SECONDARY = "#0A84FF";
    private static final String TEXT_COLOR = "#EAEAEA";
    private static final String TEXT_COLOR_MUTED = "#9E9E9E";
    private static final String ERROR_COLOR = "#FF453A";
    private static final String BORDER_COLOR = "#444444";

    private StackPane rightPane; // This will hold the different form views

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Connect | Real-Time Chat");

        HBox rootLayout = new HBox();
        VBox leftPane = createBrandingPane();
        rightPane = new StackPane();
        HBox.setHgrow(rightPane, Priority.ALWAYS);

        // --- Create Views ---
        VBox initialView = createInitialView();
        VBox signInView = createSignInView();
        VBox signUpView = createSignUpView();

        // Add views to the right pane
        rightPane.getChildren().addAll(signInView, signUpView, initialView);
        setNodeVisibility(initialView, true); // Start with the initial view

        rootLayout.getChildren().addAll(leftPane, rightPane);

        Scene scene = new Scene(rootLayout, 900, 650);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(650);
        primaryStage.show();
    }

    /**
     * Creates the left-side branding panel.
     */
    private VBox createBrandingPane() {
        VBox pane = new VBox(20);
        pane.setPrefWidth(350);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(40));

        // Use a gradient for the background
        Stop[] stops = new Stop[]{new Stop(0, Color.web("#2a2a2a")), new Stop(1, Color.web("#1a1a1a"))};
        LinearGradient lg = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        pane.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(lg, null, null)));

        SVGPath logo = new SVGPath();
        logo.setContent("M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-1 15v-4H8l4-5 1 4h3l-4 5z");
        logo.setFill(Color.web(ACCENT_COLOR_PRIMARY));
        logo.setScaleX(2.5);
        logo.setScaleY(2.5);

        Label title = new Label("Connect");
        title.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 48));
        title.setTextFill(Color.web(TEXT_COLOR));

        Text subtitle = new Text("Your seamless real-time communication hub.");
        subtitle.setFont(Font.font(FONT_FAMILY, 16));
        subtitle.setFill(Color.web(TEXT_COLOR_MUTED));
        subtitle.setWrappingWidth(250);
        subtitle.setTextAlignment(TextAlignment.CENTER);

        pane.getChildren().addAll(logo, title, subtitle);
        return pane;
    }

    /**
     * Creates the initial view with "Sign In" and "Sign Up" options.
     */
    private VBox createInitialView() {
        VBox layout = createFormContainer("Welcome Back", "Choose your path to connect with your team.");

        Button signInButton = createStyledButton("SIGN IN");
        signInButton.setOnAction(e -> switchView(getNodeByName("signInView")));

        Button signUpButton = createStyledButton("SIGN UP");
        signUpButton.setOnAction(e -> switchView(getNodeByName("signUpView")));

        VBox buttonBox = new VBox(15, signInButton, signUpButton);
        VBox.setVgrow(buttonBox, Priority.ALWAYS);
        buttonBox.setAlignment(Pos.CENTER);

        layout.getChildren().add(buttonBox);
        layout.setId("initialView");
        return layout;
    }

    /**
     * Creates the view for users to sign in.
     */
    private VBox createSignInView() {
        VBox layout = createFormContainer("Sign In", "Enter your credentials to access your account.");

        Node emailField = createIconTextField("M20 4H4c-1.1 0-1.99.9-1.99 2L2 18c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm0 4l-8 5-8-5V6l8 5 8-5v2z", "Email Address");
        Node passwordField = createIconPasswordField("M18 8h-1V6c0-2.76-2.24-5-5-5S7 3.24 7 6v2H6c-1.1 0-2 .9-2 2v10c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V10c0-1.1-.9-2-2-2zm-6 9c-1.1 0-2-.9-2-2s.9-2 2-2 2 .9 2 2-.9 2-2 2zm3.1-9H8.9V6c0-1.71 1.39-3.1 3.1-3.1s3.1 1.39 3.1 3.1v2z", "Password");
        Label errorLabel = createErrorLabel();

        Button signInButton = createStyledButton("SIGN IN");
        signInButton.setOnAction(e -> {
            // Placeholder for sign-in logic
            showSuccessMessage("Login Successful!", "Welcome back.");
        });

        HBox switchLink = createSwitchFormLink("New here? ", "Create an account", getNodeByName("signUpView"));

        VBox fieldBox = new VBox(20, emailField, passwordField, signInButton, errorLabel, switchLink);
        VBox.setVgrow(fieldBox, Priority.ALWAYS);
        fieldBox.setAlignment(Pos.CENTER_LEFT);

        layout.getChildren().add(fieldBox);
        layout.setId("signInView");
        return layout;
    }

    /**
     * Creates the view for new users to register.
     */
    private VBox createSignUpView() {
        VBox layout = createFormContainer("Create Account", "Join the conversation in a few simple steps.");

        Node usernameField = createIconTextField("M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z", "Username");
        Node emailField = createIconTextField("M20 4H4c-1.1 0-1.99.9-1.99 2L2 18c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm0 4l-8 5-8-5V6l8 5 8-5v2z", "Email Address");
        Node passwordField = createIconPasswordField("M18 8h-1V6c0-2.76-2.24-5-5-5S7 3.24 7 6v2H6c-1.1 0-2 .9-2 2v10c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V10c0-1.1-.9-2-2-2zm-6 9c-1.1 0-2-.9-2-2s.9-2 2-2 2 .9 2 2-.9 2-2 2zm3.1-9H8.9V6c0-1.71 1.39-3.1 3.1-3.1s3.1 1.39 3.1 3.1v2z", "Password");
        Label errorLabel = createErrorLabel();

        Button signUpButton = createStyledButton("CREATE ACCOUNT");
        signUpButton.setOnAction(e -> {
            // Placeholder for sign-up logic
            showSuccessMessage("Registration Complete!", "Please sign in to continue.");
        });

        HBox switchLink = createSwitchFormLink("Already have an account? ", "Sign In", getNodeByName("signInView"));

        VBox fieldBox = new VBox(20, usernameField, emailField, passwordField, signUpButton, errorLabel, switchLink);
        VBox.setVgrow(fieldBox, Priority.ALWAYS);
        fieldBox.setAlignment(Pos.CENTER_LEFT);

        layout.getChildren().add(fieldBox);
        layout.setId("signUpView");
        return layout;
    }

    // --- UI Helper & Factory Methods ---

    private VBox createFormContainer(String titleText, String subtitleText) {
        VBox container = new VBox(10);
        container.setPadding(new Insets(50, 60, 50, 60));
        container.setAlignment(Pos.TOP_LEFT);
        container.setStyle("-fx-background-color: " + PRIMARY_BACKGROUND_COLOR + ";");
        container.setVisible(false); // Initially hidden

        Label title = new Label(titleText);
        title.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 32));
        title.setTextFill(Color.web(TEXT_COLOR));

        Label subtitle = new Label(subtitleText);
        subtitle.setFont(Font.font(FONT_FAMILY, 15));
        subtitle.setTextFill(Color.web(TEXT_COLOR_MUTED));
        subtitle.setWrapText(true);

        container.getChildren().addAll(title, subtitle);
        return container;
    }

    private Node createIconTextField(String svgPath, String prompt) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        return createIconField(svgPath, textField);
    }

    private Node createIconPasswordField(String svgPath, String prompt) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(prompt);
        return createIconField(svgPath, passwordField);
    }

    private Node createIconField(String svgPath, TextField field) {
        field.setFont(Font.font(FONT_FAMILY, 14));
        field.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: " + TEXT_COLOR + ";" +
                        "-fx-prompt-text-fill: " + TEXT_COLOR_MUTED + ";" +
                        "-fx-padding: 8 0 8 0;"
        );
        HBox.setHgrow(field, Priority.ALWAYS);

        SVGPath icon = new SVGPath();
        icon.setContent(svgPath);
        icon.setFill(Color.web(TEXT_COLOR_MUTED));

        HBox fieldContainer = new HBox(10, icon, field);
        fieldContainer.setAlignment(Pos.CENTER_LEFT);
        fieldContainer.setPadding(new Insets(8, 12, 8, 12));
        fieldContainer.setStyle(
                "-fx-background-color: " + SECONDARY_BACKGROUND_COLOR + "44;" + // 44 for transparency
                        "-fx-border-color: " + BORDER_COLOR + ";" +
                        "-fx-border-width: 1.5px;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;"
        );

        field.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                icon.setFill(Color.web(ACCENT_COLOR_PRIMARY));
                fieldContainer.setStyle(fieldContainer.getStyle() + "-fx-border-color: " + ACCENT_COLOR_PRIMARY + ";");
            } else {
                icon.setFill(Color.web(TEXT_COLOR_MUTED));
                fieldContainer.setStyle(fieldContainer.getStyle().replace("-fx-border-color: " + ACCENT_COLOR_PRIMARY + ";", ""));
            }
        });

        return fieldContainer;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 15));
        button.setTextFill(Color.WHITE);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(50);
        button.setStyle(
                "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;"
        );

        Stop[] stops = new Stop[]{new Stop(0, Color.web(ACCENT_COLOR_PRIMARY)), new Stop(1, Color.web(ACCENT_COLOR_SECONDARY))};
        LinearGradient lg = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        button.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(lg, new javafx.scene.layout.CornerRadii(8), null)));

        DropShadow shadow = new DropShadow(10, Color.web(ACCENT_COLOR_PRIMARY, 0.3));
        button.setEffect(shadow);

        // Hover effect - slightly brighter
        button.setOnMouseEntered(e -> {
            Stop[] hoverStops = new Stop[]{new Stop(0, Color.web(ACCENT_COLOR_PRIMARY).brighter()), new Stop(1, Color.web(ACCENT_COLOR_SECONDARY).brighter())};
            button.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, hoverStops), new javafx.scene.layout.CornerRadii(8), null)));
        });
        button.setOnMouseExited(e -> {
            button.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(lg, new javafx.scene.layout.CornerRadii(8), null)));
        });

        return button;
    }

    private Label createErrorLabel() {
        Label label = new Label();
        label.setTextFill(Color.web(ERROR_COLOR));
        label.setFont(Font.font(FONT_FAMILY, FontWeight.NORMAL, 13));
        label.setVisible(false);
        return label;
    }

    private HBox createSwitchFormLink(String text, String linkText, Node targetView) {
        Label plainText = new Label(text);
        plainText.setFont(Font.font(FONT_FAMILY, 14));
        plainText.setTextFill(Color.web(TEXT_COLOR_MUTED));

        Label link = new Label(linkText);
        link.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 14));
        link.setTextFill(Color.web(ACCENT_COLOR_PRIMARY));
        link.setStyle("-fx-cursor: hand;");
        link.setOnMouseClicked(e -> switchView(targetView));

        HBox hbox = new HBox(5, plainText, link);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    private void showSuccessMessage(String titleText, String contentText) {
        // Simple success popup logic, can be replaced with a more complex dialog
        System.out.println(titleText + " " + contentText);
        switchView(getNodeByName("signInView"));
    }

    // --- View Switching & Animation Logic ---

    private void switchView(Node viewToShow) {
        Node currentView = null;
        for (Node n : rightPane.getChildren()) {
            if (n.isVisible()) {
                currentView = n;
                break;
            }
        }

        if (currentView != null) {
            final Node viewToHide = currentView;
            FadeTransition ftOut = new FadeTransition(Duration.millis(250), viewToHide);
            ftOut.setFromValue(1.0);
            ftOut.setToValue(0.0);
            ftOut.setOnFinished(e -> {
                viewToHide.setVisible(false);
                FadeTransition ftIn = new FadeTransition(Duration.millis(250), viewToShow);
                ftIn.setFromValue(0.0);
                ftIn.setToValue(1.0);
                viewToShow.setVisible(true);
                ftIn.play();
            });
            ftOut.play();
        } else {
            viewToShow.setVisible(true); // For initial show
        }
    }

    private void setNodeVisibility(Node target, boolean isVisible) {
        rightPane.getChildren().forEach(node -> node.setVisible(false));
        target.setVisible(isVisible);
    }

    private Node getNodeByName(String name) {
        return rightPane.getChildren().stream().filter(n -> name.equals(n.getId())).findFirst().orElse(null);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
