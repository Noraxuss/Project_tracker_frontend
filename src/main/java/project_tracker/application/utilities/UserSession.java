package project_tracker.application.utilities;

import lombok.Getter;
import lombok.Setter;
import project_tracker.application.domain.UserModel;

@Getter
@Setter
public class UserSession {
    private static UserSession instance;
    private UserModel userModel;

    private UserSession() {
        // Private constructor to prevent instantiation
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void clearSession() {
        userModel = null;
    }
}