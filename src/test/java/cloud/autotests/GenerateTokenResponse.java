package cloud.autotests;

import lombok.Getter;

@Getter
public class GenerateTokenResponse {
    private String expires;
    private String result;
    private String status;
    private String token;
}
