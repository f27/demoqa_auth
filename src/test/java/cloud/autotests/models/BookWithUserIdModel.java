package cloud.autotests.models;

import lombok.Builder;

@Builder
public class BookWithUserIdModel {
    private String userId;
    private String isbn;
}
