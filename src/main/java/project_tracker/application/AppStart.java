package project_tracker.application;

import javafx.application.Application;
import javafx.stage.Stage;
import project_tracker.application.scene.LoginScene;

public class AppStart extends Application {

    @Override
    public void init() {
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Project Tracker");
        primaryStage.show();

        LoginScene loginScene = new LoginScene();
        loginScene.loginScene(primaryStage);
    }

    @Override
    public void stop() {
    }
}
