package project_tracker.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project_tracker.application.domain.UserModel;
import project_tracker.application.utilities.BackendConnectors;

public class RegisterController {

    @FXML
    public javafx.scene.control.Label titleLabel;

    @FXML
    public javafx.scene.control.TextField usernameField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public Button registerButton;

    @FXML
    public javafx.scene.control.Button backButton;

    @FXML
    public TextField emailField;

    @FXML
    public void initialize() {
        titleLabel.setText("Register");
    }

    @FXML
    public void handleRegister(ActionEvent actionEvent) {
        // Create a User object with the input data
        UserModel user = new UserModel(usernameField.getText(), passwordField.getText(), emailField.getText());

        // Validate the user data (add your validation logic here)
        if (validateUser(user)) {
            // Send the user data to the backend
            BackendConnectors.postRegister(user);
        } else {
            // Handle validation errors (e.g., show an error message)
            System.out.println("Validation failed");
        }
    }

    private boolean validateUser(UserModel user) {
        // Add your validation logic here (e.g., check for empty fields, valid email format, etc.)
        return !user.getUsername().isEmpty() && !user.getPassword().isEmpty() && !user.getEmail().isEmpty();
    }

    @FXML
    public void handleBack(ActionEvent actionEvent) {

        // Close the current window
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();

    }
}
