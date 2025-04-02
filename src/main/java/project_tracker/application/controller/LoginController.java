package project_tracker.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project_tracker.application.scene.RegisterScene;

public class LoginController {

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
    }

    @FXML
    public void handleRegister(ActionEvent actionEvent) {
        RegisterScene registerScene = new RegisterScene();
        registerScene.register(new Stage());
    }
}

