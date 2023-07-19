package cloud.autotests;

import lombok.Getter;

@Getter
public class LoginResponse {
    private String userId;
    private String username;
    private String password;
    private String token;
    private String expires;
    private String created_date;
    private String isActive;
}
