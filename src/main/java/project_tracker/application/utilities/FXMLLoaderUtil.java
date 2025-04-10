package project_tracker.application.utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.Setter;
import project_tracker.application.controller.ControllerFactory;
import project_tracker.application.scene.SceneEngine;

import java.io.IOException;
import java.util.ResourceBundle;


public class FXMLLoaderUtil {

    private static ControllerFactory controllerFactory;

    public static void initialize(SceneEngine sceneEngine) {
        controllerFactory = new ControllerFactory(sceneEngine);
    }

    public static Parent loadFXML(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(FXMLLoaderUtil.class.getResource(fxmlPath));
        loader.setResources(ResourceBundle.getBundle("languages/messages_hu"));

        // Use the controller factory
        loader.setControllerFactory(controllerFactory::createController);

        return loader.load();
    }
}