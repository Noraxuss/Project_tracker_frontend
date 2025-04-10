package project_tracker.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.Setter;
import project_tracker.application.domain.LoginModel;
import project_tracker.application.scene.SceneEngine;
import project_tracker.application.utilities.BackendConnectors;
import project_tracker.application.utilities.SceneEngineAware;

public class LoginController implements SceneEngineAware {

    private SceneEngine sceneEngine;

    @FXML
    public Label systemResponseLabel;

    @FXML
    public Label titleLabel;

    @FXML
    public TextField usernameField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public Button loginButton;

    @FXML
    public Button RegisterButton;

    @FXML
    public void initialize() {
        titleLabel.setText("Login");
    }

    @FXML
    public void handleLogin(ActionEvent actionEvent) {
        String response = BackendConnectors.getLogin
                (new LoginModel(usernameField.getText(), passwordField.getText()));

        if (response.equals("Login successful")) {
            // Handle successful login (e.g., switch to the main menu scene)
            sceneEngine.switchScene("menu");
        } else {
            // Handle login failure (e.g., show an error message)
            System.out.println("Login failed: " + response);
            // Optionally, you can update a label to show the error message
            systemResponseLabel.setText("Invalid username or password");
        }
    }

    @FXML
    public void handleRegister(ActionEvent actionEvent) {
        sceneEngine.switchScene("register");
    }

    @Override
    public void setSceneEngine(SceneEngine engine) {
        this.sceneEngine = engine;
    }
}

