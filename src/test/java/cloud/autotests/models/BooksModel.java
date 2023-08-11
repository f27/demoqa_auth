package cloud.autotests.models;

import lombok.Getter;

import java.util.List;

@Getter
public class BooksModel {
    private List<IsbnModel> books;
}
