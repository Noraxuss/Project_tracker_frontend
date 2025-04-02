package project_tracker.application.scene;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project_tracker.application.utilities.Data;
import project_tracker.application.utilities.FXMLLoaderUtil;

public class RegisterScene {

    public static final String FXML_REGISTER_SCENE_FXML = "/fxml/RegisterScene.fxml";

    public void register(Stage stage) {

        try {
            Parent root = FXMLLoaderUtil.loadFXML(FXML_REGISTER_SCENE_FXML);
            Scene scene = new Scene(root, Data.SceneHeight, Data.SceneWidth);
            stage.setTitle("Register");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
