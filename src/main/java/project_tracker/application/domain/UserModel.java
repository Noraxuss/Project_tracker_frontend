package project_tracker.application.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserModel {

    private String username;
    private String password;
    private String email;

}
