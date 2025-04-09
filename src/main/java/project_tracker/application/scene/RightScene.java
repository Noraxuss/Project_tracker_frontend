package project_tracker.application.scene;

import javafx.scene.Parent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RightScene {

    private static RightScene instance;
    private String name;
    private Parent scene;

    private RightScene() {}

    public static RightScene getInstance() {
        if (instance == null) {
            instance = new RightScene();
        }
        return instance;
    }
}
