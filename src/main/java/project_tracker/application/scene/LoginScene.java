package project_tracker.application.scene;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project_tracker.application.utilities.Data;
import project_tracker.application.utilities.FXMLLoaderUtil;

public class LoginScene {

    public static final String FXML_LOGIN_SCENE_FXML = "/fxml/LoginScene.fxml";

    public void loginScene(Stage stage) {

        try {
            Parent root = FXMLLoaderUtil.loadFXML(FXML_LOGIN_SCENE_FXML);
            Scene scene = new Scene(root, Data.SceneHeight, Data.SceneWidth);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

