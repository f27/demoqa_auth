package cloud.autotests.models;

import lombok.Builder;

import java.util.List;

@Builder
public class ListOfBooksWithUserIdModel {
    private String userId;
    private List<IsbnModel> collectionOfIsbns;
}
