package project_tracker.application.controller;

import project_tracker.application.scene.SceneEngine;
import project_tracker.application.utilities.SceneEngineAware;

public class ControllerFactory {

    private final SceneEngine sceneEngine;

    public ControllerFactory(SceneEngine sceneEngine) {
        this.sceneEngine = sceneEngine;
    }

    public Object createController(Class<?> controllerClass) {
        try {
            Object controller = controllerClass.getDeclaredConstructor().newInstance();
            if (controller instanceof SceneEngineAware) {
                ((SceneEngineAware) controller).setSceneEngine(sceneEngine);
            }
            return controller;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create controller: " + controllerClass.getName(), e);
        }
    }

}
