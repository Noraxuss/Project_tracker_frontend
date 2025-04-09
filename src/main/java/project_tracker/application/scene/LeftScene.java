package project_tracker.application.scene;

import javafx.scene.Parent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeftScene {

    private static LeftScene instance;
    private String name;
    private Parent scene;

    private LeftScene() {}

    public static LeftScene getInstance() {
        if (instance == null) {
            instance = new LeftScene();
        }
        return instance;
    }


}
