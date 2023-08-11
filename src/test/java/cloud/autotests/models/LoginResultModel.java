package cloud.autotests.models;

import lombok.Getter;

@Getter
public class LoginResultModel {
    private String userId;
    private String username;
    private String password;
    private String token;
    private String expires;
    private String created_date;
    private String isActive;
}
