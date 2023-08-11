package cloud.autotests.models;

import lombok.Getter;

@Getter
public class TokenModel {
    private String expires;
    private String result;
    private String status;
    private String token;
}
