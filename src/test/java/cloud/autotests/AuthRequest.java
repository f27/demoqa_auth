package cloud.autotests;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthRequest {
    private String userName;
    private String password;
}
