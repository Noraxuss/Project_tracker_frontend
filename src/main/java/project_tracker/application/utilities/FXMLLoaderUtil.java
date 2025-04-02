package project_tracker.application.utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.IOException;
import java.util.ResourceBundle;

public class FXMLLoaderUtil {

    public static Parent loadFXML(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(FXMLLoaderUtil.class.getResource(fxmlPath));
        loader.setResources(ResourceBundle.getBundle("languages.messages",
                LocaleContextHolder.getLocale()));
        return loader.load();
    }
}